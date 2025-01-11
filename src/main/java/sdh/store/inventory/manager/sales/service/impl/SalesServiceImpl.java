package sdh.store.inventory.manager.sales.service.impl;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import sdh.store.inventory.manager.exception.InsufficientStockException;
import sdh.store.inventory.manager.movement.dto.CurrentStockDTO;
import sdh.store.inventory.manager.movement.dto.MovementCreateDTO;
import sdh.store.inventory.manager.movement.mappers.MovementMapper;
import sdh.store.inventory.manager.sale_details.dto.SaleDetailsCreateDTO;
import sdh.store.inventory.manager.sale_details.mappers.SaleDetailsMapper;
import sdh.store.inventory.manager.sales.dto.ProductCreateSaleDTO;
import sdh.store.inventory.manager.sales.dto.SalesCreateDTO;
import sdh.store.inventory.manager.sales.dto.SalesDTO;
import sdh.store.inventory.manager.sales.enumerate.ReferenceDocumentType;
import sdh.store.inventory.manager.sales.mappers.SalesMapper;
import sdh.store.inventory.manager.sales.service.SalesService;
import sdh.store.inventory.manager.util.PaginationUtils;

import static sdh.store.inventory.manager.util.Constants.EXIT;
import static sdh.store.inventory.manager.util.Constants.NOT_ENOUGH_STOCK_PRODUCT;

@Service
@Slf4j
public class SalesServiceImpl implements SalesService {

  @Autowired
  private SalesMapper salesMapper;

  @Autowired
  private SaleDetailsMapper saleDetailsMapper;

  @Autowired
  private MovementMapper movementMapper;

  @Autowired
  private TransactionTemplate transactionTemplate;


  @Override
  @Transactional(rollbackFor = InsufficientStockException.class)
  public Mono<Integer> createSale(SalesCreateDTO salesCreateDTO) {
    return Mono.fromCallable(() ->
        transactionTemplate.execute(status -> {
          try {
            calculateSubTotalAndTotal(salesCreateDTO);

            salesMapper.createSale(salesCreateDTO);

            Integer saleCreatedId = salesMapper.getLastInsertedId();

            validateProductStockAndSaveSaleDetailsAndMovement(salesCreateDTO, saleCreatedId);
            return salesCreateDTO.getId();
          } catch (InsufficientStockException e) {
            System.out.println("Error al crear la venta: " + e.getMessage());
            status.setRollbackOnly();
            throw e;
          }
        }))
      .subscribeOn(Schedulers.boundedElastic())
      .doOnError(RuntimeException::new);
  }

  private void validateProductStockAndSaveSaleDetailsAndMovement(SalesCreateDTO salesCreateDTO, Integer saleCreatedId) {
    for (ProductCreateSaleDTO product : salesCreateDTO.getProducts()) {
      CurrentStockDTO currentStockProduct = movementMapper.getCurrentStockByProductId(Long.valueOf(product.getProductId()));
      if (currentStockProduct.getCurrentStock() < product.getQuantity()) {
        throw new InsufficientStockException(NOT_ENOUGH_STOCK_PRODUCT + ": " + product.getProductId());
      }
      SaleDetailsCreateDTO saleDetailsCreateDTO = SaleDetailsCreateDTO
        .builder()
        .saleId(saleCreatedId)
        .productId(product.getProductId())
        .quantity(product.getQuantity())
        .unitPrice(product.getPrice())
        .subTotal(product.getQuantity() * product.getPrice())
        .build();
      saleDetailsMapper.createSaleDetails(saleDetailsCreateDTO);

      // Paso 3: Registrar el movimiento de salida
      MovementCreateDTO movementCreateDTO = MovementCreateDTO
        .builder()
        .productId(product.getProductId())
        .movementType(EXIT)
        .quantity(-product.getQuantity())
        .description(salesCreateDTO.getSaleDescription())
        .referenceDocument(salesCreateDTO.getReferenceDocument())
        .userId(salesCreateDTO.getUserId())
        .build();
      movementMapper.createMovement(movementCreateDTO);
    }
  }

  private static void calculateSubTotalAndTotal(SalesCreateDTO salesCreateDTO) {
    double subtotal = salesCreateDTO.getProducts().stream()
      .mapToDouble(product -> product.getQuantity() * product.getPrice())
      .sum();

    double taxes = 0;
    if (ReferenceDocumentType.FACTURA.name().equalsIgnoreCase(salesCreateDTO.getReferenceDocumentType().name())) {
      taxes = subtotal * 0.18;
    }
    double totalAmount = subtotal + taxes;

    salesCreateDTO.setTotalAmount(totalAmount);
  }

  @Override
  public Mono<PageInfo<SalesDTO>> findAllSales(int page, int size) {
    return Mono.fromCallable(() -> PaginationUtils.toPageInfo(page, size, salesMapper::findAllSales))
      .switchIfEmpty(Mono.defer(Mono::empty))
      .subscribeOn(Schedulers.boundedElastic());
  }
}

package sdh.store.inventory.manager.product.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import sdh.store.inventory.manager.product.dto.ProductCreateDTO;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.product.dto.ProductUpdateDTO;
import sdh.store.inventory.manager.product.mappers.ProductMapper;
import sdh.store.inventory.manager.product.service.ProductService;
import sdh.store.inventory.manager.util.PaginationUtils;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Mono<PageInfo<ProductDTO>> findAllProducts(int page, int size) {
        return Mono.fromCallable(() -> PaginationUtils.toPageInfo(page, size, productMapper::findAllProducts))
                .subscribeOn(Schedulers.boundedElastic())
                .switchIfEmpty(Mono.defer(Mono::empty));
    }

    @Override
    @Transactional
    public Mono<Integer> createProduct(ProductCreateDTO product) {
        return Mono.fromCallable(() -> productMapper.createProduct(product))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(RuntimeException::new);
    }

    @Override
    public Mono<ProductDTO> getProductById(Long id) {
        return Mono.fromCallable(() -> productMapper.getProductById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Mono.defer(Mono::empty));
    }

    @Override
    @Transactional
    public Mono<Integer> updateProduct(Long id, ProductUpdateDTO product) {
        return Mono.fromCallable(() -> productMapper.updateProduct(id, product))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(RuntimeException::new);
    }

    @Override
    @Transactional
    public Mono<Integer> deleteProduct(Long id) {
        return Mono.fromCallable(() -> productMapper.deleteProduct(id))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(RuntimeException::new);
    }


}

package sdh.store.inventory.manager.sale_details.service;

import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.sale_details.dto.SaleDetailsCreateDTO;

public interface SaleDetailsService {

    Mono<Integer> createSaleDetails(SaleDetailsCreateDTO saleDetailsCreateDTO);
}

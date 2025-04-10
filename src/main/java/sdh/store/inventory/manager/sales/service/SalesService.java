package sdh.store.inventory.manager.sales.service;


import com.github.pagehelper.PageInfo;
import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.sales.dto.SalesCreateDTO;
import sdh.store.inventory.manager.sales.dto.SalesDTO;

public interface SalesService {
    Mono<PageInfo<SalesDTO>> findAllSales(int page, int size);

    Mono<Integer> createSale(SalesCreateDTO salesCreateDTO);
}

package sdh.store.inventory.manager.sale_details.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import sdh.store.inventory.manager.sale_details.dto.SaleDetailsCreateDTO;
import sdh.store.inventory.manager.sale_details.mappers.SaleDetailsMapper;
import sdh.store.inventory.manager.sale_details.service.SaleDetailsService;

@Service
public class SaleDetailsServiceImpl implements SaleDetailsService {

    @Autowired
    private SaleDetailsMapper saleDetailsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Mono<Integer> createSaleDetails(SaleDetailsCreateDTO saleDetailsCreateDTO) {
        return Mono.fromCallable(() -> saleDetailsMapper.createSaleDetails(saleDetailsCreateDTO))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(RuntimeException::new);
    }
}

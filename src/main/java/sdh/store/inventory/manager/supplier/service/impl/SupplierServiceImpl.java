package sdh.store.inventory.manager.supplier.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import sdh.store.inventory.manager.supplier.dto.SupplierDTO;
import sdh.store.inventory.manager.supplier.mappers.SupplierMapper;
import sdh.store.inventory.manager.supplier.service.SupplierService;
import sdh.store.inventory.manager.util.PaginationUtils;

import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierMapper supplierMapper;

    @Autowired
    public SupplierServiceImpl(SupplierMapper supplierMapper) {
        this.supplierMapper = supplierMapper;
    }

    @Override
    public Mono<PageInfo<SupplierDTO>> findAllSuppliers(int page, int size) {
        return Mono.fromCallable(() -> PaginationUtils.toPageInfo(page, size, supplierMapper::findAllSuppliers))
                .switchIfEmpty(Mono.defer(Mono::empty))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Integer> createSupplier(SupplierDTO supplier) {
        return Mono.fromCallable(() -> supplierMapper.createSupplier(supplier))
                .doOnError(RuntimeException::new)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<SupplierDTO> getSupplierById(Long id) {
        return Mono.fromCallable(() -> supplierMapper.getSupplierById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Mono.defer(Mono::empty))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Integer> updateSupplier(Long id, SupplierDTO supplier) {
        return Mono.fromCallable(() -> supplierMapper.updateSupplier(id, supplier))
                .doOnError(RuntimeException::new)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Integer> deleteSupplier(Long id) {
        return Mono.fromCallable(() -> supplierMapper.deleteSupplier(id))
                .doOnError(RuntimeException::new)
                .subscribeOn(Schedulers.boundedElastic());
    }
}

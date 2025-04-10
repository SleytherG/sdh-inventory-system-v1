package sdh.store.inventory.manager.supplier.service;

import com.github.pagehelper.PageInfo;
import io.micrometer.observation.ObservationFilter;
import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.supplier.dto.SupplierDTO;

public interface SupplierService {
    Mono<PageInfo<SupplierDTO>> findAllSuppliers(int page, int size);

    Mono<Integer> createSupplier(SupplierDTO supplier);

    Mono<SupplierDTO> getSupplierById(Long id);

    Mono<Integer> updateSupplier(Long id, SupplierDTO supplier);

    Mono<Integer> deleteSupplier(Long id);
}

package sdh.store.inventory.manager.supplier.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sdh.store.inventory.manager.supplier.dto.SupplierDTO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SupplierMapper {
    List<SupplierDTO> findAllSuppliers();

    Integer createSupplier(
            @Param("supplier") SupplierDTO supplier);

    Optional<SupplierDTO> getSupplierById(
            @Param("id") Long id);

    Integer updateSupplier(
            @Param("id") Long id,
            @Param("supplier") SupplierDTO supplier);

    Integer deleteSupplier(
            @Param("id") Long id);
}

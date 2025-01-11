package sdh.store.inventory.manager.sale_details.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sdh.store.inventory.manager.sale_details.dto.SaleDetailsCreateDTO;

@Mapper
public interface SaleDetailsMapper {

    Integer createSaleDetails(
            @Param("saleDetails") SaleDetailsCreateDTO saleDetailsCreateDTO);

}

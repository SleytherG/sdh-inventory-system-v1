package sdh.store.inventory.manager.sales.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sdh.store.inventory.manager.sales.dto.SalesCreateDTO;
import sdh.store.inventory.manager.sales.dto.SalesDTO;

import java.util.List;

@Mapper
public interface SalesMapper {


    List<SalesDTO> findAllSales();

    void createSale(
            @Param("sales") SalesCreateDTO salesCreateDTO);

    Integer getLastInsertedId();

}

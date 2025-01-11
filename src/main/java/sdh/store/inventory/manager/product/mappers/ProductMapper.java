package sdh.store.inventory.manager.product.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sdh.store.inventory.manager.product.dto.ProductCreateDTO;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.product.dto.ProductUpdateDTO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {

//    List<ProductDTO> findAllProducts(
//            @Param("limit") int limit,
//            @Param("offset") int offset
//    );

    List<ProductDTO> findAllProducts();

    Integer createProduct(
            @Param("product") ProductCreateDTO product
    );

    Optional<ProductDTO> getProductById(
            @Param("id") Long id);

    Integer updateProduct(
            @Param("id") Long id,
            @Param("product") ProductUpdateDTO product
    );

    Integer deleteProduct(
            @Param("id") Long id);
}

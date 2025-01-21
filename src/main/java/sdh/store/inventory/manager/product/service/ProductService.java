package sdh.store.inventory.manager.product.service;

import com.github.pagehelper.PageInfo;
import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.product.dto.ProductCreateDTO;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.product.dto.ProductUpdateDTO;

public interface ProductService {

    Mono<PageInfo<ProductDTO>> findAllProducts(int page, int size);

    Mono<Integer> createProduct(ProductCreateDTO product);

    Mono<ProductDTO> getProductById(Long id);

    Mono<Integer> updateProduct(Long id, ProductUpdateDTO product);

    Mono<Integer> deleteProduct(Long id);
}

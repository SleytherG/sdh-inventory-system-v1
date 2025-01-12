package sdh.store.inventory.manager.product.service;


import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import sdh.store.inventory.manager.product.dto.ProductCreateDTO;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.product.dto.ProductUpdateDTO;

import java.util.List;

public interface ProductService {

    Flowable<List<ProductDTO>> findAllProducts(int page, int size);

    Maybe<Integer> createProduct(ProductCreateDTO product);

    Maybe<ProductDTO> getProductById(Long id);

    Maybe<Integer> updateProduct(Long id, ProductUpdateDTO product);

    Maybe<Integer> deleteProduct(Long id);
}

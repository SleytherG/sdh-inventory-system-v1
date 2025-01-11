package sdh.store.inventory.manager.product.service;


import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import sdh.store.inventory.manager.product.dto.ProductDTO;

public interface ProductService {

    Flowable<ProductDTO> findAllProducts();
}

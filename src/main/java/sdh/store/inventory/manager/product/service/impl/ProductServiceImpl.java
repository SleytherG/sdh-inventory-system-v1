package sdh.store.inventory.manager.product.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.product.mappers.ProductMapper;
import sdh.store.inventory.manager.product.repository.ProductRepository;
import sdh.store.inventory.manager.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Find all products.
     *
     * @return a {@link Flowable} that emits all products as {@link ProductDTO}s
     */
    @Override
    public Flowable<ProductDTO> findAllProducts() {
        return Flowable.fromStream(productRepository.findAll()
                .stream().map(ProductMapper::toProductDTO))
                ;
    }
}

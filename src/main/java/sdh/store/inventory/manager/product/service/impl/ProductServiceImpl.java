package sdh.store.inventory.manager.product.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdh.store.inventory.manager.product.dto.ProductCreateDTO;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.product.dto.ProductUpdateDTO;
import sdh.store.inventory.manager.product.mappers.ProductMapper;
import sdh.store.inventory.manager.product.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * Find all products.
     *
     * @return a {@link Flowable} that emits all products as {@link ProductDTO}s
     */
    @Override
    public Flowable<List<ProductDTO>> findAllProducts(int page, int size) {
        return Flowable.fromCallable(() -> productMapper.findAllProducts(size, calculateOffset(page, size)))
                .subscribeOn(Schedulers.io())
                .switchIfEmpty(Flowable.defer(Flowable::empty));
    }

    private Integer calculateOffset(int page,int size) {
        return page * size;
    }

    @Override
    @Transactional
    public Maybe<Integer> createProduct(ProductCreateDTO product) {
        return Maybe.fromCallable(() -> productMapper.createProduct(product))
                .subscribeOn(Schedulers.io())
                .doOnError(RuntimeException::new);
    }

    @Override
    public Maybe<ProductDTO> getProductById(Long id) {
        return Maybe.fromCallable(() -> productMapper.getProductById(id))
                .subscribeOn(Schedulers.io())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Maybe.defer(Maybe::empty));
    }

    @Override
    @Transactional
    public Maybe<Integer> updateProduct(Long id, ProductUpdateDTO product) {
        return Maybe.fromCallable(() -> productMapper.updateProduct(id, product))
                .subscribeOn(Schedulers.io())
                .doOnError(RuntimeException::new);
    }

    @Override
    @Transactional
    public Maybe<Integer> deleteProduct(Long id) {
        return Maybe.fromCallable(() -> productMapper.deleteProduct(id))
                .subscribeOn(Schedulers.io())
                .doOnError(RuntimeException::new);
    }


}

package sdh.store.inventory.manager.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Flowable<PageInfo<ProductDTO>> findAllProducts(int page, int size) {
        return Flowable.fromCallable(() -> toPageInfo(page, size))
                .subscribeOn(Schedulers.io())
                .switchIfEmpty(Flowable.defer(Flowable::empty));
    }

    private PageInfo<ProductDTO> toPageInfo(int page, int size) {
        PageHelper.startPage(page, size);
        List<ProductDTO> products = productMapper.findAllProducts();
        return new PageInfo<ProductDTO>(products);
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

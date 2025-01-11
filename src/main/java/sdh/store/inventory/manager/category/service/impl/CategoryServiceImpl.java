package sdh.store.inventory.manager.category.service.impl;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import sdh.store.inventory.manager.category.dto.CategoryCreateDTO;
import sdh.store.inventory.manager.category.dto.CategoryDTO;
import sdh.store.inventory.manager.category.dto.CategoryUpdateDTO;
import sdh.store.inventory.manager.category.dto.CategoryUpdateResponseDTO;
import sdh.store.inventory.manager.category.mappers.CategoryMapper;
import sdh.store.inventory.manager.category.service.CategoryService;
import sdh.store.inventory.manager.util.PaginationUtils;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {



    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Mono<PageInfo<CategoryDTO>> findAllCategories(int page, int size) {
        return Mono.fromCallable(() -> PaginationUtils.toPageInfo(page, size, categoryMapper::findAllCategories))
                .switchIfEmpty(Mono.defer(Mono::empty))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Mono<Integer> createCategory(CategoryCreateDTO category) {
        return Mono.fromCallable(() -> categoryMapper.createCategory(category))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(RuntimeException::new);
    }

    @Override
    public Mono<CategoryDTO> getCategoryById(Long id) {
        return Mono.fromCallable(() -> categoryMapper.getCategoryById(id))
                .doOnSubscribe(subscription -> log.info("Starting to fetch category with ID: {}", id))
                .doOnSuccess(category -> log.info("Fetched category: {}", category))
                .doOnError(error -> log.error("Error fetching category with ID: {}", id))
                .switchIfEmpty(Mono.defer(Mono::empty))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Integer> updateCategory(Long id, CategoryUpdateDTO category) {
        return Mono.fromCallable(() -> categoryMapper.updateCategory(id, category))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(RuntimeException::new);

    }

    @Override
    @Transactional
    public Mono<Integer> deleteCategory(Long id) {
        return Mono.fromCallable(() -> categoryMapper.deleteCategory(id))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(RuntimeException::new);
    }


}

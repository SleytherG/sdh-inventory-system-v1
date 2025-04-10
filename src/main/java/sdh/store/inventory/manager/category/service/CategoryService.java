package sdh.store.inventory.manager.category.service;


import com.github.pagehelper.PageInfo;
import reactor.core.publisher.Mono;
import sdh.store.inventory.manager.category.dto.CategoryCreateDTO;
import sdh.store.inventory.manager.category.dto.CategoryDTO;
import sdh.store.inventory.manager.category.dto.CategoryDeleteResponseDTO;
import sdh.store.inventory.manager.category.dto.CategoryUpdateDTO;
import sdh.store.inventory.manager.category.dto.CategoryUpdateResponseDTO;

public interface CategoryService {
    Mono<PageInfo<CategoryDTO>> findAllCategories(int page, int size);

    Mono<Integer> createCategory(CategoryCreateDTO category);

    Mono<CategoryDTO> getCategoryById(Long id);

    Mono<Integer> updateCategory(Long id, CategoryUpdateDTO category);

    Mono<Integer> deleteCategory(Long id);
}

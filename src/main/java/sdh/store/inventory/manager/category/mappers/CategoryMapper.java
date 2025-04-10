package sdh.store.inventory.manager.category.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sdh.store.inventory.manager.category.dto.CategoryCreateDTO;
import sdh.store.inventory.manager.category.dto.CategoryDTO;
import sdh.store.inventory.manager.category.dto.CategoryUpdateDTO;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDTO> findAllCategories();

    Integer createCategory(
            @Param("category") CategoryCreateDTO category);

    CategoryDTO getCategoryById(
            @Param("id") Long id);

    Integer updateCategory(
            @Param("id") Long id,
            @Param("category") CategoryUpdateDTO category);

    Integer deleteCategory(
            @Param("id") Long id);
}

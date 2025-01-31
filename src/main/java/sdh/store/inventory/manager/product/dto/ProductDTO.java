package sdh.store.inventory.manager.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sdh.store.inventory.manager.category.dto.CategoryDTO;
import sdh.store.inventory.manager.supplier.dto.SupplierDTO;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private CategoryDTO category;
    private SupplierDTO supplier;
    private Boolean isActive;
}

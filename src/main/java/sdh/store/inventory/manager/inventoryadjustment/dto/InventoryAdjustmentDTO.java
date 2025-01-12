package sdh.store.inventory.manager.inventoryadjustment.dto;


import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.user.dto.UserDTO;

import java.time.LocalDateTime;

public class InventoryAdjustmentDTO {

    private Long id;
    private ProductDTO product;
    private Integer adjustedQuantity;
    private String description;
    private LocalDateTime adjustmentDate = LocalDateTime.now();
    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getAdjustedQuantity() {
        return adjustedQuantity;
    }

    public void setAdjustedQuantity(Integer adjustedQuantity) {
        this.adjustedQuantity = adjustedQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAdjustmentDate() {
        return adjustmentDate;
    }

    public void setAdjustmentDate(LocalDateTime adjustmentDate) {
        this.adjustmentDate = adjustmentDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}

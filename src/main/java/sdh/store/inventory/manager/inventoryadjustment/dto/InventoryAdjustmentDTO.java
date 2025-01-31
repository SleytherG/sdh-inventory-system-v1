package sdh.store.inventory.manager.inventoryadjustment.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.user.dto.UserDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryAdjustmentDTO {

    private Long id;
    private ProductDTO product;
    private Integer adjustedQuantity;
    private String description;
    private LocalDateTime adjustmentDate = LocalDateTime.now();
    private UserDTO user;

}

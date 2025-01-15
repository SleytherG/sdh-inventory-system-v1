package sdh.store.inventory.manager.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sdh.store.inventory.manager.movement.enumerate.MovementType;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.user.dto.UserDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementTypeDTO {

    private Long id;
    private ProductDTO product;
    private Integer quantity;
    private MovementType movementType;
    private final LocalDateTime movementDate = LocalDateTime.now();
    private UserDTO user;
    private String description;

}

package sdh.store.inventory.manager.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sdh.store.inventory.manager.movement.enumerate.MovementType;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.product.dto.ProductMovementDTO;
import sdh.store.inventory.manager.user.dto.UserDTO;
import sdh.store.inventory.manager.user.dto.UserMovementDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementDTO {

    private Long id;
    private ProductMovementDTO product;
    private Integer quantity;
    private MovementType movementType;
    private final LocalDateTime movementDate = LocalDateTime.now();
    private UserMovementDTO user;
    private String description;
    private String referenceDocument;

}

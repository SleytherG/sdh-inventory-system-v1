package sdh.store.inventory.manager.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sdh.store.inventory.manager.movement.enumerate.MovementType;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.user.dto.UserDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementCreateDTO {

    private Long id;
    private Integer productId;
    private Integer quantity;
    private String movementType;
    private final LocalDateTime movementDate = LocalDateTime.now();
    private Integer userId;
    private String description;
    private String referenceDocument;

}

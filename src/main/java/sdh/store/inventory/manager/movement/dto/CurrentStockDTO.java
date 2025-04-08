package sdh.store.inventory.manager.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentStockDTO {

    private Long currentStock;
    private Long productId;
    private String productName;

}

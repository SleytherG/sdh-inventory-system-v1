package sdh.store.inventory.manager.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateSaleDTO {

    private Integer productId;
    private Integer quantity;
    private Double price;
}

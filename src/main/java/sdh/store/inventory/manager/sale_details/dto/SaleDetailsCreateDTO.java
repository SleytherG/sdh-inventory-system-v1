package sdh.store.inventory.manager.sale_details.dto;

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
public class SaleDetailsCreateDTO {

    private Integer saleId;
    private Integer productId;
    private Integer quantity;
    private Double unitPrice;
    private Double subTotal;

}

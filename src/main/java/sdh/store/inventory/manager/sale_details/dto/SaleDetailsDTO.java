package sdh.store.inventory.manager.sale_details.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sdh.store.inventory.manager.product.dto.ProductDTO;
import sdh.store.inventory.manager.sales.dto.SalesDTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailsDTO {

    private Integer id;
//    private SalesDTO sale;
    private ProductDTO product;
    private Integer quantity;
    private Double unitPrice;
    private Double subTotal;


}

package sdh.store.inventory.manager.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sdh.store.inventory.manager.sales.enumerate.PaymentMethod;
import sdh.store.inventory.manager.sales.enumerate.ReferenceDocumentType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesCreateDTO {

    private Integer id;
    private String customerFullName;
    private Long customerDocumentNumber;
    private Double totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime saleDate = LocalDateTime.now();
    private String referenceDocument;
    private ReferenceDocumentType referenceDocumentType;

    private List<ProductCreateSaleDTO> products;
    private Integer userId;
    private String saleDescription;

}

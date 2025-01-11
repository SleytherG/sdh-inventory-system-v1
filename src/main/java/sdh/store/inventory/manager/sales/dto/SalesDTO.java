package sdh.store.inventory.manager.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesDTO {

    private Integer id;
    private String customerFullName;
    private Long customerDocumentNumber;
    private Double totalAmount;
    private String paymentMethod;
    private LocalDateTime saleDate = LocalDateTime.now();
    private String referenceDocumentType;
    private String referenceDocument;


}

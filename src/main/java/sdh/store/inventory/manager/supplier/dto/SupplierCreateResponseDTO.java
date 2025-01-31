package sdh.store.inventory.manager.supplier.dto;

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
public class SupplierCreateResponseDTO {

    private String message;
    private Integer code;
}

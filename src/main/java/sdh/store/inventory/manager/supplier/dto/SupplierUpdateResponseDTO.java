package sdh.store.inventory.manager.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierUpdateResponseDTO {

    private Integer code;
    private String message;
}

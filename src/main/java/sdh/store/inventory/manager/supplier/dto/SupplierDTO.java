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
public class SupplierDTO {

    private Long id;
    private String name;
    private String contact;
    private String phoneNumber;
    private String address;
    private Boolean isActive;

}

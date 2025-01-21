package sdh.store.inventory.manager.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryUpdateResponseDTO {

    private Integer code;
    private String message;

}

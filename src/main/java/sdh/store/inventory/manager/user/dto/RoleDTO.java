package sdh.store.inventory.manager.user.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {

    private Long id;
    private String roleName;
    private Set<PermissionDTO> permissions = new HashSet<>();

}

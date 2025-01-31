package sdh.store.inventory.manager.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {

    private Long id;
    private String roleName;
    private Set<PermissionDTO> permissions = new HashSet<>();

}

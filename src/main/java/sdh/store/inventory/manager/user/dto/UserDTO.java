package sdh.store.inventory.manager.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String fullName;
    private String password;
    private String username;
    private Boolean isEnabled;
    private Boolean accountNoExpired;
    private Boolean accountNoLocked;
    private Boolean credentialsNoExpired;
    private Set<RoleDTO> roles = new HashSet<>();

}

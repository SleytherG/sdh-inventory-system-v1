package sdh.store.inventory.manager.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sdh.store.inventory.manager.user.enumerate.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String fullName;
    private Role role;
    private String password;

}

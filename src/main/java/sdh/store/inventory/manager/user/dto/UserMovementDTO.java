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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserMovementDTO {

    private Long id;
    private String fullName;
    private String username;
    private Boolean isEnabled;
}

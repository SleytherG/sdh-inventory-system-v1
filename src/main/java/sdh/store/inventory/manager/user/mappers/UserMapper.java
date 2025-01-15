package sdh.store.inventory.manager.user.mappers;

import org.apache.ibatis.annotations.Mapper;
//import org.springframework.security.core.userdetails.User;
import sdh.store.inventory.manager.user.dto.UserDTO;

import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<UserDTO> findByUsername(String username);


}

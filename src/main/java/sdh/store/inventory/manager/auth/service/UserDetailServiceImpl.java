package sdh.store.inventory.manager.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sdh.store.inventory.manager.user.dto.UserDTO;
import sdh.store.inventory.manager.user.mappers.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userFounded = userMapper.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userFounded.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleName()))));

        userFounded.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        return new User(
                userFounded.getUsername(),
                userFounded.getPassword(),
                userFounded.getIsEnabled(),
                userFounded.getAccountNoExpired(),
                userFounded.getCredentialsNoExpired(),
                userFounded.getAccountNoLocked(),
                authorityList
        );
    }

}

package sdh.store.inventory.manager.auth.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sdh.store.inventory.manager.auth.config.JwtUtils;
import sdh.store.inventory.manager.auth.dto.AuthLoginRequestDTO;
import sdh.store.inventory.manager.auth.dto.AuthResponseDTO;
import sdh.store.inventory.manager.user.dto.UserDTO;
import sdh.store.inventory.manager.user.mappers.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final JwtUtils jwtUtils;

    public UserDetailServiceImpl(
            UserMapper userMapper,
            JwtUtils jwtUtils,
            PasswordEncoder passwordEncoder
            ) {
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

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

    public AuthResponseDTO loginUser(AuthLoginRequestDTO authLoginRequestDTO) {
        String username = authLoginRequestDTO.username();
        String password = authLoginRequestDTO.password();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(username, "User logged successfully", accessToken, true);
        return authResponseDTO;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);

        if ( userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

}

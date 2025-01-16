package sdh.store.inventory.manager.auth.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import sdh.store.inventory.manager.auth.config.JwtUtils;
import sdh.store.inventory.manager.auth.dto.AuthLoginRequest;
import sdh.store.inventory.manager.auth.dto.AuthResponse;
import sdh.store.inventory.manager.auth.service.UserDetailServiceImpl;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(
            @Parameter(required = true) @RequestBody @Valid AuthLoginRequest userRequest) {
        return new ResponseEntity<>(userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }
}

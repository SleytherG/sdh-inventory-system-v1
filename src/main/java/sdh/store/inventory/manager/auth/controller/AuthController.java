package sdh.store.inventory.manager.auth.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdh.store.inventory.manager.auth.dto.AuthLoginRequestDTO;
import sdh.store.inventory.manager.auth.dto.AuthResponseDTO;
import sdh.store.inventory.manager.auth.service.UserDetailServiceImpl;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDTO> login(
            @Parameter(required = true) @RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity<>(userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }
}

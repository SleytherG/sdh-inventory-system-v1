package sdh.store.inventory.manager.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import sdh.store.inventory.manager.auth.config.JwtUtil;

//@RestController
//@RequestMapping(value = "/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @PostMapping(value = "/login")
//    public ResponseEntity<?> login(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password
//    ) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            String token = jwtUtil.generateToken(userDetails.getUsername());
//            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
//        } catch (BadCredentialsException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials");
//        }
//    }
//}

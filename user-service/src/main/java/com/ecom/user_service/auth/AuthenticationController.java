package com.ecom.user_service.auth;


import com.ecom.user_service.config.JwtService;
import com.ecom.user_service.dto.AuthenticatedResponseDTO;
import com.ecom.user_service.dto.LoginRequestDTO;
import com.ecom.user_service.dto.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticatedResponseDTO> register(@RequestBody RegisterRequestDTO request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticatedResponseDTO> login(@RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/extract_username")
    public String validateToken(@RequestParam("token") String token) {
        return jwtService.extractUsername(token);
    }

}

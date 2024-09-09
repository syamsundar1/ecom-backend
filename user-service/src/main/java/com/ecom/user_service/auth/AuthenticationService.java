package com.ecom.user_service.auth;

import com.ecom.user_service.config.JwtService;
import com.ecom.user_service.dto.AuthenticatedResponseDTO;
import com.ecom.user_service.dto.LoginRequestDTO;
import com.ecom.user_service.dto.RegisterRequestDTO;
import com.ecom.user_service.model.Role;
import com.ecom.user_service.model.User;
import com.ecom.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticatedResponseDTO register(RegisterRequestDTO request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNo1(request.getPhoneNo1())
                .phoneNo2(request.getPhoneNo2())
                .role(Role.USER)
                .build();

        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());


        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatedResponseDTO.builder().token(jwtToken).build();
    }

    public AuthenticatedResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow( () -> new UsernameNotFoundException("User Not found"));
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return AuthenticatedResponseDTO.builder().token(token).build();
    }


}

package com.ecom.user_service.service;

import com.ecom.user_service.config.JwtService;
import com.ecom.user_service.dto.RegisterRequestDTO;
import com.ecom.user_service.model.User;
import com.ecom.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    public User updateUserById(Integer id, RegisterRequestDTO request) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        user.get().setFirstName(request.getFirstName()!=null?request.getFirstName(): user.get().getFirstName());
        user.get().setLastName(request.getLastName()!=null? request.getLastName() : user.get().getLastName());
        user.get().setEmail(request.getEmail()!=null? request.getEmail() : user.get().getEmail());
        user.get().setPhoneNo1(request.getPhoneNo1()!=null? request.getPhoneNo1() : user.get().getPhoneNo1());
        user.get().setPhoneNo2(request.getPhoneNo2()!=null? request.getPhoneNo2() : user.get().getPhoneNo2());
        user.get().setPassword(request.getPassword()!=null? request.getPassword() : user.get().getPassword());
        user.get().setUpdatedDate(LocalDateTime.now());
        userRepository.save(user.get());
        return user.get();
    }

    public boolean deleteUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    public User findByEmail(String username) {
        Optional<User> user = userRepository.findByEmail(username);
        return user.orElse(null);
    }

    public User getUserInformation(Integer userId) {

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new RuntimeException("User not found ");
        }
        return user.get();
    }
}

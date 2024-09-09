package com.ecom.user_service.controller;

import com.ecom.user_service.config.JwtService;
import com.ecom.user_service.dto.RegisterRequestDTO;
import com.ecom.user_service.model.User;
import com.ecom.user_service.service.UserService;
import io.jsonwebtoken.Jwt;
import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserInformation(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(userService.getUserInformation(userId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable Integer id, @RequestBody RegisterRequestDTO request, Authentication authentication){

        try {

            String username;
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else if (principal instanceof String) {
                username = (String) principal;
            } else {
                throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
            }
            User loggedInUser = userService.findByEmail(username);

            if (!loggedInUser.getUserId().equals(id)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You are not authorized to update this user's details.");
            }

            User updatedUser = userService.updateUserById(id, request);
            if (updatedUser != null) {
                return ResponseEntity.ok("User updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the user: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id, Authentication authentication){
        try {

            String username;
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else if (principal instanceof String) {
                username = (String) principal;
            } else {
                throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
            }
            User loggedInUser = userService.findByEmail(username);

            if (!loggedInUser.getUserId().equals(id)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You are not authorized to delete this user's details.");
            }

            boolean isDeleted = userService.deleteUserById(id);

            if (isDeleted) {
                return ResponseEntity.ok("User deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the user: " + e.getMessage());
        }
    }

}

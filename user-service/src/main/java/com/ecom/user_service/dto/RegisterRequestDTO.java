package com.ecom.user_service.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long phoneNo1;
    private Long phoneNo2;

}

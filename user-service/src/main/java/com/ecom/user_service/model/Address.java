package com.ecom.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private Integer addressId;

    private Integer userId;
    private String doorNo;
    private  String street;
    private String city;
    private String state;
    private String country;
    private Long pinCode;
    private String landmark;


}

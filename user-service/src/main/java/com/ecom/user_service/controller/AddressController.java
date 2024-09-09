package com.ecom.user_service.controller;

import com.ecom.user_service.model.Address;
import com.ecom.user_service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/save/user/{userId}")
    public Address saveAddress(@PathVariable("userId") Integer userId, @RequestBody Address address){
        return addressService.saveAddress(address, userId);
    }

    @GetMapping("/all/{userId}")
    public List<Address> getAllAddress(@PathVariable("userId") Integer userId){
        return addressService.getAllAddress(userId);
    }

    @GetMapping("/{userId}/address/{addressId}")
    public Address getAddress(@PathVariable("userId") Integer userId, @PathVariable("addressId") Integer addressId){
        return addressService.getAddress(userId, addressId);
    }

}

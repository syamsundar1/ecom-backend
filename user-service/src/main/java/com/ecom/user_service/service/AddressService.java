package com.ecom.user_service.service;

import com.ecom.user_service.model.Address;
import com.ecom.user_service.model.User;
import com.ecom.user_service.repository.AddressRepository;
import com.ecom.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public Address saveAddress(Address address, Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        address.setUserId(user.isPresent() ? user.get().getUserId() : userId);
        return addressRepository.save(address);
    }

    public List<Address> getAllAddress(Integer userId) {
        return addressRepository.findByUserId(userId);
    }

    public Address getAddress(Integer userId, Integer addressId) {
        return addressRepository.findByUserIdAndAddressId(userId,addressId);

    }
}

package com.ecom.user_service.repository;

import com.ecom.user_service.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByUserId(Integer userId);

    Address findByUserIdAndAddressId(Integer userId, Integer addressId);
}

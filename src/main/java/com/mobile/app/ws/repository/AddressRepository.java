package com.mobile.app.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.app.ws.entity.AddressEntity;
import com.mobile.app.ws.entity.UserEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
	
	public List<AddressEntity> findAllByUserEntity(UserEntity userEntity);
	
	public AddressEntity findByAddressId(String addressId);
	
}

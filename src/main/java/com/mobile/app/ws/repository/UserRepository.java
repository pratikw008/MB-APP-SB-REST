package com.mobile.app.ws.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.app.ws.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	public boolean existsByEmail(String email);
	
	public UserEntity findByEmail(String email);
	
	public Optional<UserEntity> findByUserId(String userId);
}

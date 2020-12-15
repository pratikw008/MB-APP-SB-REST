package com.mobile.app.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.app.ws.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	public boolean existsByEmail(String email);
}

package com.mobile.app.ws.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 735549111701929336L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@Column
	private String userId;
	
	@Column(nullable = false, length = 50)
	private String firstName;
	
	@Column(nullable = false, length = 50)
	private String lastName;
	
	//@Column(nullable = false, length = 50, unique = true)
	@Column(nullable = false, length = 50)
	private String email;
	
	@Column(nullable = false)
	private String encryptedPassword;
	
	@Column
	private String emailVerificationToken;
	
	@Column(nullable = false)
	private Boolean emailVerificationStatus = false;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime cratedAt;
	
	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime lastModifiedAt;
}

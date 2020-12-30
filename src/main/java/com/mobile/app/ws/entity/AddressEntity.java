package com.mobile.app.ws.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = 1701609100861155148L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	private String addressId;
	
	private String city;
	
	private String country;
	
	private String streetName;
	
	private String postalCode;
	
	@Enumerated(EnumType.STRING)
	private AddressType addressType; 
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",referencedColumnName = "U_ID")
	private UserEntity userEntity;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime cratedAt;
	
	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime lastModifiedAt;
}

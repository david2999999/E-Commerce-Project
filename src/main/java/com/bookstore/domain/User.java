package com.bookstore.domain;

import static org.junit.Assert.fail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, updatable=false)
	private Long id;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	@Column(nullable = false, updatable=false)
	private String email;
	private String phone;
	private boolean enabled = true;
}





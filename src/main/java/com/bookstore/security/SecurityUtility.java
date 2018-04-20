package com.bookstore.security;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtility {
	
	private static final String SALT = "salt";// Salt should be protected carefully
	
	//Implementation of PasswordEncoder that uses the BCrypt 
	//strong hashing function. Clients can optionally supply a "strength" 
	//(a.k.a. log rounds in BCrypt) and a SecureRandom instance. 
	//The larger the strength parameter the more work will have to be done 
	//(exponentially) to hash the passwords. The default value is 10.
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		
		//public BCryptPasswordEncoder(int strength,
        //java.security.SecureRandom random)
		//Parameters:
		//strength - the log rounds to use, between 4 and 31
		//random - the secure random instance to use
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}
	
	// generate a random string for salt
	@Bean
	public static String randomPassword() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder salt = new StringBuilder();
		Random random = new Random();
		
		
		while (salt.length() < 18) {
			int index = (int) (random.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		
		String saltString = salt.toString();
		return saltString;
	}
}









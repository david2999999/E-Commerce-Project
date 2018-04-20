package com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookstore.domain.User;
import com.bookstore.repository.UserRepository;

//Core interface which loads user-specific data.
//It is used throughout the framework as a user DAO and is the strategy used by the DaoAuthenticationProvider.
//The interface requires only one read-only method, which simplifies support for new data-access strategies.
@Service
public class UserSecurityService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		return user;
	}
	

}

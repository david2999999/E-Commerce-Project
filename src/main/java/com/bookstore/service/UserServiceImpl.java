package com.bookstore.service;

import org.springframework.stereotype.Service;

import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.repository.PasswordResetTokenRepository;

@Service
public class UserServiceImpl implements UserService{

	private final PasswordResetTokenRepository passwordResetTokenRepository;
	
	public UserServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository) {
		this.passwordResetTokenRepository = passwordResetTokenRepository;
	}

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
	
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
		
	}

}

package com.abdulrehman.managee.service;

import org.springframework.http.ResponseEntity;

import com.abdulrehman.managee.model.RoleName;
import com.abdulrehman.managee.model.User;
import com.abdulrehman.managee.payload.LoginRequest;
import com.abdulrehman.managee.payload.SignUpRequest;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Feb 6, 2020
 */
public interface AuthService {
	/**
	 * Authenticate user by email/user name & password to get access to api's.
	 */
	public ResponseEntity<?> signin(LoginRequest loginRequest);

	/**
	 * New user registration.
	 */
	public ResponseEntity<?> signup(SignUpRequest signUpRequest);

	public User updateCurrentUserRole(RoleName roleName);

	public User getCurrentUser();
}

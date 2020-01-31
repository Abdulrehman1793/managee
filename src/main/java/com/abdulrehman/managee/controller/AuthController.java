package com.abdulrehman.managee.controller;

import com.abdulrehman.managee.exception.AppException;
import com.abdulrehman.managee.model.Role;
import com.abdulrehman.managee.model.RoleName;
import com.abdulrehman.managee.model.User;
import com.abdulrehman.managee.payload.ApiResponse;
import com.abdulrehman.managee.payload.AuthenticationResponse;
import com.abdulrehman.managee.payload.JwtAuthenticationResponse;
import com.abdulrehman.managee.payload.LoginRequest;
import com.abdulrehman.managee.payload.SignUpRequest;
import com.abdulrehman.managee.repository.RoleRepository;
import com.abdulrehman.managee.repository.UserRepository;
import com.abdulrehman.managee.security.JwtTokenProvider;
import com.abdulrehman.managee.security.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwt);

		String[] role = { "User" };

		AuthenticationResponse authenticationResponse = new AuthenticationResponse(userPrincipal.getName(),
				userPrincipal.getEmail(), userPrincipal.getUsername(), Arrays.asList(role),
				jwtAuthenticationResponse.getAccessToken(), jwtAuthenticationResponse.getTokenType());

		return ResponseEntity.ok(authenticationResponse);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<Object>(new ApiResponse(false, "Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<Object>(new ApiResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("User Role not set."));

		user.setRoles(Collections.singleton(userRole));

		User result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
}

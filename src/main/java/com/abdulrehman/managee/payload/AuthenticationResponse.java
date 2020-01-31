package com.abdulrehman.managee.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 25, 2020
 */
@Getter
@Setter
public class AuthenticationResponse {
	private String name;
	private String email;
	private String username;
	private List<String> role;
	private String token;
	private String type = "Bearer";

	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(String name, String email, String username, List<String> role, String token,
			String type) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.role = role;
		this.token = token;
		this.type = type;
	}
}

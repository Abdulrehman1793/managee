package com.abdulrehman.managee.payload.response;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 3, 2020
 */
public class DeleteResponse {

	private String message;

	public DeleteResponse(String message) {
		super();
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}

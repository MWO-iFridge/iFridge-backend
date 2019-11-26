package mwo.auth;


import java.io.Serializable;

public class TokenResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String token;
	private final String username;
	
	public TokenResponse(String token, String username) {
		this.token = token;
		this.username = username;
	}
	
	public String getToken() {
		return this.token;
	}

	public String getUsername() {
		return this.username;
	}
}
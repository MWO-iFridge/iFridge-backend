package mwo.auth;


import java.io.Serializable;

public class TokenResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String auth_token;
	private final String username;
	
	public TokenResponse(String auth_token, String username) {
		this.auth_token = auth_token;
		this.username = username;
	}
	
	public String getAuth_token() {
		return this.auth_token;
	}

	public String getUsername() {
		return this.username;
	}
}
package mwo.profile_edit;

import java.io.Serializable;

public class RegisterResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private final String code;
	private final String message;
	
	public RegisterResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}
}

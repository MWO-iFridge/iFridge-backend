package mwo.profile_edit;


public class NewUserDTO {

	private String username;
	private String password;
	private String email;
	private String phone;
	private String name;
	private String surname;
	
	public void set(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public boolean isEmpty() {
		if(name == null || name.isEmpty()) {
			return true;
		}
		if(surname == null || surname.isEmpty()) {
			return true;
		}
		if(username == null || username.isEmpty()) {
			return true;
		}
		if(password == null || password.isEmpty()) {
			return true;
		}
		if(email == null || email.isEmpty()) {
			return true;
		}
		if(phone == null || phone.isEmpty()) {
			return true;
		}
		
		return false;
	}
}

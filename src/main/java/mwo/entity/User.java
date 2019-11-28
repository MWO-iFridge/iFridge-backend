package mwo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;
	@Column
	@JsonIgnore
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

}
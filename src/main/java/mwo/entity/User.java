package mwo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access .WRITE_ONLY)
	private String password;
	@Column
	private String name;
	@Column 
	private String surname;
	@Column
	private float weight;
	@Column
	private float height;
	@Column
	private boolean male;
	@Column
	private int diet;

	public void setWeight(float weight){
		this.weight = weight;
	}

	public void setHeight(float height){
		this.height = height;
	}

	public void setDiet(int diet){
		this.diet = diet;
	}

	public void setMale(boolean male){
		this.male = male;
	}

	public void setPassword(String password){
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public String getName(){
		return name;
	}

	public String getSurname(){
		return surname;
	}

	public float getWeight(){
		return weight;
	}

	public float getHeight(){
		return height;
	}

	public boolean getMale(){
		return male;
	}
	public int getDiet(){
		return diet;
	}
}
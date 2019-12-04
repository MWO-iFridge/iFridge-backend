package mwo.service;


import mwo.entity.User;
import mwo.profile_edit.NewUserDTO;
import mwo.repository.UserRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	   private UserRepository userRepository;
	   
	    @Autowired
		private PasswordEncoder bcryptEncoder;


	    public UserService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    public User getUserByUsername(String username){
	        return userRepository.findByUsername(username);
		}
		
		public void addUser(User user){
			User oldUser = getUserByUsername(user.getUsername());
			oldUser.setWeight(user.getWeight());
			oldUser.setHeight(user.getHeight());
			oldUser.setDiet(user.getDiet());
			oldUser.setMale(user.getMale());
			userRepository.save(oldUser);
		}
		
		public User newUser(NewUserDTO data) {
			User user = new User();
			user.setUsername(data.getUsername());
			user.setPassword(bcryptEncoder.encode(data.getPassword()));
			user.setName(data.getName());
			user.setSurname(data.getSurname());
			// some defaults (or can be left blank?)
			user.setDiet(1);
			user.setHeight(0);
			user.setWeight(0);
			user.setMale(true);
			// if we want to extend user with email and phone
			//user.setEmail(data.getEmail());
			//user.setPhone(data.getPhone());
			
			return userRepository.save(user);
		}
	        
}

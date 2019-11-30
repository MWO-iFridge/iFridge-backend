package mwo.service;


import mwo.entity.User;
import mwo.repository.UserRepository;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	   private UserRepository userRepository;

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
	        
}

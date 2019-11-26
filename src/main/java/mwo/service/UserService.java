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
	    
	   // public Optional<User> getUserById(Long id){
	 //       return userRepository.findById(id);
	   // }
	    
	    public User getUserByUsername(String username){
	        return userRepository.findByUsername(username);
	    }
	        
}

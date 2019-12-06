package mwo.controller.rest;

import mwo.entity.User;
import mwo.profile_edit.NewPasswordForm;
import mwo.profile_edit.NewUserDTO;
import mwo.profile_edit.RegisterResponse;
import mwo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public User getProfileInfo(@RequestBody String username){
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "/save-profile", method = RequestMethod.POST)
    public void saveProfile(@RequestBody User newUserData){
        userService.addUser(newUserData);
    }

    @RequestMapping(value = "/check-password", method = RequestMethod.POST)
    public boolean checkPassword(@RequestBody NewPasswordForm passwordForm){
        User user = userService.getUserByUsername(passwordForm.getUsername());
        String databaseUserPassword = user.getPassword();
        String oldPassword = passwordForm.getOldPassword();
        boolean isPasswordCorrect = databaseUserPassword.equals(oldPassword);
        if(isPasswordCorrect){
            user.setPassword(passwordForm.getNewPassword());
            userService.addUser(user);
        }    
        return isPasswordCorrect;
    }
    
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public RegisterResponse newUser(@RequestBody NewUserDTO newUser) {
		
		if (userService.getUserByUsername(newUser.getUsername()) != null) {
			return new RegisterResponse("0","Username taken. Choose a different one.");
		}
	
		if (newUser.isEmpty()) {
			return new RegisterResponse("0","A field is empty. Make sure to fill in all boxes.");
		}
		
		User user = userService.newUser(newUser);
		
		if (user == null) {
			return new RegisterResponse("0","Server failed while creating a user.");
		}else {
			return new RegisterResponse("1","User created successfully.");
		}
	}


}
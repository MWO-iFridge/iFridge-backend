package mwo.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mwo.auth.AuthRequest;
import mwo.entity.User;
import mwo.profile_edit.NewPasswordForm;
import mwo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public User getProfileInfo(@RequestBody String username){
        System.out.println(userService.getUserByUsername(username).getPassword());
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

}
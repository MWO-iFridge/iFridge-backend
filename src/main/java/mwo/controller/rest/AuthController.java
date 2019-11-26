package mwo.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mwo.auth.AuthRequest;
import mwo.auth.TokenHelper;
import mwo.auth.TokenResponse;
import mwo.entity.User;
import mwo.service.UserService;


@RestController
//@CrossOrigin
public class AuthController {

	@Autowired
	private TokenHelper tokenHelper;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping({ "/hidden" })
	public String firstPage() {
		return "Hello World";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public TokenResponse createAuthenticationToken(@RequestBody AuthRequest authRequest){
		// debug
		System.out.println("User authentication "+ authRequest.getUsername() + " " +  authRequest.getPassword());
		
		Boolean auth = authenticate(authRequest.getUsername(), authRequest.getPassword());
		User user = userService.getUserByUsername(authRequest.getUsername());
		String token = "";
	    String username = "";
		if (auth) {
			System.out.println("User properly authenticated.");
			token = tokenHelper.generateToken(user);
			username =  user.getUsername();
		}
		System.out.println("token granted: "+token);
		return new TokenResponse(token,username);
	}

	private Boolean authenticate(String username, String password) { // configure authentication manager
		User user = userService.getUserByUsername(username);
		System.out.println("Credentials provided:"+ username+ " "+ password);
		System.out.println("Credentials in db:"+user.getUsername() + " " +user.getPassword());
		if (password.equals(user.getPassword())) {
			return true;
		}else {
			return false;
		}

	}
}

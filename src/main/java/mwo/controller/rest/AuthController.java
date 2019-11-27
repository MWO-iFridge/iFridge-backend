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
	
		String username = authenticate(authRequest.getUsername(), authRequest.getPassword());
		String token = "";
	    
		if (username != null && !username.isEmpty()) {
			token = tokenHelper.generateToken(username);
		}
		
		return new TokenResponse(token,username);
	}

	private String authenticate(String username, String password) { // configure authentication manager
		User user = userService.getUserByUsername(username);
		
	    if (user == null) {
	    	return "";
	    }
	    
		if (password.equals(user.getPassword())) {
			return user.getUsername();
		}else {
			return "";
		}

	}
}

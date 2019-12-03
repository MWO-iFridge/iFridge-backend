package mwo.controller.rest;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mwo.auth.AuthRequest;
import mwo.auth.TokenHelper;
import mwo.auth.TokenResponse;
import mwo.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	private TokenHelper tokenHelper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value="/hidden", method=RequestMethod.GET, produces="text/plain")
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

	private String authenticate(String username, String password) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return username;
		} catch (DisabledException e) {
			return "";
		} catch (BadCredentialsException e) {
			return "";
		}

	}
	/*
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
	*/
}

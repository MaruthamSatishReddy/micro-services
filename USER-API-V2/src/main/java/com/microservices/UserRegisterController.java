package com.microservices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserRegisterController {
@Autowired
public UserRepository userRepository;

@Value("${config.value}")
private String configValue;

	@PostMapping("/signUp")
	public ResponseEntity<User> userSignUp(@RequestBody User user) {
		User userDetails = userRepository.insert(user);
		return new ResponseEntity<User>(userDetails,HttpStatus.CREATED);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/signIn/{userName}/{password}")
	public Optional<User> userSignIn(@PathVariable("userName") String userName,@PathVariable("password") String password) {
    Optional<User> userDetails = userRepository.findByUserNameAndUserPassword(userName,password);
    return userDetails;
	}
	@GetMapping("/configServer")
	public String testConfigServer() {
		return configValue;
	}
}

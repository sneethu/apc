package com.ingg.appaunthentication.rest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingg.appaunthentication.domain.User;
import com.ingg.appaunthentication.model.JwtUser;
import com.ingg.appaunthentication.repository.CustomSequencesRepository;
import com.ingg.appaunthentication.repository.UserRepository;
import com.ingg.appaunthentication.security.JwtGenerator;
import com.ingg.appaunthentication.util.UserConverter;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	private final JwtGenerator jwtGenerator;
	private final UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	public UserController(UserRepository userRepository, JwtGenerator jwtGenerator) {
		this.userRepository = userRepository;
		this.jwtGenerator = jwtGenerator;
	}
	
	
	@GetMapping("/login")
	public ResponseEntity<String> processLogin(@RequestBody final JwtUser jwtUser){
		if (jwtUser.getUserName() == null || jwtUser.getPassword() == null)
			return new ResponseEntity<String>("Please, introduce your username and password",HttpStatus.BAD_REQUEST);
		
		User userDB = userRepository.findByUsername(jwtUser.getUserName());
		if (userDB != null) {
			if (BCrypt.checkpw(jwtUser.getPassword(), userDB.getPassword())){
				return new ResponseEntity<String>(jwtGenerator.generate(jwtUser),HttpStatus.FOUND);
			}
			return new ResponseEntity<String>("Password is incorrect", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("User does not exist on DB. Please, introduce a valid username",HttpStatus.BAD_REQUEST);

	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody final JwtUser jwtUser ) {
		if (jwtUser.getUserName() == null || jwtUser.getPassword() == null)
			return new ResponseEntity<String>("Please, introduce your username and password",HttpStatus.BAD_REQUEST);
		User userDB = userRepository.findByUsername(jwtUser.getUserName());
		if (userDB != null)
			return new ResponseEntity<String>("User already exists. Please try different username.",HttpStatus.UNAUTHORIZED);
		User userData = userConverter.convertJwtUserToUser(jwtUser);
		userRepository.save(userData);		
		return new ResponseEntity<String>("User saved on DB",HttpStatus.CREATED);
	}
	
	
}

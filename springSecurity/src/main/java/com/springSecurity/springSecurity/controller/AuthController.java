package com.springSecurity.springSecurity.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurity.springSecurity.entity.User;
import com.springSecurity.springSecurity.repo.UserRepository;
import com.springSecurity.springSecurity.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;
	private final UserRepository userRepository;
	
	@PostMapping("/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body) {
		String email = body.get("email");
		String password = body.get("password");
		if(userRepository.findByEmail(email).isPresent()) {
			return new ResponseEntity<>("email already exists",HttpStatus.CONFLICT);
			
		}
		userService.createUser(User.builder().email(email).password(password).build());
		return new ResponseEntity<>("Successfully saved",HttpStatus.CONFLICT);
	}
	 
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Map<String,String> body) {
		String email = body.get("email");
		String password = body.get("password");
		var userOptional = userRepository.findByEmail(email);
		if(userOptional.isEmpty()) {
			return new ResponseEntity<>("User  Not Register",HttpStatus.UNAUTHORIZED);
		}
		
		return null;
	}
	

}

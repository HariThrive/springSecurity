package com.springSecurity.springSecurity.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurity.springSecurity.entity.User;
import com.springSecurity.springSecurity.repo.UserRepository;
import com.springSecurity.springSecurity.service.UserService;
import com.springSecurity.springSecurity.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	private final UserService userService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil; 
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body) {
		String email = body.get("email");
		String password = body.get("password");
		password = passwordEncoder.encode(password);
		if(userRepository.findByEmail(email).isPresent()) {
			return new ResponseEntity<>("email already exists",HttpStatus.CONFLICT);
			
		}
		userService.createUser(User.builder().email(email).password(password).build());
		return new ResponseEntity<>("Successfully saved",HttpStatus.CREATED);
	}
	
	 
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body) {
		String email = body.get("email");
		String password = body.get("password");
		var userOptional = userRepository.findByEmail(email);
		if(userOptional.isEmpty()) {
			return new ResponseEntity<>("User  Not Register",HttpStatus.UNAUTHORIZED);
		}
		 
		User user = userOptional.get();
		if(!passwordEncoder.matches(password,user.getPassword())) {
			return new ResponseEntity<>("Invalid User",HttpStatus.UNAUTHORIZED);
			
		}
		String token = jwtUtil.generateToken(email);
		return ResponseEntity.ok(Map.of("token",token));
	}
	

}

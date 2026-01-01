package com.springSecurity.springSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springSecurity.springSecurity.entity.User;
import com.springSecurity.springSecurity.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	public void createUser(User build) {
		userRepository.save(build);
		
	}

}

package com.polarisfinder.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.user.dao.UserDAO;
import com.polarisfinder.user.entity.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public synchronized boolean createUser(User user){
		return userDAO.createUser(user);
	}
	
	@Override
	public User findByUserName(String username) {
		return userDAO.findByUserName(username);
	}
	/*
	public void saveUser(User user){
	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	user.setActive(1);
	Role userRole = roleRepository.findByRole("ADMIN");
	user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	userRepository.save(user);
	*/
}

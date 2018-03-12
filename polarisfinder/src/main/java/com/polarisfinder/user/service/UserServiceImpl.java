package com.polarisfinder.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.user.dao.UserDAO;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.cubemap.dao.CubemapDAO;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public synchronized boolean createUser(User user){
		return userDAO.createUser(user);
	}
	
	@Override
	public User findUserByEmail(String email) {
		return userDAO.findUserByEmail(email);
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

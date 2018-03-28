package com.polarisfinder.user.dao;

import com.polarisfinder.user.entity.User;

public interface UserDAO {
	boolean createUser(User user);
	User findById(int id);
	User findByUserName(String username);
	User findByOauthid(String type, String oauthid);
	
	
}

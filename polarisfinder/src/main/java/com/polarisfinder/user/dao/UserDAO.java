package com.polarisfinder.user.dao;

import com.polarisfinder.user.entity.User;

public interface UserDAO {
	boolean createUser(User user);
	User findByUserName(String username);
}

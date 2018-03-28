package com.polarisfinder.user.service;

import com.polarisfinder.user.entity.User;

public interface UserService {

    boolean createUser(User user);
    User findByUserName(String username);
    User findById(int id);
    User findByOauthid(String type, String oauthid);
    
    
}

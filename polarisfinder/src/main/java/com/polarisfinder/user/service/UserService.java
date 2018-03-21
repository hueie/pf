package com.polarisfinder.user.service;

import com.polarisfinder.user.entity.User;

public interface UserService {

    boolean createUser(User user);
    User findByUserName(String username);
    User findByOauthid(String type, String oauthid);
    
    
}

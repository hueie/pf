package com.polarisfinder.user.service;

import java.util.List;

import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;

public interface UserService {

    boolean createUser(User user);
    User findUserByEmail(String email);
}

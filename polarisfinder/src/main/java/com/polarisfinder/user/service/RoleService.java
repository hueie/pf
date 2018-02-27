package com.polarisfinder.user.service;

import java.util.List;

import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;
import com.polarisfinder.user.entity.Role;

public interface RoleService {

    boolean createRole(Role role);
    Role findByRole(String role);
}

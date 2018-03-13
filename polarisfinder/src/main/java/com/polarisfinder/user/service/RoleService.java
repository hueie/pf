package com.polarisfinder.user.service;

import com.polarisfinder.user.entity.Role;

public interface RoleService {

    boolean createRole(Role role);
    Role findByRole(String role);
}

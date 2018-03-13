package com.polarisfinder.user.dao;

import com.polarisfinder.user.entity.Role;

public interface RoleDAO {
	void createRole(Role role);
	Role findByRole(String role);
}

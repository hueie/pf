package com.polarisfinder.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.atestpage.entity.Article;
import com.polarisfinder.cubemap.dao.CubemapDAO;
import com.polarisfinder.cubemap.entity.Cubemap;
import com.polarisfinder.cubemap.entity.Stack;
import com.polarisfinder.user.dao.RoleDAO;
import com.polarisfinder.user.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public synchronized boolean createRole(Role role){
		roleDAO.createRole(role);
		return true;
	}
	
	@Override
	public Role findByRole(String role) {
		return roleDAO.findByRole(role);
	}
	
}

package com.polarisfinder.user.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.polarisfinder.user.dao.UserDAO;
import com.polarisfinder.user.entity.CurrentUser;
import com.polarisfinder.user.entity.Role;
import com.polarisfinder.user.entity.User;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		System.out.println("Load User By Username : "+username);
		User user = userDAO.findByUserName(username);
		System.out.println("1234");
		if (user == null) {
			System.out.println("1234");
			throw new UsernameNotFoundException(username);
        }
		System.out.println("user.getRoles() : "+user.getRoles());
		// Build user Authority. some how a convert from your custom roles which
		// are in database to spring GrantedAuthority
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

		// The magic is happen in this private method !
		return buildUserForAuthentication(user, authorities);
	}
	
	//Fill your extended User object (CurrentUser) here and return it
	private CurrentUser buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	    String username = user.getUsername();
	    String password = user.getPassword();
	    boolean enabled = true;
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
	    
	    CurrentUser currentUser = new CurrentUser(username, password, enabled, accountNonExpired, credentialsNonExpired,
	            accountNonLocked, authorities);
	    currentUser.setNickname(user.getNickname());
	    currentUser.setUser_id(user.getUser_id());
	    return currentUser;
	}

	private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
	    // Build user's authorities
	    for (Role userRole : userRoles) {
	        setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
	    }
	    return new ArrayList<GrantedAuthority>(setAuths);
	}
	
}

package com.polarisfinder.user.entity;

import java.util.Collection;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

public class CurrentUser extends User {

	// This constructor is a must
	public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	// Setter and getters are required
	private String nickname;
	private int user_id;
	
	public final String getNickname() {
		return nickname;
	}

	public final void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public final int getUser_id() {
		return user_id;
	}

	public final void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
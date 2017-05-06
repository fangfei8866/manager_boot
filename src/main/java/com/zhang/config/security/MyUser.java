package com.zhang.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser<T> extends User {

	private Integer id;
	private T currentUser;

	public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer id,
			T currentUser) {
		super(username, password, authorities);
		this.currentUser = currentUser;
		this.id = id;
	}
	
	

	public MyUser(String username, String password, boolean enabled,Collection<? extends GrantedAuthority> authorities,Integer id,T currentUser) {
		super(username, password, enabled, true, true, true, authorities);
		this.currentUser = currentUser;
		this.id = id;
	}



	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public T getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(T currentUser) {
		this.currentUser = currentUser;
	}

}

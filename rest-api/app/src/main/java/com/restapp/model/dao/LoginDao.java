package com.restapp.model.dao;

import com.restapp.model.entities.User;

public interface LoginDao {
	
	public boolean insert(User usuario);
	public User validaUsuario(String user, String password);
	public String validaRoleUser(String user);
}
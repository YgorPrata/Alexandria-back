package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arte;

public interface ArteDao {
	
	Arte insert(Arte art, String arqnome);
	List<Arte> findAll();
	List<Arte> findByName(String autor);
}

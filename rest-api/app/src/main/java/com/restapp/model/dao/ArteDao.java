package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arte;

public interface ArteDao {
	
	Arte insert(Arte art);
	Arte update(Arte art);
	Arte deleteById(Integer id);
	Arte findById(Integer id);
	List<Arte> findAll();
	List<Arte> findByName(String autor);
}

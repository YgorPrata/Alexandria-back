package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Artigos;

public interface ArtigosDao {
	
	Artigos insert(Artigos art);
	Artigos update(Artigos art);
	Artigos deleteById(Integer id);
	Artigos findById(Integer id);
	List<Artigos> findByName(String autor);
	List<Artigos> findAll();
	
}

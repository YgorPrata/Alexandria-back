package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Livro;

public interface LivroDao {
	
	Livro insert(Livro art);
	Livro update(Livro art);
	Livro deleteById(Integer id);
	Livro findById(Integer id);
	List<Livro> findByName(String autor);
	List<Livro> findAll();
}

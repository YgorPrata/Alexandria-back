package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Livro;

public interface LivroDao {
	
	Livro insert(Livro livro, String tituloarq);
	Livro update(Livro livro);
	List<Livro> findByName(String autor);
	List<Livro> findAll();
}

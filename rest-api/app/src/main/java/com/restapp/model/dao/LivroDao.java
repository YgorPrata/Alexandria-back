package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Livro;

public interface LivroDao {
	
	List<Livro> getAll();
	Livro getById(Integer id_livro);	
	List<Livro> getLivroTipo(String titulo, String autor, String localidade, Integer limit);
	List<Livro> getLivroCategoria(String query, Integer limit);
}

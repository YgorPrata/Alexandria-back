package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Txt;

public interface LivroDao {
	
	boolean insert(Livro livro, List<Img> img, Txt txt);
	List<Livro> getAll();
	Livro getById(Integer id_livro);	
	List<Livro> getLivroTipo(String titulo, String autor, String localidade, Integer limit);
	List<Livro> getLivroCategoria(String query, Integer limit);
}

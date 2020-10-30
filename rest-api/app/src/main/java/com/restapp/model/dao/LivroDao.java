package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Txt;

public interface LivroDao {
	
	boolean insert(Livro livro, List<Img> img, Txt txt);
	Livro getById(Integer id_arq);
	List<Livro> getAll();
	List<Livro> getArqSimp(List<String> query);
}

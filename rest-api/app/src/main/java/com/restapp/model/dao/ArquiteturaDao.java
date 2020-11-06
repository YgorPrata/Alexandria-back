package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Txt;

public interface ArquiteturaDao {
		
	boolean insert(Arquitetura arq, List<Img> img, Txt txt);
	List<Arquitetura> getAll();
	Arquitetura getById(Integer id_arq);
	List<Arquitetura> getArqTipo(String titulo, String autor, String localidade, Integer limit);
	List<Arquitetura> getArqCategoria(String query, Integer limit);
}

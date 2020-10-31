package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Produto;
import com.restapp.model.entities.Txt;

public interface ArquiteturaDao {
		
	boolean insert(Arquitetura arq, List<Img> img, Txt txt);
	Arquitetura getById(Integer id_arq);
	List<Arquitetura> getAll();
	List<Arquitetura> getArqTipo(String titulo, String autor, String localidade, String limit);
	List<Arquitetura> getArqCategoria(String query, String limit);
	List<Arquitetura> getNovidade();
}

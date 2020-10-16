package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Txt;

public interface ArquiteturaDao {
	
	//generic DAO
	
	boolean insert(Arquitetura arq, List<Img> img, Txt txt);
	List<Arquitetura> getById(Integer id_arq);
	List<Arquitetura> getAll();
	List<Img> getArqSimpTitulo(String titulo);
	List<Arquitetura> getArqSimpAutor(String autor);
	List<Arquitetura> getArqSimpLocal(String local);

}

package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arquitetura;

public interface ArquiteturaDao {
	
	boolean insert(Arquitetura arq, List<String> arqpath, List<String> descricao, String arqtxt);
	List<Arquitetura> findByName(String autor);
	List<Arquitetura> findAll();
}

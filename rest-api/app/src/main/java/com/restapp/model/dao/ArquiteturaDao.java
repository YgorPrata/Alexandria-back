package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arquitetura;

public interface ArquiteturaDao {
	
	Arquitetura insert(Arquitetura art);
	List<Arquitetura> findByName(String autor);
	List<Arquitetura> findAll();
}

package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arte;

public interface ArteDao {
	
	List<Arte> getAll();
	Arte getById(Integer id_arte);
	List<Arte> getArteTipo(String titulo, String autor, String localidade, Integer limit);
	List<Arte> getArteCategoria(String query, Integer limit);
}

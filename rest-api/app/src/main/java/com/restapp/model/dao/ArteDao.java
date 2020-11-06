package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arte;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Txt;

public interface ArteDao {
	
	public boolean insert(Arte arte, List<Img> list, Txt txt);
	List<Arte> getAll();
	Arte getById(Integer id_arte);
	List<Arte> getArteTipo(String titulo, String autor, String localidade, Integer limit);
	List<Arte> getArteCategoria(String query, Integer limit);
}

package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Produto;

public interface ProdutoDao {

	List<Produto> getProdNoFiltro(String query, Integer limit);
	List<Produto> getNovidades(Integer limit);
	List<Produto> getProdTipo(String titulo, String autor, String localidade, Integer limit);
	Integer getProdCount();

}

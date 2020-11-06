package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Produto;

public interface ProdutoDao {

	public List<Produto> getProdNoFiltro(String query, Integer limit);
	public List<Produto> getNovidades(Integer limit);
	public List<Produto> getProdTipo(String titulo, String autor, String localidade, Integer limit);
	public Integer getProdCount();

}

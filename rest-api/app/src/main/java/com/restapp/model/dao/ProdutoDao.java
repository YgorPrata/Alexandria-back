package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Produto;

public interface ProdutoDao {

	public List<Produto> getProdNoFiltro(String query, String limit);
}

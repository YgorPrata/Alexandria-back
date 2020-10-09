package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arquitetura;

public interface ArquiteturaDao {
	
	boolean insert(Arquitetura arq, List<String> arqpath, List<String> descricao, String arqtxt);
	List<Arquitetura> getById(Integer id_arq);
	List<Arquitetura> getAll();
	List<Arquitetura> getArqSimpTitulo(String titulo);
	List<Arquitetura> getArqSimpAutor(String autor);
	List<Arquitetura> getArqSimpLocal(String local);

}

package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Arte;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Produto;

public interface UserDao {
	
	boolean insertArq(Arquitetura arq, List<Img> img);
	boolean insertArte(Arte arte, List<Img> list);
	boolean insertLivro(Livro livro, List<Img> img);
	List<Produto> displayUserProdsSimp(Integer id_user);
	Produto getProdById(Integer id_user, Integer id_prod);
	Produto updateUserArqProd(Integer id_user, Arquitetura arq);
	boolean deleteUserProd(Integer id_prod);
}

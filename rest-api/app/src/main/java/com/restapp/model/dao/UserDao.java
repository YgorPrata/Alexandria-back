package com.restapp.model.dao;

import java.util.List;

import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Arte;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Produto;

public interface UserDao {
	
	boolean insertArq(Arquitetura arq, Integer id_user);
	boolean insertArte(Arte arte, Integer id_user);
	boolean insertLivro(Livro livro, Integer id_user);
	List<Produto> displayUserProdsSimp(Integer id_user);
	Produto getProdById(Integer id_user, Integer id_prod);
	boolean checkExistProd(String query);
	boolean updateUserArqProd(Arquitetura arq, Integer id_user);
	boolean updateUserArteProd(Arte arte, Integer id_user);
	boolean updateUserLivroProd(Livro livro, Integer id_user);
	boolean addNewImg(Produto prod);
	boolean deleteUserProd(Integer id_prod);
	
	

}

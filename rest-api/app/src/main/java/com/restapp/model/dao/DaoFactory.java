package com.restapp.model.dao;

import com.restapp.db.DB;
import com.restapp.model.dao.impl.ArquiteturaDaoJDBC;
import com.restapp.model.dao.impl.ArteDaoJDBC;
import com.restapp.model.dao.impl.LivroDaoJDBC;
import com.restapp.model.dao.impl.ProdutoDaoJDBC;

public class DaoFactory {
	
	public static ArquiteturaDao criarArquitetura() {
		return new ArquiteturaDaoJDBC(DB.getConnection());
	}
	
	public static ArteDao criarArte() {
		return new ArteDaoJDBC(DB.getConnection());
	}
	
	public static LivroDao criarLivro() {
		return new LivroDaoJDBC(DB.getConnection());
	}
	
	public static ProdutoDao criarProduto() {
		return new ProdutoDaoJDBC(DB.getConnection());
	}
}

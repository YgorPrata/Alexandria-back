package com.restapp.model.dao;

import com.restapp.db.DB;
import com.restapp.model.dao.impl.ArquiteturaDaoJDBC;

public class DaoFactory {
	
	public static ArquiteturaDao criarArquitetura() {
		return new ArquiteturaDaoJDBC(DB.getConnection());
	}
	
}

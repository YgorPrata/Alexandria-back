package com.restapp.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.restapp.model.dao.impl.LivroDaoJDBC;
import com.restapp.model.entities.Artigos;
import com.restapp.model.entities.Livro;

public class LivroResource {
	
	LivroDaoJDBC livrodao = new LivroDaoJDBC();
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Livro insert(Livro livro, String tituloarq) {
		System.out.println("criando novo livro...");
		return livrodao.insert(livro, tituloarq);
	}
}

package com.restapp.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.restapp.db.DbException;
import com.restapp.model.dao.impl.ArteDaoJDBC;
import com.restapp.model.entities.Arte;
import com.restapp.model.entities.Artigos;

@Path("produto")
public class ArteResource {
	
	ArteDaoJDBC artedao = new ArteDaoJDBC();
	
	@GET @Path("/arte")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Arte> findAll() throws Exception {		
		System.out.println("findAll");
		return artedao.findAll();
	}
	
	@GET @Path("/arte/busca/{query}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Arte> findByName(@PathParam("query") String query){
		System.out.println("findByName: " + query);
		return artedao.findByName(query);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Arte insert(Arte arte, @PathParam("nomearq") String nomearq) {
		System.out.println("criando artigo");
		return artedao.insert(arte, nomearq);
	}
		
}

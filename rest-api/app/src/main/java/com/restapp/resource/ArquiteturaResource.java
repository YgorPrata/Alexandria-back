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
import com.restapp.model.dao.impl.ArquiteturaDaoJDBC;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Arquitetura;

@Path("/produto")
public class ArquiteturaResource {
	
	ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
	
	@GET @Path("/arquitetura")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Arquitetura> findAll() throws Exception {		
		System.out.println("findAll");
		return arqdao.findAll();
	}
	
	@GET @Path("/arquitetura/busca/{query}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Arquitetura> findByName(@PathParam("query") String query){
		System.out.println("findByName: " + query);
		return arqdao.findByName(query);
	}
	
	@POST @Path("/cadastro")
	@Consumes({MediaType.APPLICATION_JSON})
	public Arquitetura insert(Arquitetura arq) {
		return arqdao.insert(arq);
	}
	
}

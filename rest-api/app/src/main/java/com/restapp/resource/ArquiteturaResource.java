package com.restapp.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.restapp.db.DbException;
import com.restapp.model.dao.impl.ArquiteturaDaoJDBC;
import com.restapp.model.entities.Arquitetura;

@Path("/produto")
public class ArquiteturaResource {
	
	ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
	
	@GET @Path("/arquitetura")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() throws Exception {		
		if(arqdao.findAll().size() > 0) {
			return Response.ok().entity(arqdao.findAll()).build();
		}
		else {
			return Response.status(404).entity("Não há nenhum registro para essa categoria").build();
		}
	}
	
	@GET @Path("/arquitetura/busca/{query}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findByName(@PathParam("query") String query){
		if(arqdao.findByName(query).size() > 0) {
			return Response.ok().entity(arqdao.findByName(query)).build();
		}
		else {
			return Response.status(404).entity("Não há nenhum registro com esse nome.").build();
		}
		
	}
	
	@POST @Path("arquitetura/cadastro")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response insert(Arquitetura arq) {		
		try {
			boolean cadsalvo = new ArquiteturaDaoJDBC().insert(arq);
			if(cadsalvo) {
				return Response.ok().entity(arq).build();
			}
			else {
				//se o cadastro não for salvo
				return Response.status(500).entity("Ops! Ocorreu um erro ao salvar o cadastro no banco.").build();
			}
		}
		catch (DbException e) {
			e.printStackTrace();
			return Response.status(500).entity("Ops! Parece que o banco de dados está com um problema.").build();
		}
	}
	
}

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
import com.restapp.model.dao.impl.ArtigosDaoJDBC;
import com.restapp.model.entities.Artigos;

@Path("/artigos")
public class ArtigosResource {
	
	ArtigosDaoJDBC artigos = new ArtigosDaoJDBC();
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Artigos> obterArtigos() throws Exception {		
		List<Artigos> artigos = null;
		try {
			artigos = new ArtigosDaoJDBC().findAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return artigos != null? artigos: new ArrayList<Artigos>();
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Artigos> findAll() throws Exception {		
		System.out.println("findAll");
		return artigos.findAll();
	}
	
	@GET @Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Artigos findById(@PathParam("id") String id) {
		System.out.println("findById " + id);
		return artigos.findById(Integer.parseInt(id));
	}
	
	@GET @Path("search/{query}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Artigos> findByName(@PathParam("query") String query){
		System.out.println("findByName: " + query);
		return artigos.findByName(query);
	}
	
	@GET @Path("dsearch/{query}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Artigos> dynamicSearch(@PathParam("query") String query){
		System.out.println("dynamicSearch: " + query);
		return artigos.dynamicSearch(query);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Artigos insert(Artigos art) {
		System.out.println("criando artigo");
		return artigos.insert(art);
	}
	
	
}

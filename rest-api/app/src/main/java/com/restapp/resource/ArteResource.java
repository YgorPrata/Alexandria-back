package com.restapp.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




import com.restapp.model.dao.ArteDao;
import com.restapp.model.dao.DaoFactory;


import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;


@Path("/produto")
@Api("/Arte Service")
public class ArteResource {
	
	ArteDao artedao = DaoFactory.criarArte();
	
	@GET
	@Path("arte/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws Exception {
		if (artedao.getAll().size() > 0) {
			return Response.status(200).entity(artedao.getAll()).build();
		} else {
			return Response.status(200).entity("Não há nenhum registro para essa categoria").build();
		}
	}
		
	@GET
	@Path("arte/tipo/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getArteSimpFiltro(@QueryParam(value = "titulo") String titulo, @QueryParam(value = "autor") String autor, 
			@QueryParam(value = "localidade") String localidade, @QueryParam(value = "limite") Integer limit) {
		if (artedao.getArteTipo(titulo, autor, localidade, limit).size() > 0) {
			return Response.status(200).entity(artedao.getArteTipo(titulo, autor, localidade, limit)).build();			
		}
		else if(artedao.getArteTipo(titulo, autor, localidade, limit).size() <= 0) {
			return Response.status(200).entity("Não há nenhum registro com esse termo.").build();
		}
		else {
			return Response.status(500).entity("Erro no banco").build();
		}	
	}
	
	@GET
	@Path("arte/categoria/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getArteCategoria(@QueryParam(value = "query") String query, @QueryParam(value = "limite") Integer limit) {
		if (artedao.getArteCategoria(query, limit).size() > 0) {
			return Response.status(200).entity(artedao.getArteCategoria(query, limit)).build();			
		}
		else if(artedao.getArteCategoria(query, limit).size() <= 0) {
			return Response.status(200).entity("Não há nenhum registro com esse termo.").build();
		}
		else {
			return Response.status(500).entity("Erro no banco").build();
		}	
	}
		
	@GET
	@Path("arte/buscacompleta/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getById(@QueryParam(value = "id") Integer id_prod) {
		if (artedao.getById(id_prod) != null) {
			return Response.status(200).entity(artedao.getById(id_prod)).build();			
		}
		else {			
			return Response.status(500).entity("Erro no banco de dados").build();
		}
		
			
	}
	
	
		
}

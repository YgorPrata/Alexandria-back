package com.restapp.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.restapp.model.dao.DaoFactory;
import com.restapp.model.dao.ProdutoDao;


@Path("/produto")
public class ProdutoResource {
	
	ProdutoDao proddao = DaoFactory.criarProduto();
	
	@GET
	@Path("nofilter/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdNoFiltro(@QueryParam(value = "query") String query, @QueryParam(value = "limite") Integer limit) throws Exception {
		if (proddao.getProdNoFiltro(query, limit).size() > 0) {
			return Response.status(200).entity(proddao.getProdNoFiltro(query, limit)).build();
		}
		else if (proddao.getProdNoFiltro(query, limit).size() <= 0){
			return Response.status(200).entity("Não há registros com esse termo").build();
		}
		else {
			return Response.status(200).entity("Não há nenhum registro para essa categoria").build();
		}
	}
	
	@GET
	@Path("tipo/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdTipo(@QueryParam(value = "titulo") String titulo, @QueryParam(value = "autor") String autor, 
			@QueryParam(value = "localidade") String localidade, @QueryParam(value = "limite") Integer limit) throws Exception {
		if (proddao.getProdTipo(titulo, autor, localidade, limit).size() > 0) {
			return Response.status(200).entity(proddao.getProdTipo(titulo, autor, localidade, limit)).build();
		}
		else if (proddao.getProdTipo(titulo, autor, localidade, limit).size() <= 0){
			return Response.status(200).entity("Não há registros com esse termo").build();
		}
		else {
			return Response.status(200).entity("Não há nenhum registro para essa categoria").build();
		}
	}
	
	@GET
	@Path("novidades/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNovidade(@QueryParam(value = "limite") Integer limit) throws Exception {
		if (proddao.getNovidades(limit).size() > 0) {
			return Response.status(200).entity(proddao.getNovidades(limit)).build();
		} 
		else if (proddao.getNovidades(limit).size() <= 0) {
			return Response.status(200).entity("Não há nenhum registro para essa categoria").build();
		}
		else {
			return Response.status(500).entity("Erro no banco").build();
		}
	}
	
	@GET
	@Path("count/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdCount(@QueryParam(value = "limite") Integer limit) throws Exception {
		if (proddao.getProdCount() != 0) {
			return Response.status(200).entity(proddao.getProdCount()).build();
		} 
		else if (proddao.getProdCount() <= 0) {
			return Response.status(200).entity("Não há nenhum registro.").build();
		}
		else {
			return Response.status(500).entity("Erro no banco").build();
		}
	}
	
	
}

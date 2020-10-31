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
	public Response getProdNoFiltro(@QueryParam(value = "query") String query, @QueryParam(value = "limite") String limit) throws Exception {
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
	
}

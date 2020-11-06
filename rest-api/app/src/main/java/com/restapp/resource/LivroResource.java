package com.restapp.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.restapp.model.dao.DaoFactory;
import com.restapp.model.dao.LivroDao;

@Path("/produto")
public class LivroResource {
	
	LivroDao livrodao = DaoFactory.criarLivro();

	@GET
	@Path("livro/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws Exception {
		if (livrodao.getAll().size() > 0) {
			return Response.status(200).entity(livrodao.getAll()).build();
		} else {
			return Response.status(200).entity("Não há nenhum registro para essa categoria").build();
		}
	}
		
	@GET
	@Path("livro/tipo/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getLivroTipo(@QueryParam(value = "titulo") String titulo, @QueryParam(value = "autor") String autor, 
			@QueryParam(value = "localidade") String localidade, @QueryParam(value = "limite") Integer limit) {
		if (livrodao.getLivroTipo(titulo, autor, localidade, limit).size() > 0) {
			return Response.status(200).entity(livrodao.getLivroTipo(titulo, autor, localidade, limit)).build();			
		}
		else if(livrodao.getLivroTipo(titulo, autor, localidade, limit).size() <= 0) {
			return Response.status(200).entity("Não há nenhum registro com esse termo.").build();
		}
		else {
			return Response.status(500).entity("Erro no banco").build();
		}	
	}
	
	@GET
	@Path("livro/categoria/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getLivroCategoria(@QueryParam(value = "query") String query, @QueryParam(value = "limite") Integer limit) {
		if (livrodao.getLivroCategoria(query, limit).size() > 0) {
			return Response.status(200).entity(livrodao.getLivroCategoria(query, limit)).build();			
		}
		else if(livrodao.getLivroCategoria(query, limit).size() <= 0) {
			return Response.status(200).entity("Não há nenhum registro com esse termo.").build();
		}
		else {
			return Response.status(500).entity("Erro no banco").build();
		}	
	}
	
	
	@GET
	@Path("livro/buscacompleta/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getById(@QueryParam(value = "id") Integer id_prod) {
		if (livrodao.getById(id_prod) != null) {
			return Response.status(200).entity(livrodao.getById(id_prod)).build();			
		}
		else {			
			return Response.status(500).entity("Erro no banco de dados").build();
		}		
	}
}

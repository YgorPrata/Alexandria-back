package com.restapp.resource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.restapp.db.DbException;
import com.restapp.model.dao.ArteDao;
import com.restapp.model.dao.DaoFactory;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Arte;
import com.restapp.model.entities.Img;


@Path("/produto")
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

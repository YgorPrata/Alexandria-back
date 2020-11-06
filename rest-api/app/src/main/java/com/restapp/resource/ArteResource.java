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
import com.restapp.model.entities.Txt;

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
	
	@POST
	@Path("/arte/cadastro")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFiles2(
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@FormDataParam("desc_img") List<String> descricao2,
			@FormDataParam("txt") InputStream inputStream2,
			@FormDataParam("txt") FormDataContentDisposition fileDisposition2,
			@FormDataParam("titulo") String titulo,
			@FormDataParam("autor") String autor,
			@FormDataParam("descricao") String descricao,
			@FormDataParam("categoria") String categoria,
			@FormDataParam("tipo") String tipo,
			@FormDataParam("localidade") String localidade,
			@FormDataParam("ano") Integer ano,
			@FormDataParam("tecnica") String tecnica) throws IOException{
	
			List<Img> infoImg = new ArrayList<Img>();
			String nomesImgs;
			String nomeTxt;
			String auxDesc;

			for (int i = 0; i < inputStream.size(); i++) {
				BodyPartEntity bodyPartEntity = (BodyPartEntity) inputStream.get(i).getEntity();				
				InputStream inp = new BufferedInputStream(bodyPartEntity.getInputStream());
				nomesImgs = UUID.randomUUID()+inputStream.get(i).getContentDisposition().getFileName();
				//lista para as descricoes de cada imagem
				auxDesc = descricao2.get(i);
				
				if(nomesImgs.endsWith(".jpg") || nomesImgs.endsWith(".jpeg") || nomesImgs.endsWith(".png")) {
					infoImg.add(new Img(null, Img.pathimg+nomesImgs, auxDesc));					
					try {	
						File file = new File(Img.abspathimg+nomesImgs);					
						OutputStream ops = new FileOutputStream(file);						
						int read = 0;
						byte[] bytes = new byte[8192];						
						while ((read = inp.read(bytes)) != -1) {
							ops.write(bytes, 0, read);							
						}			
						ops.flush();
						ops.close();						
					}						
					catch (IOException e) {
						e.printStackTrace();												
					}
				}
			}
		
			
			//tratando o único text file
			Txt txt;
			nomeTxt = UUID.randomUUID()+fileDisposition2.getFileName();
			if(nomeTxt.endsWith(".pdf") || nomeTxt.endsWith(".docx") || nomeTxt.endsWith(".txt")) {
				txt = new Txt(Txt.pathtxt+nomeTxt);
				try {				
					inputStream2 = new BufferedInputStream(inputStream2);
					File file = new File(Txt.abspathtxt+nomeTxt);
					OutputStream ops2 = new FileOutputStream(file);
					int read = 0;
					byte[] bytes = new byte[8192];
					
					while ((read = inputStream2.read(bytes)) != -1) {
						ops2.write(bytes, 0, read);
					}
					ops2.flush();
					ops2.close();						
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				txt = new Txt();
			}
													
			try {
				Arte arte = new Arte(titulo, autor, descricao, categoria, tipo, localidade, ano, infoImg, tecnica);
				artedao.insert(arte, infoImg, txt);					
				return Response.status(200).build();
			} catch (DbException e) {
				e.printStackTrace();
				return Response.status(500).entity("Ops! Erro ao salvar o cadastro.").build();
			}
		
	}
		
}

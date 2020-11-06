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
import com.restapp.model.dao.DaoFactory;
import com.restapp.model.dao.LivroDao;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.Txt;

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
	
	@POST
	@Path("/livro/cadastro")
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
			@FormDataParam("editora") String editora,
			@FormDataParam("edicao") Integer edicao,
			@FormDataParam("biografia") String biografia) throws IOException{
	
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
				Livro livro = new Livro(titulo, autor, descricao, categoria, tipo, localidade, ano, infoImg, editora, edicao, biografia);
				livrodao.insert(livro, infoImg, txt);					
				return Response.status(200).build();
			} catch (DbException e) {
				e.printStackTrace();
				return Response.status(500).entity("Ops! Erro ao salvar o cadastro.").build();
			}
		
	}
}

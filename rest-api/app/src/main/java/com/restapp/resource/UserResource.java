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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.restapp.db.DbException;
import com.restapp.model.dao.DaoFactory;
import com.restapp.model.dao.UserDao;
import com.restapp.model.entities.Arquitetura;
import com.restapp.model.entities.Arte;
import com.restapp.model.entities.Img;
import com.restapp.model.entities.Livro;
import com.restapp.model.entities.User;

//@Seguro({UserRoles.ADMIN, UserRoles.USER})
@Path("/user")
public class UserResource {
	
	UserDao userdao = DaoFactory.criarUsuario();
	
	@POST
	@Path("/arquitetura/cadastro")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFiles(
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@FormDataParam("desc_img") List<String> descricao2,
			@FormDataParam("titulo") String titulo,
			@FormDataParam("autor") String autor,
			@FormDataParam("descricao") String descricao,
			@FormDataParam("categoria") String categoria,
			@FormDataParam("tipo") String tipo,
			@FormDataParam("localidade") String localidade,
			@FormDataParam("ano") Integer ano,
			@FormDataParam("curador") String curador,
			@FormDataParam("area") Double area,
			@HeaderParam("UserId") Integer userId) throws IOException{
			//userid é o value de um dos headers customizado para trafegar as infos do usuario		
			List<Img> infoImg = new ArrayList<Img>();
			String nomesImgs;
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
		
													
			try {
		
				Arquitetura arq = new Arquitetura(titulo, autor, descricao, categoria, tipo, localidade, ano, infoImg, new User(userId), curador, area);
				userdao.insertArq(arq, infoImg);					
				return Response.status(200).build();
			} catch (DbException e) {
				e.printStackTrace();
				return Response.status(500).entity("Ops! Erro ao salvar o cadastro.").build();
			}
		
	}
	
	@POST
	@Path("/arte/cadastro")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFiles2(
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@FormDataParam("desc_img") List<String> descricao2,
			@FormDataParam("titulo") String titulo,
			@FormDataParam("autor") String autor,
			@FormDataParam("descricao") String descricao,
			@FormDataParam("categoria") String categoria,
			@FormDataParam("tipo") String tipo,
			@FormDataParam("localidade") String localidade,
			@FormDataParam("ano") Integer ano,
			@FormDataParam("tecnica") String tecnica,
			@HeaderParam("UserId") Integer userId) throws IOException{
	
			List<Img> infoImg = new ArrayList<Img>();
			String nomesImgs;
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
															
			try {
				Arte arte = new Arte(titulo, autor, descricao, categoria, tipo, localidade, ano, infoImg, new User(userId), tecnica);
				userdao.insertArte(arte, infoImg);					
				return Response.status(200).build();
			} catch (DbException e) {
				e.printStackTrace();
				return Response.status(500).entity("Ops! Erro ao salvar o cadastro.").build();
			}
		
	}
	
	@POST
	@Path("/livro/cadastro")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFiles2(
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@FormDataParam("desc_img") List<String> descricao2,
			@FormDataParam("titulo") String titulo,
			@FormDataParam("autor") String autor,
			@FormDataParam("descricao") String descricao,
			@FormDataParam("categoria") String categoria,
			@FormDataParam("tipo") String tipo,
			@FormDataParam("localidade") String localidade,
			@FormDataParam("ano") Integer ano,
			@FormDataParam("editora") String editora,
			@FormDataParam("edicao") Integer edicao,
			@FormDataParam("biografia") String biografia,
			@HeaderParam("UserId") Integer userId) throws IOException{
	
			List<Img> infoImg = new ArrayList<Img>();
			String nomesImgs;
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
															
			try {
				Livro livro = new Livro(titulo, autor, descricao, categoria, tipo, localidade, ano, infoImg, new User(userId), editora, edicao, biografia);
				userdao.insertLivro(livro, infoImg);					
				return Response.status(200).build();
			} catch (DbException e) {
				e.printStackTrace();
				return Response.status(500).entity("Ops! Erro ao salvar o cadastro.").build();
			}		
	}
	
	@GET
	@Path("prod/prodid/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProdById(@QueryParam(value = "id") Integer id_prod, @HeaderParam("UserId") Integer id_user) {
		if (userdao.getProdById(id_prod, id_user ) != null) {
			return Response.status(200).entity(userdao.getProdById(id_prod, id_user)).build();			
		}
		else {			
			return Response.status(500).entity("Erro no banco de dados").build();
		}					
	}
	
	@GET
	@Path("prod/myprods/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response displayUserProdsSimp(@HeaderParam("UserId") Integer id_user) {
		if (userdao.displayUserProdsSimp(id_user) != null) {
			return Response.status(200).entity(userdao.displayUserProdsSimp(id_user)).build();			
		}
		else if(userdao.displayUserProdsSimp(id_user) == null) {
			return Response.status(200).entity("Usuário não possui registros").build();
		}
		else {			
			return Response.status(500).entity("Erro no banco de dados").build();
		}					
	}
	
	@PUT
	@Path("prod/myprods/uparq")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateUserArqProd(Arquitetura arq, @HeaderParam("UserId") Integer id_user) {
		if (userdao.updateUserArqProd(arq, id_user) != null) {
			return Response.status(200).entity(userdao.updateUserArqProd(arq, id_user)).build();			
		}
		else {			
			return Response.status(500).entity("Erro no banco de dados").build();
		}					
	}
	
	@PUT
	@Path("prod/myprods/uparte")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateUserArteProd(Arte arte, @HeaderParam("UserId") Integer id_user) {
		if (userdao.updateUserArteProd(arte, id_user) != null) {
			return Response.status(200).entity(userdao.updateUserArteProd(arte, id_user)).build();			
		}
		else {			
			return Response.status(500).entity("Erro no banco de dados").build();
		}					
	}
	
	@PUT
	@Path("prod/myprods/uplivro")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateUserLivroProd(Livro livro, @HeaderParam("UserId") Integer id_user) {
		if (userdao.updateUserLivroProd(livro, id_user) != null) {
			return Response.status(200).entity(userdao.updateUserLivroProd(livro, id_user)).build();			
		}
		else {			
			return Response.status(500).entity("Erro no banco de dados").build();
		}					
	}
	
	@DELETE
	@Path("prod/delete")
	@Consumes({MediaType.APPLICATION_JSON })
	public Response deleteUserProd(Integer id_user) {
		return null;
		
	}
	
	
	
	
}

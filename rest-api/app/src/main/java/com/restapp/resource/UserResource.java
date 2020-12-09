package com.restapp.resource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.restapp.resource.security.Seguro;
import com.restapp.resource.security.UserRoles;

import io.swagger.annotations.Api;

@Seguro({UserRoles.ADMIN, UserRoles.USER})
@Path("/user")
@Api("/User Service")
public class UserResource {
	
	UserDao userdao = DaoFactory.criarUsuario();
	
	@POST
	@Path("/arquitetura/cadastro")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response insertArq(@FormDataParam("arq") FormDataBodyPart jsonArq,
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@HeaderParam("UserId") Integer userId) throws IOException{
			//userid é o value de um dos headers customizado para trafegar as infos do usuario
			
			jsonArq.setMediaType(MediaType.APPLICATION_JSON_TYPE);
			
			System.out.println("JSON "+jsonArq.getFormDataContentDisposition().getParameters().values());
			
			Arquitetura arq = jsonArq.getValueAs(Arquitetura.class);

			System.out.println("AUTOR: "+arq.getAutor());
			
			String nomeIs;
			int cont = 0;
			
			System.out.println();
				
			for(Img img : arq.getListImg()) {				
				BodyPartEntity bodyPartEntity = (BodyPartEntity) inputStream.get(cont).getEntity();				
				InputStream inp = new BufferedInputStream(bodyPartEntity.getInputStream());
				nomeIs = UUID.randomUUID()+inputStream.get(cont).getContentDisposition().getFileName();
				img.setPath_img(Img.pathimg+nomeIs);
				//arq.getUser().setId_user(userId);
			
				try {
					File file = new File(Img.abspathimg+nomeIs);
					OutputStream ops = new FileOutputStream(file);
					int read = 0;
					byte[] bytes = new byte[8192];	
					
					while ((read = inp.read(bytes)) != -1) {				
						ops.write(bytes, 0, read);					
					}					
					ops.flush();
					ops.close();
					cont++;
				}						
				catch (IOException e) {
					e.printStackTrace();												
				}
			}		
			
			try {
				userdao.insertArq(arq, userId);					
				return Response.status(201).build();
			} catch (DbException e) {
				e.printStackTrace();
				return Response.status(500).entity("Ops! Erro ao salvar o cadastro.").build();
			}
			
	}
	
	@POST
	@Path("/arte/cadastro")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response insertArte(@FormDataParam("arte") FormDataBodyPart jsonArte,
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@HeaderParam("UserId") Integer userId) throws IOException{
			//userid é o value de um dos headers customizado para trafegar as infos do usuario
			
			jsonArte.setMediaType(MediaType.APPLICATION_JSON_TYPE);
			Arte arte = jsonArte.getValueAs(Arte.class);
			
			String nomeIs;
			int cont = 0;
			
			System.out.println();
				
			for(Img img : arte.getListImg()) {				
				BodyPartEntity bodyPartEntity = (BodyPartEntity) inputStream.get(cont).getEntity();				
				InputStream inp = new BufferedInputStream(bodyPartEntity.getInputStream());
				nomeIs = UUID.randomUUID()+inputStream.get(cont).getContentDisposition().getFileName();
				img.setPath_img(Img.pathimg+nomeIs);
				//arte.getUser().setId_user(userId);
			
				try {
					File file = new File(Img.abspathimg+nomeIs);
					OutputStream ops = new FileOutputStream(file);
					int read = 0;
					byte[] bytes = new byte[8192];	
					
					while ((read = inp.read(bytes)) != -1) {				
						ops.write(bytes, 0, read);					
					}					
					ops.flush();
					ops.close();
					cont++;
				}						
				catch (IOException e) {
					e.printStackTrace();												
				}
			}		
			
			try {
				userdao.insertArte(arte, userId);					
				return Response.status(201).build();
			} catch (DbException e) {
				e.printStackTrace();
				return Response.status(500).entity("Ops! Erro ao salvar o cadastro.").build();
			}
		
	}
	
	@POST
	@Path("/livro/cadastro")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response insertLivro(@FormDataParam("livro") FormDataBodyPart jsonLivro,
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@HeaderParam("UserId") Integer userId) throws IOException{
			//userid é o value de um dos headers customizado para trafegar as infos do usuario
			
			jsonLivro.setMediaType(MediaType.APPLICATION_JSON_TYPE);
			Livro livro = jsonLivro.getValueAs(Livro.class);
			
			String nomeIs;
			int cont = 0;
			
			System.out.println();
				
			for(Img img : livro.getListImg()) {				
				BodyPartEntity bodyPartEntity = (BodyPartEntity) inputStream.get(cont).getEntity();				
				InputStream inp = new BufferedInputStream(bodyPartEntity.getInputStream());
				nomeIs = UUID.randomUUID()+inputStream.get(cont).getContentDisposition().getFileName();
				img.setPath_img(Img.pathimg+nomeIs);
				//livro.getUser().setId_user(userId);
			
				try {
					File file = new File(Img.abspathimg+nomeIs);
					OutputStream ops = new FileOutputStream(file);
					int read = 0;
					byte[] bytes = new byte[8192];	
					
					while ((read = inp.read(bytes)) != -1) {				
						ops.write(bytes, 0, read);					
					}					
					ops.flush();
					ops.close();
					cont++;
				}						
				catch (IOException e) {
					e.printStackTrace();												
				}
			}		
			
			try {
				userdao.insertLivro(livro, userId);					
				return Response.status(201).build();
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
	
	@GET
	@Path("prod/check/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response checkExistProd(@QueryParam(value = "query") String query) {
		if (!userdao.checkExistProd(query)) {
			return Response.status(200).build();			
		}
		else if(userdao.checkExistProd(query)) {
			return Response.status(200).entity("Essa obra já existe!").build();
		}
		else {			
			return Response.status(500).entity("Erro no banco de dados").build();
		}					
	}
	
	@PUT
	@Path("prod/myprods/uparq")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response updateUserArqProd(@FormDataParam("arq") FormDataBodyPart jsonArq, 
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@HeaderParam("UserId") Integer userId) {
		
		 
		jsonArq.setMediaType(MediaType.APPLICATION_JSON_TYPE);
		Arquitetura arq = jsonArq.getValueAs(Arquitetura.class);
		
		String nomeIs;
		int cont = 0;
		
		
		for(Img img : arq.getListImg()) {
			System.out.println("TAMANHO DA LISTA DE IMAGEM: "+arq.getListImg().size());
			BodyPartEntity bodyPartEntity = (BodyPartEntity) inputStream.get(cont).getEntity();				
			InputStream inp = new BufferedInputStream(bodyPartEntity.getInputStream());
			nomeIs = inputStream.get(cont).getContentDisposition().getFileName();
			cont++;
			System.out.println("NOME DA IMAGEM QUE VEM DO CLIENTE: "+nomeIs);
			
			if(img.getId_img() != null && !nomeIs.isEmpty()) {
				img.setPath_img(img.getPath_img());
				nomeIs = img.getPath_img();
				
				String pathAndFileName = nomeIs;
				String originalPath = "/assets/images/files/";
				String replace = "";
				String nomeImgProcessado = pathAndFileName.replace(originalPath, replace);
				
				try {
					File file = new File(Img.abspathimg+nomeImgProcessado);
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
			else if(img.getId_img() == null) {
				cont--;
				nomeIs = UUID.randomUUID()+inputStream.get(cont).getContentDisposition().getFileName();
				img.setPath_img(Img.pathimg+nomeIs);
				System.out.println("ID DO USUARIO HEADPARAM: "+userId);
			
				try {
					File file = new File(Img.abspathimg+nomeIs);
					OutputStream ops = new FileOutputStream(file);
					int read = 0;
					byte[] bytes = new byte[8192];	
					
					while ((read = inp.read(bytes)) != -1) {				
						ops.write(bytes, 0, read);					
					}					
					ops.flush();
					ops.close();
					cont++;
				}						
				catch (IOException e) {
					e.printStackTrace();												
				}
			}		
		}
		
		if(userdao.addNewImg(arq)) {
			if(userdao.updateUserArqProd(arq, userId)) {
				return Response.status(201).entity("Obra atualizada e novas imagens adicionadas!").build();
			}
			else {
				return Response.status(500).entity("Erro ao atualizar a obra.").build();
			}
		}
		else if(!userdao.addNewImg(arq)) {
			if(userdao.updateUserArqProd(arq, userId)) {
				return Response.status(200).entity("Obra atualizada!").build();
			}
			else {
				return Response.status(500).entity("Erro ao atualizar a obra.").build();
			}
		}
					
		else {			
			return Response.status(500).entity("Erro no banco de dados.").build();
		}
		
	}
	
	
	@PUT
	@Path("prod/myprods/uparte")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response updateUserArteProd(@FormDataParam("arte") FormDataBodyPart jsonArte, 
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@HeaderParam("UserId") Integer userId) {
		
		 
		jsonArte.setMediaType(MediaType.APPLICATION_JSON_TYPE);
		Arte arte = jsonArte.getValueAs(Arte.class);
		
		String nomeIs;
		int cont = 0;
			
		for(Img img : arte.getListImg()) {
			System.out.println("TAMANHO DA LISTA DE IMAGEM: "+arte.getListImg().size());
			BodyPartEntity bodyPartEntity = (BodyPartEntity) inputStream.get(cont).getEntity();				
			InputStream inp = new BufferedInputStream(bodyPartEntity.getInputStream());
			nomeIs = inputStream.get(cont).getContentDisposition().getFileName();
			cont++;
			System.out.println("NOME DA IMAGEM QUE VEM DO CLIENTE: "+nomeIs);
			
			if(img.getId_img() != null && !nomeIs.isEmpty()) {
				img.setPath_img(img.getPath_img());
				nomeIs = img.getPath_img();
				
				String pathAndFileName = nomeIs;
				String originalPath = "imgs/";
				String replace = "";
				String nomeImgProcessado = pathAndFileName.replace(originalPath, replace);
				
				try {
					File file = new File(Img.abspathimg+nomeImgProcessado);
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
			else if(img.getId_img() == null) {
				cont--;
				nomeIs = UUID.randomUUID()+inputStream.get(cont).getContentDisposition().getFileName();
				img.setPath_img(Img.pathimg+nomeIs);
				System.out.println("ID DO USUARIO HEADPARAM: "+userId);
			
				try {
					File file = new File(Img.abspathimg+nomeIs);
					OutputStream ops = new FileOutputStream(file);
					int read = 0;
					byte[] bytes = new byte[8192];	
					
					while ((read = inp.read(bytes)) != -1) {				
						ops.write(bytes, 0, read);					
					}					
					ops.flush();
					ops.close();
					cont++;
				}						
				catch (IOException e) {
					e.printStackTrace();												
				}
			}		
		}
		
		if(userdao.addNewImg(arte)) {
			if(userdao.updateUserArteProd(arte, userId)) {
				return Response.status(201).entity("Obra atualizada e novas imagens adicionadas!").build();
			}
			else {
				return Response.status(500).entity("Erro ao atualizar a obra.").build();
			}
		}
		else if(!userdao.addNewImg(arte)) {
			if(userdao.updateUserArteProd(arte, userId)) {
				return Response.status(200).entity("Obra atualizada!").build();
			}
			else {
				return Response.status(500).entity("Erro ao atualizar a obra.").build();
			}
		}
					
		else {			
			return Response.status(500).entity("Erro no banco de dados.").build();
		}					
	}
	
	@PUT
	@Path("prod/myprods/uplivro")
	@Consumes({ MediaType.MULTIPART_FORM_DATA})
	public Response updateUserLivroProd(@FormDataParam("livro") FormDataBodyPart jsonLivro, 
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@HeaderParam("UserId") Integer userId) {
		
		 
		jsonLivro.setMediaType(MediaType.APPLICATION_JSON_TYPE);
		Livro livro = jsonLivro.getValueAs(Livro.class);
		
		String nomeIs;
		int cont = 0;
			
		for(Img img : livro.getListImg()) {
			System.out.println("TAMANHO DA LISTA DE IMAGEM: "+livro.getListImg().size());
			BodyPartEntity bodyPartEntity = (BodyPartEntity) inputStream.get(cont).getEntity();				
			InputStream inp = new BufferedInputStream(bodyPartEntity.getInputStream());
			nomeIs = inputStream.get(cont).getContentDisposition().getFileName();
			cont++;
			System.out.println("NOME DA IMAGEM QUE VEM DO CLIENTE: "+nomeIs);
			
			if(img.getId_img() != null && !nomeIs.isEmpty()) {
				img.setPath_img(img.getPath_img());
				nomeIs = img.getPath_img();
				
				String pathAndFileName = nomeIs;
				String originalPath = "imgs/";
				String replace = "";
				String nomeImgProcessado = pathAndFileName.replace(originalPath, replace);
				
				try {
					File file = new File(Img.abspathimg+nomeImgProcessado);
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
			else if(img.getId_img() == null) {
				cont--;
				nomeIs = UUID.randomUUID()+inputStream.get(cont).getContentDisposition().getFileName();
				img.setPath_img(Img.pathimg+nomeIs);
				System.out.println("ID DO USUARIO HEADPARAM: "+userId);
			
				try {
					File file = new File(Img.abspathimg+nomeIs);
					OutputStream ops = new FileOutputStream(file);
					int read = 0;
					byte[] bytes = new byte[8192];	
					
					while ((read = inp.read(bytes)) != -1) {				
						ops.write(bytes, 0, read);					
					}					
					ops.flush();
					ops.close();
					cont++;
				}						
				catch (IOException e) {
					e.printStackTrace();												
				}
			}		
		}
		
		if(userdao.addNewImg(livro)) {
			if(userdao.updateUserLivroProd(livro, userId)) {
				return Response.status(201).entity("Obra atualizada e novas imagens adicionadas!").build();
			}
			else {
				return Response.status(500).entity("Erro ao atualizar a obra.").build();
			}
		}
		else if(!userdao.addNewImg(livro)) {
			if(userdao.updateUserLivroProd(livro, userId)) {
				return Response.status(200).entity("Obra atualizada!").build();
			}
			else {
				return Response.status(500).entity("Erro ao atualizar a obra.").build();
			}
		}
					
		else {			
			return Response.status(500).entity("Erro no banco de dados.").build();
		}					
	}
	
	@DELETE
	@Path("prod/delprods/{id}")
	@Consumes({MediaType.APPLICATION_JSON })
	public Response deleteUserProd(@PathParam("id") Integer id_prod) {
		if (userdao.deleteUserProd(id_prod)) {
			return Response.status(200).entity("Produto deletado com sucesso.").build();			
		}
		else if(!userdao.deleteUserProd(id_prod)) {
			return Response.status(404).entity("Id do Produto não encontrado. Nenhuma alteração foi feita.").build();
		}
		else {			
			return Response.status(500).entity("Produto não deletado, problema com o banco.").build();
		}
		
	}
	
	
	
	
}

package com.restapp.resource;

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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.restapp.db.DbException;
import com.restapp.model.dao.impl.ArquiteturaDaoJDBC;
import com.restapp.model.entities.Arquitetura;

@Path("/produto")
public class ArquiteturaResource {

	ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
	String pathimg = "c:/temp/imgs/";
	String pathtxt = "c:/temp/txt/";

	@GET
	@Path("/arquitetura")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() throws Exception {
		if (arqdao.findAll().size() > 0) {
			return Response.status(200).entity(arqdao.findAll()).build();
		} else {
			return Response.status(404).entity("Não há nenhum registro para essa categoria").build();
		}
	}

	@GET
	@Path("/arquitetura/busca/{query}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByName(@PathParam("autor") String autor) {
		if (arqdao.findByAutor(autor).size() > 0) {
			return Response.status(200).entity(arqdao.findByAutor(autor)).build();
		} else {
			return Response.status(404).entity("Não há nenhum registro com esse nome.").build();
		}

	}
	
	@GET
	@Path("/getImage")
	@Produces({"images/png", "images/jpg"})
	 public Response GetImageByName(@PathParam("nome") String nome) {
		Arquitetura arq = new Arquitetura();
		String aux;
		String aux2;
		System.out.println("SAIDA ARQUITETURA: "+arq);
		for(int i = 0; i <= 10 ; i++ ) {
			aux = arq.getImg_path();
			aux2 = arq.getImg_desc();
			
			System.out.println("CAMINHOS: "+ aux);
			System.out.println("DESCRICOES: "+ aux2);
		}
		
		/*file = new File(caminhoPasta + "/imagem.png");
		 
		responseBuilder = Response.ok(file);
 
		responseBuilder.header("Content-Disposition", "attachment;filename=" + file.getName());
 
		return responseBuilder.build();

	     String mmcpath = gi.filepath;

	     BufferedImage image = ImageUtil.getImageFromPath(mmcpath);

	     if (image != null) {

	         // resize the image to fit the GUI's image frame
	         image = ImageUtil.resize(image, width, height);
	         InputStream is = ImageUtil.getStreamFromImage(image, FileHelper.getFileSuffix(mmcpath));

	         if (is != null) {
	             return Response.ok(is).build();
	         } else {
	             return Response.noContent().build();
	         }
	     }
		*/


	     return Response.noContent().build();
	 }
	
	
	@POST
	@Path("/arquitetura/cadastro")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFiles2(
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@FormDataParam("img") FormDataContentDisposition fileDisposition,
			@FormDataParam("txt") InputStream inputStream2,
			@FormDataParam("txt") FormDataContentDisposition fileDisposition2,
			@FormDataParam("nome") String nome,
			@FormDataParam("categoria") String categoria,
			@FormDataParam("tipo") String tipo,
			@FormDataParam("autor") String autor,
			@FormDataParam("material") String material,
			@FormDataParam("ano") Integer ano,
			@FormDataParam("descricao") String descricao,
			@FormDataParam("descricao2") List<String> descricao2) {

			List<String> listarqnome = new ArrayList<String>();
			List<String> listdescimg = new ArrayList<String>();
			String nomesImgs;
			String nomeTxt;
			String auxDesc;
			boolean txtsalvo;
			boolean imgsalvo = false;
			
			//tratando os dados recebidos por parametro e enviando para salvar (saveFile)
			for (int i = 0; i < inputStream.size(); i++) {
				BodyPartEntity bodyPartEntity = (BodyPartEntity) inputStream.get(i).getEntity();							
				nomesImgs = pathimg+UUID.randomUUID()+inputStream.get(i).getContentDisposition().getFileName();				
				listarqnome.add(nomesImgs);				
				imgsalvo = saveFile(bodyPartEntity.getInputStream(), nomesImgs);
				
				auxDesc = descricao2.get(i);
				listdescimg.add(auxDesc);
			}					
			
			//tratando o único dado de arquivo texto e enviando para salvar (saveFile)
			nomeTxt = pathtxt+UUID.randomUUID()+fileDisposition2.getFileName();
			txtsalvo = saveFile(inputStream2, nomeTxt);
			
			
			//Buscar alguma forma de tratar o cadastro caso não consiga salvar os arquivos no saveFile
			Arquitetura arq = new Arquitetura(nome, categoria, tipo, autor, material, ano, descricao);
			System.out.println("RETORNO TXTSALVO: "+txtsalvo+ "RETORNO IMGSALVO"+imgsalvo);		
			try {				
				if (txtsalvo && imgsalvo) {
					arqdao.insert(arq, listarqnome, listdescimg, nomeTxt);
					return Response.status(200).build();
				} else { 
					return Response.status(500).entity("Ops! Ocorreu um erro ao salvar o cadastro no banco.").build();
					
				}
			} catch (DbException e) {
				e.printStackTrace();
				return Response.status(500).entity("Ops! Parece que o banco de dados está com um problema.").build();
			}
			
	}
	
	private boolean saveFile(InputStream uploadedInputStream, String nomesArqs) {		
		try {
			if(nomesArqs.endsWith(".txt") || nomesArqs.startsWith(".pdf") || nomesArqs.startsWith(".docx")) {
				File file = new File(nomesArqs);
				OutputStream ops = new FileOutputStream(file);
				int read = 0;
				byte[] bytes = new byte[1024];
				
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					ops.write(bytes, 0, read);
				}
				ops.flush();
				ops.close();
				return true;
			}
			else if(nomesArqs.endsWith(".jpg") || nomesArqs.startsWith(".png")){
				File file = new File(nomesArqs);
				OutputStream ops = new FileOutputStream(file);
				int read = 0;
				byte[] bytes = new byte[1024];
				
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					ops.write(bytes, 0, read);
				}
				ops.flush();
				ops.close();
				return true;
			}
			else {
				System.out.println("arquivo não salvo.");
				return false;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
				
	}
	
}
package com.restapp.resource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.restapp.db.DbException;
import com.restapp.model.dao.impl.ArquiteturaDaoJDBC;
import com.restapp.model.entities.Arquitetura;

@Path("/produto")
public class ArquiteturaResource {

	ArquiteturaDaoJDBC arqdao = new ArquiteturaDaoJDBC();
	String abspathimg = "C:/Users/felip/OneDrive/Disciplinas - Cursos/Faculdade/2020.2/Projeto Final 2/PROJETOS/Upload/WebContent/imgs/";
	String abspathtxt = "C:/Users/felip/OneDrive/Disciplinas - Cursos/Faculdade/2020.2/Projeto Final 2/PROJETOS/Upload/WebContent/txt/";
	String pathimg = "imgs/";
	String pathtxt = "txt/";

	@GET
	@Path("/arquitetura")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws Exception {
		if (arqdao.getAll().size() > 0) {
			return Response.status(200).entity(arqdao.getAll()).build();
		} else {
			return Response.status(404).entity("Não há nenhum registro para essa categoria").build();
		}
	}

	@GET
	@Path("/arquitetura/buscaautor/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getArqSimples(@QueryParam(value = "autor") String autor) {
		System.out.println("AUTOR MANDADO PELO CLIENT: "+autor);
		if (arqdao.getArqSimpAutor(autor).size() > 0) {
			return Response.status(200).entity(arqdao.getArqSimpAutor(autor)).build();
		} else {
			return Response.status(404).entity("Não há nenhum registro com esse nome.").build();
		}

	}
	
	@GET
	@Path("/arquitetura/buscatitulo/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getArqSimpTitulo(@QueryParam(value = "titulo") String titulo) {
		System.out.println("TITULO MANDADO PELO CLIENT: "+titulo);
		if (arqdao.getArqSimpTitulo(titulo).size() > 0) {
			return Response.status(200).entity(arqdao.getArqSimpTitulo(titulo)).build();
		} else {
			return Response.status(404).entity("Não há nenhum registro com esse titulo.").build();
		}
	}
	
	@GET
	@Path("/arquitetura/buscalocal/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getArqSimpLocal(@QueryParam(value = "localidade") String local) {
		System.out.println("LOCALIDADE MANDADO PELO CLIENT: "+local);
		if (arqdao.getArqSimpLocal(local).size() > 0) {
			return Response.status(200).entity(arqdao.getArqSimpLocal(local)).build();
		} else {
			return Response.status(404).entity("Não há nenhum registro com essa localidade.").build();
		}
	}
	
	@GET
	@Path("/arquitetura/buscararq/{id_arq}")
	@Produces({"images/png", "images/jpg"})
	public Response getById(@PathParam("id_arq") Integer id_arq) throws JsonProcessingException {
		Arquitetura aux;
		ArrayList<Arquitetura> list = new ArrayList<Arquitetura>(arqdao.getById(id_arq));
		//Tratando imagens e descricoes
		for(int i = 0; i <= list.size() - 1; i++) {
			aux = list.get(i);			
			
			System.out.println(aux.getImg_desc());
			System.out.println(aux.getId_img());
			System.out.println(aux.getImg_path());
						
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String jsondesc = ow.writeValueAsString(aux.getImg_desc());
			
			File file = new File(aux.getImg_path());
			System.out.println("IMAGEM: "+file);
		      if (!file.exists()) {
		    	  return Response.status(404).build();
		      }
		
			String mt = new MimetypesFileTypeMap().getContentType(file);
			return Response.ok(file, mt).build();
			
		}
		
		//Tratando entidade arquitetura
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(arqdao.getById(id_arq));
		
		if (arqdao.getById(id_arq).size() == 0) {
			return Response.status(404).build();
		} else {			
			return Response.ok(json, MediaType.APPLICATION_JSON).build();			
		}

	}
	
	@POST
	@Path("/arquitetura/cadastro")
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
			@FormDataParam("ano") Integer ano) throws IOException{
		
			List<String> listarqnome = new ArrayList<String>();
			List<String> listdescimg = new ArrayList<String>();
			String nomesImgs;
			String nomeTxt;
			String rltImgs;
			String rltTxt;
			String auxDesc;
			//tratando os dados recebidos por parametro e enviando para salvar (saveFile)
			for (int i = 0; i < inputStream.size(); i++) {
				BodyPartEntity bodyPartEntity = (BodyPartEntity) inputStream.get(i).getEntity();				
				InputStream inp = new BufferedInputStream(bodyPartEntity.getInputStream());
				nomesImgs = UUID.randomUUID()+inputStream.get(i).getContentDisposition().getFileName();				
				listarqnome.add(pathimg+nomesImgs);
				

				
				//lista para as descricoes de cada imagem
				auxDesc = descricao2.get(i);
				listdescimg.add(auxDesc);
				
				File file = new File(abspathimg+nomesImgs);				
				OutputStream ops = new FileOutputStream(file);				
				try {					
					if(nomesImgs.endsWith(".jpg") || nomesImgs.startsWith(".png")){
						//File file = new File(nomesImgs);
						//OutputStream ops = new FileOutputStream(file);						
						int read = 0;
						byte[] bytes = new byte[8192];
						while ((read = inp.read(bytes)) != -1) {
							System.out.println("FILE SIZE: "+file.length());
							ops.write(bytes, 0, read);
						}			
						ops.flush();
						ops.close();
					}
						
				} catch (IOException e) {
					e.printStackTrace();
					File delfile = file;
					delfile.delete();
										
				}
			}	
			
			//tratando o único text file
			nomeTxt = UUID.randomUUID()+fileDisposition2.getFileName();

			try {
				if(nomeTxt.endsWith(".txt") || nomeTxt.endsWith(".docx") || nomeTxt.endsWith(".pdf")){
					inputStream2 = new BufferedInputStream(inputStream2);
					File file = new File(abspathtxt+nomeTxt);
					OutputStream ops2 = new FileOutputStream(file);
					int read = 0;
					byte[] bytes = new byte[1024];
					
					while ((read = inputStream2.read(bytes)) != -1) {
						ops2.write(bytes, 0, read);
					}
					ops2.flush();
					ops2.close();						
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//montando obj arquitetura para inserir no banco			
			Arquitetura arq = new Arquitetura(titulo, autor, descricao, categoria, tipo, localidade, ano);		
			try {
				arqdao.insert(arq, listarqnome, listdescimg, pathtxt+nomeTxt);					
				return Response.status(200).build();
			} catch (DbException e) {
				e.printStackTrace();
				return Response.status(500).entity("Ops! Erro ao salvar o cadastro.").build();
			}
		
	}
	

	
			
	/*@POST
	@Path("/arquitetura/cadastro")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFiles2(
			@FormDataParam("img") List<FormDataBodyPart> inputStream,
			@FormDataParam("img") FormDataContentDisposition fileDisposition,
			@FormDataParam("desc_img") List<String> descricao2,
			@FormDataParam("txt") InputStream inputStream2,
			@FormDataParam("txt") FormDataContentDisposition fileDisposition2,
			@FormDataParam("titulo") String titulo,
			@FormDataParam("autor") String autor,
			@FormDataParam("descricao") String descricao,
			@FormDataParam("categoria") String categoria,
			@FormDataParam("tipo") String tipo,
			@FormDataParam("localidade") String localidade,
			@FormDataParam("ano") Integer ano) {
		
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
			
			
			
			Arquitetura arq = new Arquitetura(titulo, autor, descricao, categoria, tipo, localidade, ano);
			System.out.println("RETORNO TXTSALVO: "+txtsalvo+ "RETORNO IMGSALVO"+imgsalvo);		
			try {				
				if (txtsalvo && imgsalvo && arq != null) {
					System.out.println("PRINT ARQUITETURA: "+arq);
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
*/	
}
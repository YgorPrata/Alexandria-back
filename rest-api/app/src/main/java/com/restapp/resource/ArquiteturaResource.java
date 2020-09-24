package com.restapp.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
	String path = "c:/temp/";

	@GET
	@Path("/arquitetura")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() throws Exception {
		if (arqdao.findAll().size() > 0) {
			return Response.ok().entity(arqdao.findAll()).build();
		} else {
			return Response.status(404).entity("Não há nenhum registro para essa categoria").build();
		}
	}

	@GET
	@Path("/arquitetura/busca/{query}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByName(@PathParam("query") String query) {
		if (arqdao.findByName(query).size() > 0) {
			return Response.ok().entity(arqdao.findByName(query)).build();
		} else {
			return Response.status(404).entity("Não há nenhum registro com esse nome.").build();
		}

	}

	/*@POST
	@Path("arquitetura/cadastro")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(Arquitetura arq, String archnome) {
		try {
			boolean cadsalvo = new ArquiteturaDaoJDBC().insert(arq, path);
			if (cadsalvo) {
				return Response.ok().entity(arq).build();
			} else { // se o cadastro não for salvo
				return Response.status(500).entity("Ops! Ocorreu um erro ao salvar o cadastro no banco.").build();
			}
		} catch (DbException e) {
			e.printStackTrace();
			return Response.status(500).entity("Ops! Parece que o banco de dados está com um problema.").build();
		}
	}*/

	/*@POST
	@Path("/arquitetura/cadastro")
	@Consumes({MediaType.MULTIPART_FORM_DATA} )
	public Response uploadFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader,
			@FormDataParam("nome") String nome,
			@FormDataParam("categoria") String categoria,
			@FormDataParam("tipo") String tipo,
			@FormDataParam("autor") String autor,
			@FormDataParam("material") String material,
			@FormDataParam("ano") Integer ano,
			@FormDataParam("descricao") String descricao) {
		String filePath = path + contentDispositionHeader.getFileName();
		
		Arquitetura arq = new Arquitetura(nome, categoria, tipo, autor, material, ano, descricao);

		saveFile(arq, fileInputStream, filePath);

		String output = "File saved to server location : " + filePath;

		return Response.status(200).entity(output).build();

	}*/

	/*private Response saveFile(Arquitetura arq, InputStream uploadedInputStream, String filePath) {		
		try {			
			OutputStream outpuStream = new FileOutputStream(new File(filePath));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(filePath));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		System.out.println("Arquitetura: " +arq);
		System.out.println("InputStream: " +uploadedInputStream);
		System.out.println("filePath: " +filePath);
		
		try {
			boolean cadsalvo = new ArquiteturaDaoJDBC().insert(arq, filePath, "testedescricao");
			if (cadsalvo) {
				return Response.ok().entity("Cadastro salvo").build();
			} else { // se o cadastro não for salvo
				return Response.status(500).entity("Ops! Ocorreu um erro ao salvar o cadastro no banco.").build();
			}
		} catch (DbException e) {
			e.printStackTrace();
			return Response.status(500).entity("Ops! Parece que o banco de dados está com um problema.").build();
		}

	}*/
	
	@Path("/files")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFiles2(
			@FormDataParam("files") List<FormDataBodyPart> bodyParts,
			@FormDataParam("files") FormDataContentDisposition fileDispositions,
			@FormDataParam("file2") InputStream file2,
			@FormDataParam("file2") FormDataContentDisposition fileDisposition2,
			@FormDataParam("nome") String nome,
			@FormDataParam("categoria") String categoria,
			@FormDataParam("tipo") String tipo,
			@FormDataParam("autor") String autor,
			@FormDataParam("material") String material,
			@FormDataParam("ano") Integer ano,
			@FormDataParam("descricao") String descricao) {

			String fileDetails;
			Arquitetura arq = new Arquitetura(nome, categoria, tipo, autor, material, ano, descricao);

		/* Save multiple files */

		for (int i = 0; i < bodyParts.size(); i++) {
			//casting pra gerar um inputstream e dar upload no arquivo
			BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyParts.get(i).getEntity();
			String fileName = bodyParts.get(i).getContentDisposition().getFileName();

			saveFile(arq, bodyPartEntity.getInputStream(), fileName);

			fileDetails = "Arquivo salvo: "+ path + fileName;
		}

		/* Save File 2 */

		String file2Name = fileDisposition2.getFileName();

		saveFile(arq, file2, file2Name);
		fileDetails = "Arquivo salvo: "+ path  + file2Name;

		System.out.println(fileDetails);

		return Response.ok(fileDetails.toString()).build();
	}

	private Response saveFile(Arquitetura arq, InputStream file, String name) {
		List<String> list = new ArrayList<String>();
		
		try {			
			OutputStream outpuStream = new FileOutputStream(new File(path + name));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(path + name));
			while ((read = file.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		list.add(path+name);
		
		try {
			boolean cadsalvo = new ArquiteturaDaoJDBC().insert(arq, list, "testedescricao");
			if (cadsalvo) {
				return Response.ok().entity("Cadastro salvo").build();
			} else { // se o cadastro não for salvo
				return Response.status(500).entity("Ops! Ocorreu um erro ao salvar o cadastro no banco.").build();
			}
		} catch (DbException e) {
			e.printStackTrace();
			return Response.status(500).entity("Ops! Parece que o banco de dados está com um problema.").build();
		}
	}

}

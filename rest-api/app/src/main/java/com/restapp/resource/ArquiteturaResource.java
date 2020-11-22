package com.restapp.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.restapp.model.dao.ArquiteturaDao;
import com.restapp.model.dao.DaoFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;



@Path("/produto")
@Api("/Arquitetura Service")
public class ArquiteturaResource {

	ArquiteturaDao arqdao = DaoFactory.criarArquitetura();

	@GET
	@Path("arquitetura/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws Exception {
		if (arqdao.getAll().size() > 0) {
			return Response.status(200).entity(arqdao.getAll()).build();
		} else {
			return Response.status(200).entity("Não há nenhum registro para essa categoria").build();
		}
	}
		
	@GET
	@Path("arquitetura/tipo/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getArqTipo(@QueryParam(value = "titulo") String titulo, @QueryParam(value = "autor") String autor, 
			@QueryParam(value = "localidade") String localidade, @QueryParam(value = "limite") Integer limit) {
		if (arqdao.getArqTipo(titulo, autor, localidade, limit).size() > 0) {
			return Response.status(200).entity(arqdao.getArqTipo(titulo, autor, localidade, limit)).build();			
		}
		else if(arqdao.getArqTipo(titulo, autor, localidade, limit).size() <= 0) {
			return Response.status(200).entity("Não há nenhum registro com esse termo.").build();
		}
		else {
			return Response.status(500).entity("Erro no banco").build();
		}	
	}
	
	@GET
	@Path("arquitetura/categoria/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getArqCategoria(@QueryParam(value = "query") String query, @QueryParam(value = "limite") Integer limit) {
		if (arqdao.getArqCategoria(query, limit).size() > 0) {
			return Response.status(200).entity(arqdao.getArqCategoria(query, limit)).build();			
		}
		else if(arqdao.getArqCategoria(query, limit).size() <= 0) {
			return Response.status(200).entity("Não há nenhum registro com esse termo.").build();
		}
		else {
			return Response.status(500).entity("Erro no banco").build();
		}	
	}
	
	
	
	@GET
	@Path("arquitetura/buscacompleta/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getById(@QueryParam(value = "id") Integer id_prod) {
		if (arqdao.getById(id_prod) != null) {
			return Response.status(200).entity(arqdao.getById(id_prod)).build();			
		}
		else {			
			return Response.status(500).entity("Erro no banco de dados").build();
		}
		
			
	}
	
	
	/*@GET
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
			//String jsondesc = ow.writeValueAsString(aux.getImg_desc());
			
			//File file = new File(aux.getImg_path());
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

	}*/
		
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
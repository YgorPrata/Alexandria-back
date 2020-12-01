package com.restapp.resource;



import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.restapp.model.dao.DaoFactory;
import com.restapp.model.dao.LoginDao;
import com.restapp.model.entities.User;
import com.restapp.resource.security.UserRoles;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Path("/login")
@Api("/Login Service")
public class LoginResource {
	
	private static final String phrase = "TR4B4LH0D3CONCLUS@0D3CUR50";
	private static final String FRASE_SEGREDO = UUID.randomUUID()+phrase+UUID.randomUUID();
	LoginDao logindao = DaoFactory.criarAutenticacaoUsuario();

	
	@POST
	@Path("cadastro/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertUser(User usuario) {
		if(logindao.insertUser(usuario)) {
			String token = gerarToken(usuario.getUser(),1);			
			return Response.status(200).entity("Usuario cadastrado com sucesso!").header("Authorization", token)
					.header("UserId", usuario.getId_user())
					.header("UserName", usuario.getUser_name()).build();
		}
		else if(!logindao.insertUser(usuario)){
			return Response.status(500).entity("Login já existente. Tente outro. ").build();
		}	
		else {
			return Response.status(500).entity("Ocorreu algum problema no banco.").build();
		}
	}
		

	
	@POST
	@Path("autentica/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response fazerLogin(String credenciaisJson){

		try {

			Gson gson = new Gson();
	
			User credencial = gson.fromJson(credenciaisJson, User.class);
	 
			User userInfos = validaLogin(credencial.getUser(), credencial.getPassword());
	
			String token = gerarToken(credencial.getUser(),1);
			//passando as informacoes do usuario por um header customizado no http, token, id e nome
			return Response.status(200).header("Authorization", token).header("UserId", userInfos.getId_user())
					.header("UserName", userInfos.getUser_name()).build();
			

		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(401).entity("login ou senha incorretos").build();

			}

		}
	
	private User validaLogin(String user, String password) throws Exception{	
		try {
			if(logindao.validaUser(user, password) == null) {
				throw new Exception("Crendencias não válidas!");
			}
			else {
				User userInfo = logindao.validaUser(user, password);
				return userInfo;
			}
		} 
		catch (Exception e) {
			throw e;
		}

	}
	
	private String gerarToken(String user,Integer expiraEmDias ){

		//HMAC SHA2 512
		SignatureAlgorithm algoritimoAssinatura = SignatureAlgorithm.HS512;

		Date agora = new Date();

		Calendar expira = Calendar.getInstance();

		expira.add(Calendar.DAY_OF_MONTH, expiraEmDias); 

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(FRASE_SEGREDO);

		SecretKeySpec key = new SecretKeySpec(apiKeySecretBytes, algoritimoAssinatura.getJcaName());

		JwtBuilder jsonWT = Jwts.builder()
			.setIssuedAt(agora)
			.setIssuer(user)
			.signWith(algoritimoAssinatura, key)
			.setExpiration(expira.getTime());
	
		return jsonWT.compact();

	}
	
	public Claims validaToken(String token) throws Exception {

		try{
			Claims claims = Jwts.parser()     
			.setSigningKey(DatatypeConverter.parseBase64Binary(FRASE_SEGREDO))
			.parseClaimsJws(token).getBody();
					
			return claims;

		}
		catch(Exception ex){
			throw ex;
		}

	}
	
	public UserRoles getUserRoles(String login) {		
		System.out.println("VALOR DA SAIDA DA CHAMADA DO METODO DE VALIDACAO DA ROLE: "+logindao.validaRoleUser(login));
		if(logindao.validaRoleUser(login).equals("admin")) {
			return UserRoles.ADMIN;
		}
		else {
			return UserRoles.USER;
		}
		
	}

}

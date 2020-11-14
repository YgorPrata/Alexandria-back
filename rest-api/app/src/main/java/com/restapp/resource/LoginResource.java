package com.restapp.resource;



import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import com.google.gson.Gson;
import com.restapp.model.dao.DaoFactory;
import com.restapp.model.dao.LoginDao;
import com.restapp.model.entities.User;
import com.restapp.resource.security.UserRoles;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/login")
public class LoginResource {
	
	private static final String phrase = "#";
	private static final String FRASE_SEGREDO = UUID.randomUUID()+phrase+UUID.randomUUID();
	LoginDao logindao = DaoFactory.autenticaUsuario();

	
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
			if(logindao.validaUsuario(user, password) == null) {
				throw new Exception("Crendencias não válidas!");
			}
			else {
				User userInfo = logindao.validaUsuario(user, password);
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
		// CRIAR UM METODO DAO QUE PEGA O LOGIN DO USUÁRIO AQUI POR PARAMETRO E QUE RETORNE A ROLE CADASTRADA NO BANCO
		// CRIAR CONDICIONAL, SE FOR USER RETORNA ENUM USER, SE FOR ADMIN, RETORNA ENUM ADMIN
		System.out.println("VALOR DA SAIDA DA CHAMADA DO METODO DE VALIDACAO DA ROLE: "+logindao.validaRoleUser(login));
		if(logindao.validaRoleUser(login).equals("admin")) {
			return UserRoles.ADMIN;
		}
		else {
			return UserRoles.USER;
		}
		
	}

}

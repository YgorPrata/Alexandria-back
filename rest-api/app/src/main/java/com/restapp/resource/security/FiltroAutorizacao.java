package com.restapp.resource.security;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.restapp.resource.LoginResource;


@Seguro
@Provider
@Priority(Priorities.AUTHORIZATION)
public class FiltroAutorizacao implements ContainerRequestFilter {
	@Context
	private ResourceInfo resourceInfo;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		/*TRATA A AUTORIZAÇÃO A NÍVEL DE CLASSE*/
		Class<?> classe = resourceInfo.getResourceClass();
		System.out.println("CLASSE DO CLASS<?> RESOURCE INFO: "+classe);
		List<UserRoles> nivelPermissaoClasse = extrairUserRoles(classe);
		System.out.println("NIVEL DE PERMISSAO DA CLASSE: "+nivelPermissaoClasse);
		/*AQUI É EXTRAÍDO O NÍVEL DE PERMISSAO A NIVEL DE CLASSE E PASSADO PARA O BLOCO SEGUINTE*/

		/*TRATA A AUTORIZAÇÃO A NÍVEL DE MÉTODO (PERMISSAO DA NOTATION QUE ESTÁ NO ENDPOINT ACESSADO)*/
		Method metodo = resourceInfo.getResourceMethod();
		List<UserRoles> nivelPermisaoMetodo = extrairUserRoles(metodo);
		System.out.println("NIVEL DE PERMISSAO METODO: "+nivelPermisaoMetodo);
		/*AQUI É EXTRAÍDO O NÍVEL DE PERMISSAO A NIVEL DE METODO E PASSADO PARA O BLOCO SEGUINTE 
		(PERMISSAO DA NOTATION QUE ESTÁ NO ENDPOINT ACESSADO)*/

		try {
			
			String login = requestContext.getSecurityContext().getUserPrincipal().getName();
			// Verifica se o usuario tem permissão pra executar esse metodo
			// Os niveis de acesso do metodo sobrepoe o da classe
			if (nivelPermisaoMetodo.isEmpty()) {
				checarPermissoes(nivelPermissaoClasse,login);
			} else {
				checarPermissoes(nivelPermisaoMetodo,login);
			}

		} catch (Exception e) {
			//Se caso o usuario não possui permissao é dado um execption, 
			//e retorna um resposta com o status 403 FORBIDDEN 
			requestContext.abortWith(
					Response.status(Response.Status.FORBIDDEN).build());
		}
	}
	//Metodo que extrai os niveis de permissao que foram definidos no @Seguro
	private List<UserRoles> extrairUserRoles(AnnotatedElement annotatedElement) {
		System.out.println("METODO DE EXTRACAO DE PERMISSAO DOS USUARIOS: "+annotatedElement);
		if (annotatedElement == null) {
			return new ArrayList<UserRoles>();
		} else {
			Seguro secured = annotatedElement.getAnnotation(Seguro.class);
			if (secured == null) {
				return new ArrayList<UserRoles>();
			} else {
				UserRoles[] allowedRoles = secured.value();
				System.out.println("VARIAVEL IGUALANDO AO SECURED.VALUE: "+allowedRoles);
				return Arrays.asList(allowedRoles);
			}
		}
	}
	//Verifica se o usuario tem permissao pra executar o metodo, se não for definido nenhum nivel de acesso no @Seguro,
	//Entao todos vao poder executar desde que possuam um token valido
	private void checarPermissoes(List<UserRoles> nivelPermissaoPermitidos,String login) throws Exception {
		try {
			if(nivelPermissaoPermitidos.isEmpty())
				return;
			
			boolean temPermissao = false;
			//Busca quais os niveis de acesso o usuario tem.
			UserRoles nivelPermissaoUsuario = new LoginResource().getUserRoles(login);
			System.out.println("NIVEL DE PERMISSAO DO USUARIO PASSADO PELO METODO GET USER ROLES: "+nivelPermissaoUsuario);
			
			for (UserRoles nivelPermissao : nivelPermissaoPermitidos) {
				if(nivelPermissao.equals(nivelPermissaoUsuario))
				{
					temPermissao = true;
					break;
				}
			}
			
			if(!temPermissao)
				throw new Exception("Cliente não possui o nível de permissão para esse método");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
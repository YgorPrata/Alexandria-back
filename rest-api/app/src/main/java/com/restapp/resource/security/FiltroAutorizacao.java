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
//Defini que a @seguro que vai utilizar essa classe
@Seguro
//Indica que essa classe vai prover a funcionalidade pra @seguro não o contario
@Provider
//E prioridade de execucao, pois podemos ter outras classe filtro
//que devem ser executas em uma ordem expecifica
//Nesse caso vai ser executada depois do FiltroAutenticacao,
//pois a prioridade AUTHENTICATION é maio que o do AUTHORIZATION
@Priority(Priorities.AUTHORIZATION)
public class FiltroAutorizacao implements ContainerRequestFilter {
	//O JAX-RS faz a injeção do ResourceInfoque vai ter os informações
	//do metodo que ta sendo verificado 
	@Context
	private ResourceInfo resourceInfo;
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Pega a classe que contem URL requisitada 
		// E extrai os nivel de permissão dela
		/*TRATA A AUTORIZAÇÃO A NÍVEL DE CLASSE*/
		Class<?> classe = resourceInfo.getResourceClass();
		System.out.println("CLASSE DO CLASS<?> RESOURCE INFO: "+classe);
		List<UserRoles> nivelPermissaoClasse = extrairUserRoles(classe);
		System.out.println("NIVEL DE PERMISSAO DA CLASSE: "+nivelPermissaoClasse);
		/*AQUI É EXTRAÍDO O NÍVEL DE PERMISSAO A NIVEL DE CLASSE E PASSADO PARA O BLOCO SEGUINTE*/

		// Pega o metodo que contem URL requisitada 
		// E extrai os nivel de permissão dele
		/*TRATA A AUTORIZAÇÃO A NÍVEL DE MÉTODO (PERMISSAO DA NOTATION QUE ESTÁ NO ENDPOINT ACESSADO)*/
		Method metodo = resourceInfo.getResourceMethod();
		List<UserRoles> nivelPermisaoMetodo = extrairUserRoles(metodo);
		System.out.println("NIVEL DE PERMISSAO METODO: "+nivelPermisaoMetodo);
		/*AQUI É EXTRAÍDO O NÍVEL DE PERMISSAO A NIVEL DE METODO E PASSADO PARA O BLOCO SEGUINTE 
		(PERMISSAO DA NOTATION QUE ESTÁ NO ENDPOINT ACESSADO)*/

		try {
			//Como modificamos o securityContext na hora de validar o token, para podemos pegar
			//O login do usuario, para fazer a verificação se ele tem o nivel de permissao necessario
			//para esse endpoint
			String login = requestContext.getSecurityContext().getUserPrincipal().getName();
			System.out.println("LOGIN CRIADO NO FILTRO DE AUTORIZACAO: "+login);
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
			System.out.println("OBJETO DO TIPO SEGURO PASSANDO O ANNOTATED PEGANDO A ANOTACAO DA CLASSE SEGURO: "+secured);
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
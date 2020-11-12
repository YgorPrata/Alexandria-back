package com.restapp.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.restapp.resource.security.Seguro;
import com.restapp.resource.security.UserRoles;

@Path("/conversor")
public class TesteResource {

	@Seguro({UserRoles.USER})
	@GET
	@Path("quilometrosParaMilhas/{km}")
	public Response quilometroParaMilha(@PathParam("km") Double quilometros){
		quilometros = quilometros / 1.6;
		System.out.println("veio");
		return Response.ok(quilometros).build();
	}
	
	@Seguro({UserRoles.ADMIN})
	@GET
	@Path("milhasParaQuilometros/{milhas}")
	public Response milhasParaQuilometros(@PathParam("milhas")Double milhas){

		milhas = milhas * 1.6;

		return Response.ok(milhas).build();

	}
}

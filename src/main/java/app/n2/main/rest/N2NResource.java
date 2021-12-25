package app.n2.main.rest;

import app.n2.main.service.N2NAuthentication;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/")
public class N2NResource {

    N2NAuthentication n2NAuthentication = new N2NAuthentication();

    @POST
    @Path("/login")
    public Response getLogin(@Context HttpHeaders headers){
        return Response.ok().build();
    }

    @POST
    @Path("/register")
    public Response getRegister(@Context HttpHeaders headers){
        n2NAuthentication.registerUser();
        return Response.ok().build();
    }

}

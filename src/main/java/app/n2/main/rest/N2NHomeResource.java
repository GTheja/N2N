package app.n2.main.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/home")
public class N2NHomeResource {

    @GET
    @Path("/")
    public Response getHome(){
        return Response.ok("Hello").build();
    }
}

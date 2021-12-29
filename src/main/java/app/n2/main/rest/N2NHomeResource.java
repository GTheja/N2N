package app.n2.main.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/home")
public class N2NHomeResource {

    @POST
    @Path("/")
    public Response getHome(){
        //checks for user email and password present in db or not
        //check for email if email no present throw error.
        //check for password if password not present throw error.
        //If yes redirect to home page
        //if not throw error with proper message.
        return Response.ok().build();
    }
}

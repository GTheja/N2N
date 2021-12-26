package app.n2.main.rest;

import app.n2.main.service.N2NAuthentication;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@Path("/")
public class N2NResource {

    N2NAuthentication n2NAuthentication = new N2NAuthentication();

    @GET
    @Path("/login")
    public Response getLogin(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("login.html");
        return Response.ok(resourceAsStream).build();
    }

    @POST
    @Path("/register")
    public Response getRegister(@Context HttpHeaders headers){
        return Response.ok().build();
    }

    @POST
    @Path("/home")
    public Response getRegister(@FormParam("email") String email, @FormParam("password") String password){
        //checks for user email and password present in db or not
        //check for email if email no present throw error.
        //check for password if password not present throw error.
        //If yes redirect to home page
        //if not throw error with proper message.
        return Response.ok().build();
    }

}

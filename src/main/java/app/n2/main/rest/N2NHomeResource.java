package app.n2.main.rest;

import app.n2.main.account.ProfileDTO;
import app.n2.main.service.N2NHomeService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.InputStream;

import static app.n2.main.utils.N2NConstants.PROFILE_ID;

@Path("/home")
public class N2NHomeResource {

    private final N2NHomeService n2NHomeService = new N2NHomeService();

    @GET
    @Path("/")
    public Response getHome(@Context HttpHeaders headers){
        n2NHomeService.addAccountToDB(headers);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("home.html");
        return Response.ok(resourceAsStream).build();
    }

    @POST
    @Path("/profile")
    public Response createProfileInfo(){
        return Response.ok().build();
    }

    @GET
    @Path("/profile")
    public Response getProfile(@QueryParam(PROFILE_ID) String profileId){
        ProfileDTO profileDB = n2NHomeService.getProfileDB(profileId);
        return Response.ok().build();
    }

}

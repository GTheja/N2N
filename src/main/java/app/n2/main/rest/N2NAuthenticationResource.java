package app.n2.main.rest;

import app.n2.main.service.N2NAuthentication;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

import static app.n2.main.utils.Validations.*;

@Path("/")
public class N2NAuthenticationResource {

    N2NAuthentication n2NAuthentication = new N2NAuthentication();

    @GET
    @Path("/login")
    public Response getLogin(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("login.html");
        return Response.ok(resourceAsStream).build();
    }

    @GET
    @Path("/register")
    public Response getRegisterPage(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("register.html");
        return Response.ok(resourceAsStream).build();
    }

    @POST
    @Path("/register")
    public Response getRegister(@FormParam("email") String email,
                                @FormParam("password") String password,
    @FormParam("confirmPassword") String confirmPassword, @FormParam("username") String username){
        isEmailPresent(email);
        isPasswordPresent(password);
        isConfirmPasswordPresent(confirmPassword);
        isUsernamePresent(username);
        if(!(Objects.equals(password, confirmPassword))){
            throw new IllegalArgumentException("Confirm password is not matching");
        }
        String code = n2NAuthentication.registerUser(email, username, password);
        n2NAuthentication.sendEmail(email, code);
        return Response.ok().build();
    }

    @GET
    @Path("/verify")
    public Response getVerifyCode(@QueryParam("code") String code){
        n2NAuthentication.verifyCode(code);
        String redirectURI = "http://localhost:8080/login";
        return Response.temporaryRedirect(URI.create(redirectURI)).status(302).build();
    }

    @POST
    @Path("/login")
    public Response getLoginVerification(@FormParam("email") String email,
                                         @FormParam("password") String password){
        isEmailPresent(email);
        isPasswordPresent(password);
        n2NAuthentication.getLoginVerification(email, password);
        String redirectHomeUrl = "http://localhost:8080/home";
        return Response.temporaryRedirect(URI.create(redirectHomeUrl)).status(302).build();
    }

}

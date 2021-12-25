package app.n2.main.service;

import app.n2.main.authentication.RegisterDTO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class N2NAuthentication {

    public String registerUser(){
        try {
            getHashedPassword("theja", "1234");
            RegisterDTO  registerDTO = new RegisterDTO("","","","");
            return null;
        }catch (NoSuchAlgorithmException cause){
            cause.printStackTrace();
            throw new IllegalStateException("Failed to register user");
        }
    }

    private String getHashedPassword(String hash, String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update((hash + password).getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(digest.digest()));
    }
}

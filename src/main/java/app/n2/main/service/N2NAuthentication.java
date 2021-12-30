package app.n2.main.service;

import app.n2.main.authentication.RegisterDTO;
import app.n2.main.database.AuthenticationDB;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;


public class N2NAuthentication {
    private final AuthenticationDB authenticationDB = new AuthenticationDB();
    private final MailService service = new MailService();

    public void getLoginVerification(String email, String password){
        try {
            RegisterDTO registerDTO = authenticationDB.readWithEmail(email);
            if(!Objects.equals(registerDTO.getEmail(), email) &&
                    !Objects.equals(registerDTO.getPassword(), getHashedPassword(email, password))){
                throw new IllegalStateException("Invalid email and password");
            }

        }catch (NoSuchAlgorithmException cause){
            throw new IllegalStateException("Check your credentials");
        }
    }

    public String registerUser(String email, String username, String password){
        try {
            String code = UUID.randomUUID().toString();
            RegisterDTO  registerDTO = new RegisterDTO(email,username,getHashedPassword(email, password),code);
            authenticationDB.createDB(registerDTO.getEmail(),registerDTO.getUsername()
                    ,registerDTO.getPassword(), RegisterDTO.isAuthStates.NOTAUTHENTICATED.toString()
                    ,registerDTO.getCode());
            return code;
        }catch (NoSuchAlgorithmException cause){
            cause.printStackTrace();
            throw new IllegalStateException("Failed to register user");
        }
    }

    public RegisterDTO verifyCode(String code){
        RegisterDTO registerDTO = authenticationDB.readDB(code);
        String codeDB = registerDTO.getCode();
        if(codeDB.equals(code)){
            authenticationDB.updateDB(registerDTO.email,
                    RegisterDTO.isAuthStates.AUTHENTICATED.toString());
            return new RegisterDTO(registerDTO.email,
                    registerDTO.username,
                    registerDTO.password, code);
        }
        throw new IllegalStateException("Invalid code");
    }

    public void sendEmail(String email, String code){
        String verifyUrl = "http://localhost:8080/verify?code="+code;
        String subject = "Verification link to verify email";
        service.sendEmail(email, subject, verifyUrl);
    }


    private String getHashedPassword(String hash, String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update((hash + password).getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(digest.digest()));
    }
}

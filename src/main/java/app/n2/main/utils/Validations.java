package app.n2.main.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Validations {

    public static void isEmailPresent(String email){
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email Not found");
        }
    }
    public static void isPasswordPresent(String password){
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("Password Not found");
        }
    }
    public static void isConfirmPasswordPresent(String confirmPassword){
        if(confirmPassword == null || confirmPassword.isEmpty()){
            throw new IllegalArgumentException("Confirm password Not found");
        }
    }
    public static void isUsernamePresent(String username){
        if(username == null || username.isEmpty()){
            throw new IllegalArgumentException("Username Not found");
        }
    }

    public static String getCreateAccountId(String firstName) {
        String name = Base64.getEncoder().encodeToString(firstName.getBytes(StandardCharsets.UTF_8));
        String res2 = "Account_"+name;
        long count = 1919197;
        String res5 = "00" + count;
        return res2 + res5;
    }

    public static String getCreateProfileID(String firstName) {
        String res2 = "Profile_"+firstName;
        long count = 1919197;
        String res5 = "00" + count;
        return res2 + res5;
    }


}

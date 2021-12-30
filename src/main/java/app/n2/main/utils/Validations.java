package app.n2.main.utils;

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


}

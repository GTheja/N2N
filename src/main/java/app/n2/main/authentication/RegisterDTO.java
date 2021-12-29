package app.n2.main.authentication;

public class RegisterDTO {

    public enum isAuthStates{
        AUTHENTICATED,
        NOTAUTHENTICATED
    }

    public String email;
    public String username;
    public String password;
    public String code;

    public RegisterDTO(String email, String username, String password, String code) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCode() {
        return code;
    }
}

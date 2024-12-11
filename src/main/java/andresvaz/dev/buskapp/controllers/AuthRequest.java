package andresvaz.dev.buskapp.controllers;



public class AuthRequest {
    private String username;
    private String password;

    // Constructor vacío
    public AuthRequest() {
    }

    // Constructor con parámetros
    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package andresvaz.dev.buskapp.entities;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Musico implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String password;
    private String email;

    // Rol del usuario, ejemplo: "ROLE_USER" o "ROLE_ADMIN"
    private String role;

    @OneToMany(mappedBy = "musico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Repertorio> repertorios;

    @OneToMany(mappedBy = "musico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cancion> canciones;

    // Constructor vacío
    public Musico() {
    }

    // Constructor con parámetros
    public Musico(String nombre, String password, String email, String role) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Repertorio> getRepertorios() {
        return repertorios;
    }

    public void setRepertorios(List<Repertorio> repertorios) {
        this.repertorios = repertorios;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    // Métodos de UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email; // Utilizamos el email como el nombre de usuario
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes personalizar esta lógica si necesitas manejar cuentas expiradas
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Personaliza si necesitas manejar cuentas bloqueadas
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Personaliza si necesitas manejar credenciales expiradas
    }

    @Override
    public boolean isEnabled() {
        return true; // Personaliza si necesitas manejar el estado de la cuenta (activo/inactivo)
    }

    // Método toString (opcional)
    @Override
    public String toString() {
        return "Musico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

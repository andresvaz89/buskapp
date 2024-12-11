package andresvaz.dev.buskapp.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Artist implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Aseguramos que el nombre sea único para autenticación
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // Ejemplo: "ROLE_USER" o "ROLE_ADMIN"

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Setlist> Setlists;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs;

    // Constructor vacío
    public Artist() {
    }

    // Constructor con parámetros
    public Artist(String username, String password, String email, String role) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
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

    public List<Setlist> getSetlists() {
        return Setlists;
    }

    public void setSetlists(List<Setlist> Setlists) {
        this.Setlists = Setlists;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    // Métodos de UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null || role.isBlank()) {
            throw new IllegalStateException("El rol del usuario no puede estar vacío");
        }
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Personaliza esta lógica si necesitas manejar cuentas expiradas
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

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

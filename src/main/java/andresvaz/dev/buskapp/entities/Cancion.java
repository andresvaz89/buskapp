package andresvaz.dev.buskapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String artistaOriginal;
    private String tonalidad;
    private String genero;

    @ManyToOne
    @JoinColumn(name = "musico_id", nullable = false)
    private Musico musico;

    // Constructor vacío
    public Cancion() {
    }

    // Constructor con parámetros
    public Cancion(String titulo, String artistaOriginal, String tonalidad, String genero, Musico musico) {
        this.titulo = titulo;
        this.artistaOriginal = artistaOriginal;
        this.tonalidad = tonalidad;
        this.genero = genero;
        this.musico = musico;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtistaOriginal() {
        return artistaOriginal;
    }

    public void setArtistaOriginal(String artistaOriginal) {
        this.artistaOriginal = artistaOriginal;
    }

    public String getTonalidad() {
        return tonalidad;
    }

    public void setTonalidad(String tonalidad) {
        this.tonalidad = tonalidad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Musico getMusico() {
        return musico;
    }

    public void setMusico(Musico musico) {
        this.musico = musico;
    }

    // Método toString (opcional, útil para depuración)
    @Override
    public String toString() {
        return "Cancion{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", artistaOriginal='" + artistaOriginal + '\'' +
                ", tonalidad='" + tonalidad + '\'' +
                ", genero='" + genero + '\'' +
                ", musico=" + (musico != null ? musico.getNombre() : "N/A") +
                '}';
    }
}

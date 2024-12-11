package andresvaz.dev.buskapp.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Repertorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaConcierto;

    @ManyToOne
    @JoinColumn(name = "musico_id")
    private Musico musico;

    @ManyToMany
    @JoinTable(
        name = "repertorio_cancion",
        joinColumns = @JoinColumn(name = "repertorio_id"),
        inverseJoinColumns = @JoinColumn(name = "cancion_id")
    )
    private List<Cancion> canciones;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaConcierto() {
        return fechaConcierto;
    }

    public void setFechaConcierto(LocalDate fechaConcierto) {
        this.fechaConcierto = fechaConcierto;
    }

    public Musico getMusico() {
        return musico;
    }

    public void setMusico(Musico musico) {
        this.musico = musico;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}

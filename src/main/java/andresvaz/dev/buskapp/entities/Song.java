package andresvaz.dev.buskapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String originalArtist;
    private String key;
    private String genre;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    // Constructor vacío
    public Song() {
    }

    // Constructor con parámetros
    public Song(String title, String originalArtist, String key, String genre, Artist artist) {
        this.title = title;
        this.originalArtist = originalArtist;
        this.key = key;
        this.genre = genre;
        this.artist = artist;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalArtist() {
        return originalArtist;
    }

    public void setOriginalArtist(String originalArtist) {
        this.originalArtist = originalArtist;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    // Método toString (opcional, útil para depuración)
    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", originalArtist='" + originalArtist + '\'' +
                ", key='" + key + '\'' +
                ", genre='" + genre + '\'' +
                ", artist=" + (artist != null ? artist.getUsername() : "N/A") +
                '}';
    }
}

package andresvaz.dev.buskapp.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Setlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    // Constructor por defecto
    public Setlist() {
    }

   
    public Setlist(Artist artist, Song song) {
        this.artist = artist;
        this.song = song;
    }

   
    public Long getId() {
        return id;
    }

    public Artist getArtist() {
        return artist;
    }

    public Song getSong() {
        return song;
    }

   
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

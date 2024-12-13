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
    private String songKey;
    private String originalArtist;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

  
    public Song() {
    }


    public Song(String title, String songKey, String originalArtist, Artist artist) {
        this.title = title;
        this.songKey = songKey;
        this.originalArtist = originalArtist;
        this.artist = artist;
    }
    
   
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSongKey() {
        return songKey;
    }

    public String getOriginalArtist() {
        return originalArtist;
    }

    public Artist getArtist() {
        return artist;
    }

   
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setSongKey(String songKey) {
        this.songKey = songKey;
    }

    public void setOriginalArtist(String originalArtist) {
        this.originalArtist = originalArtist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}

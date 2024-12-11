package andresvaz.dev.buskapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andresvaz.dev.buskapp.entities.Song;
import andresvaz.dev.buskapp.entities.Artist;
import andresvaz.dev.buskapp.repositories.SongRepository;
import andresvaz.dev.buskapp.repositories.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public List<Song> getAllSongsByArtist(Long artistId) {
        return songRepository.findByArtistId(artistId);
    }

    public Song addSong(Song song, Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("Músico no encontrado"));
        song.setArtist(artist);
        return songRepository.save(song);
    }

    public Song updateSong(Long id, Song updatedSong) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Canción no encontrada"));
        song.setTitle(updatedSong.getTitle());
        song.setOriginalArtist(updatedSong.getOriginalArtist());
        song.setKey(updatedSong.getKey());
        song.setGenre(updatedSong.getGenre());
        return songRepository.save(song);
    }

    public void deleteSong(Long id) {
        if (!songRepository.existsById(id)) {
            throw new EntityNotFoundException("Canción no encontrada");
        }
        songRepository.deleteById(id);
    }
}

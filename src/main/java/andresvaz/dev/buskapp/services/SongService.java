/* package andresvaz.dev.buskapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import andresvaz.dev.buskapp.entities.Song;
import andresvaz.dev.buskapp.entities.Artist;
import andresvaz.dev.buskapp.repositories.SongRepository;
import andresvaz.dev.buskapp.repositories.ArtistRepository;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song createSong(Song song, String userEmail) {
        Artist artist = artistRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No autorizado"));
        
        song.setArtist(artist);
        return songRepository.save(song);
    }

    public Song updateSong(Long id, Song updatedSong, String userEmail) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Canción no encontrada"));

        if (!song.getArtist().getEmail().equals(userEmail)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes actualizar esta canción");
        }

        song.setTitle(updatedSong.getTitle());
        song.setOriginalArtist(updatedSong.getOriginalArtist());
        song.setSongkey(updatedSong.getSongkey());
        song.setGenre(updatedSong.getGenre());

        return songRepository.save(song);
    }

    public void deleteSong(Long id, String userEmail) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Canción no encontrada"));

        if (!song.getArtist().getEmail().equals(userEmail)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes eliminar esta canción");
        }

        songRepository.delete(song);
    }
}
 */
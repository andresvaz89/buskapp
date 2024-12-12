package andresvaz.dev.buskapp.services;

import andresvaz.dev.buskapp.entities.Song;
import andresvaz.dev.buskapp.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    /**
     * Crear una canción
     */
    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    /**
     * Obtener todas las canciones
     */
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    /**
     * Obtener una canción por ID
     */
    public Optional<Song> getSongById(Long id) {
        return songRepository.findById(id);
    }

    /**
     * Actualizar una canción
     */
    public Song updateSong(Long id, Song songDetails) throws Exception {
        Optional<Song> songOptional = songRepository.findById(id);
        
        if (songOptional.isEmpty()) {
            throw new Exception("Song not found");
        }

        Song songToUpdate = songOptional.get();
        songToUpdate.setTitle(songDetails.getTitle());
        songToUpdate.setSongKey(songDetails.getSongKey());
        songToUpdate.setOriginalArtist(songDetails.getOriginalArtist());
        songToUpdate.setArtist(songDetails.getArtist());

        return songRepository.save(songToUpdate);
    }

    /**
     * Eliminar una canción
     */
    public void deleteSong(Long id) throws Exception {
        if (!songRepository.existsById(id)) {
            throw new Exception("Song not found");
        }

        songRepository.deleteById(id);
    }
}

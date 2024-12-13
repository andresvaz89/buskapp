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


    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

  
    public Optional<Song> getSongById(Long id) {
        return songRepository.findById(id);
    }

  
    public Song updateSong(Long id, Song songDetails) {
        Song songToUpdate = songRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Song with id " + id + " not found"));
    
       
        songToUpdate.setTitle(songDetails.getTitle());
        songToUpdate.setSongKey(songDetails.getSongKey());
        songToUpdate.setOriginalArtist(songDetails.getOriginalArtist());
        songToUpdate.setArtist(songDetails.getArtist());
    
    
        return songRepository.save(songToUpdate);
    }
    
  
    public void deleteSong(Long id) throws Exception {
        if (!songRepository.existsById(id)) {
            throw new Exception("Song not found");
        }

        songRepository.deleteById(id);
    }
}

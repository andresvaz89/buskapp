package andresvaz.dev.buskapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andresvaz.dev.buskapp.entities.Song;
import andresvaz.dev.buskapp.entities.Artist;
import andresvaz.dev.buskapp.entities.Setlist;
import andresvaz.dev.buskapp.repositories.SongRepository;
import andresvaz.dev.buskapp.repositories.ArtistRepository;
import andresvaz.dev.buskapp.repositories.SetlistRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SetlistService {

    @Autowired
    private SetlistRepository setlistRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongRepository songRepository;

    public List<Setlist>getAllSetlists() {
        return setlistRepository.findAll();
    }

    public List<Setlist> getAllSetlistsByArtist(Long artistId) {
        return setlistRepository.findByArtistId(artistId);
    }

    public Setlist createSetlist(Setlist Setlist, Long artistId, List<Long> songId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("Músico no encontrado"));

        List<Song> songs = songRepository.findAllById(songId);
        if (songs.size() != songId.size()) {
            throw new IllegalArgumentException("Una o más songs no existen");
        }

        Setlist.setArtist(artist);
        Setlist.setSongs(songs);
        return setlistRepository.save(Setlist);
    }

    public Setlist updateSetlist(Long id, Setlist updatedSetlist, List<Long> songId) {
        Setlist Setlist = setlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setlist no encontrado"));

        List<Song> songs = songRepository.findAllById(songId);
        if (songs.size() != songId.size()) {
            throw new IllegalArgumentException("Una o más songs no existen");
        }

        Setlist.setGigDate(updatedSetlist.getGigDate());
        Setlist.setSongs(songs);
        return setlistRepository.save(Setlist);
    }

    public void deleteSetlist(Long id) {
        if (!setlistRepository.existsById(id)) {
            throw new EntityNotFoundException("Setlist no encontrado");
        }
        setlistRepository.deleteById(id);
    }
}

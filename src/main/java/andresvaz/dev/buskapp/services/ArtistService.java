package andresvaz.dev.buskapp.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andresvaz.dev.buskapp.entities.Artist;
import andresvaz.dev.buskapp.repositories.ArtistRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public Artist registerArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtist(Long id) {
        return artistRepository.findById(id).orElse(null);
    }

    public Artist updateArtist(Long id, Artist artistDetails) {
        Optional<Artist> artistOptional = artistRepository.findById(id);
        if (artistOptional.isPresent()) {
            Artist artist = artistOptional.get();
            artist.setName(artistDetails.getName());
            artist.setEmail(artistDetails.getEmail());
            artist.setPassword(artistDetails.getPassword());
            return artistRepository.save(artist);
        }
        return null;
    }

    public boolean deleteArtist(Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
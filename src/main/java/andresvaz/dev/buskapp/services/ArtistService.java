package andresvaz.dev.buskapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andresvaz.dev.buskapp.entities.Artist;
import andresvaz.dev.buskapp.repositories.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> getArtistById(Long id) {
        return artistRepository.findById(id);
    }

    public Artist addArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist updateArtist(Long id, Artist updatedArtist) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artista no encontrado"));
        artist.setUsername(updatedArtist.getUsername());
        artist.setEmail(updatedArtist.getEmail());
        artist.setPassword(updatedArtist.getPassword());
        return artistRepository.save(artist);
    }

    public void deleteArtist(Long id) {
        if (!artistRepository.existsById(id)) {
            throw new EntityNotFoundException("Artista no encontrado");
        }
        artistRepository.deleteById(id);
    }
}

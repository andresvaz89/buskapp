package andresvaz.dev.buskapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import andresvaz.dev.buskapp.entities.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByEmail(String email);
    Optional<Artist> findByUsername(String username);
}

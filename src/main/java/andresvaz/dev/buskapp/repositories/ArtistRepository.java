package andresvaz.dev.buskapp.repositories;



import andresvaz.dev.buskapp.entities.Artist;



import org.springframework.data.jpa.repository.JpaRepository;



public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByEmail(String email);
}
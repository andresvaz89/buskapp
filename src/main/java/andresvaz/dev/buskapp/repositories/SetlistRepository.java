package andresvaz.dev.buskapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import andresvaz.dev.buskapp.entities.Setlist;

public interface SetlistRepository extends JpaRepository<Setlist, Long> {
    List<Setlist> findByArtistId(Long artistId);
}

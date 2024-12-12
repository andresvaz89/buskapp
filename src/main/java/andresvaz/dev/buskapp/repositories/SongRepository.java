package andresvaz.dev.buskapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import andresvaz.dev.buskapp.entities.Song;



public interface SongRepository extends JpaRepository<Song, Long> {
}
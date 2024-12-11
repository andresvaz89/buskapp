package andresvaz.dev.buskapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import andresvaz.dev.buskapp.entities.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Long> {
    List<Cancion> findByMusicoId(Long musicoId);
}

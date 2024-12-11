package andresvaz.dev.buskapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import andresvaz.dev.buskapp.entities.Repertorio;

public interface RepertorioRepository extends JpaRepository<Repertorio, Long> {
    List<Repertorio> findByMusicoId(Long musicoId);
}

package andresvaz.dev.buskapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import andresvaz.dev.buskapp.entities.Musico;

public interface MusicoRepository extends JpaRepository<Musico, Long> {
    Optional<Musico> findByEmail(String email);
    Optional<Musico> findByUsername(String username);
}

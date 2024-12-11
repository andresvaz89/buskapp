package andresvaz.dev.buskapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andresvaz.dev.buskapp.entities.Musico;
import andresvaz.dev.buskapp.repositories.MusicoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MusicoService {

    @Autowired
    private MusicoRepository musicoRepository;

    public List<Musico> obtenerTodosLosMusicos() {
        return musicoRepository.findAll();
    }

    public Optional<Musico> obtenerMusicoPorId(Long id) {
        return musicoRepository.findById(id);
    }

    public Musico crearMusico(Musico musico) {
        return musicoRepository.save(musico);
    }

    public Musico actualizarMusico(Long id, Musico musicoActualizado) {
        Musico musico = musicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Músico no encontrado"));
        musico.setNombre(musicoActualizado.getNombre());
        musico.setEmail(musicoActualizado.getEmail());
        musico.setPassword(musicoActualizado.getPassword());
        return musicoRepository.save(musico);
    }

    public void eliminarMusico(Long id) {
        if (!musicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Músico no encontrado");
        }
        musicoRepository.deleteById(id);
    }
}

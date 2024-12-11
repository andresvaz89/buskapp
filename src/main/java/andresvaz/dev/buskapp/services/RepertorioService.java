package andresvaz.dev.buskapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andresvaz.dev.buskapp.entities.Cancion;
import andresvaz.dev.buskapp.entities.Musico;
import andresvaz.dev.buskapp.entities.Repertorio;
import andresvaz.dev.buskapp.repositories.CancionRepository;
import andresvaz.dev.buskapp.repositories.MusicoRepository;
import andresvaz.dev.buskapp.repositories.RepertorioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RepertorioService {

    @Autowired
    private RepertorioRepository repertorioRepository;

    @Autowired
    private MusicoRepository musicoRepository;

    @Autowired
    private CancionRepository cancionRepository;

    public List<Repertorio> obtenerTodosLosRepertorios() {
        return repertorioRepository.findAll();
    }

    public List<Repertorio> obtenerRepertoriosPorMusico(Long musicoId) {
        return repertorioRepository.findByMusicoId(musicoId);
    }

    public Repertorio crearRepertorio(Repertorio repertorio, Long musicoId, List<Long> cancionesIds) {
        Musico musico = musicoRepository.findById(musicoId)
                .orElseThrow(() -> new EntityNotFoundException("Músico no encontrado"));

        List<Cancion> canciones = cancionRepository.findAllById(cancionesIds);
        if (canciones.size() != cancionesIds.size()) {
            throw new IllegalArgumentException("Una o más canciones no existen");
        }

        repertorio.setMusico(musico);
        repertorio.setCanciones(canciones);
        return repertorioRepository.save(repertorio);
    }

    public Repertorio actualizarRepertorio(Long id, Repertorio repertorioActualizado, List<Long> cancionesIds) {
        Repertorio repertorio = repertorioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Repertorio no encontrado"));

        List<Cancion> canciones = cancionRepository.findAllById(cancionesIds);
        if (canciones.size() != cancionesIds.size()) {
            throw new IllegalArgumentException("Una o más canciones no existen");
        }

        repertorio.setFechaConcierto(repertorioActualizado.getFechaConcierto());
        repertorio.setCanciones(canciones);
        return repertorioRepository.save(repertorio);
    }

    public void eliminarRepertorio(Long id) {
        if (!repertorioRepository.existsById(id)) {
            throw new EntityNotFoundException("Repertorio no encontrado");
        }
        repertorioRepository.deleteById(id);
    }
}

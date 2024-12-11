package andresvaz.dev.buskapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andresvaz.dev.buskapp.entities.Cancion;
import andresvaz.dev.buskapp.entities.Musico;
import andresvaz.dev.buskapp.repositories.CancionRepository;
import andresvaz.dev.buskapp.repositories.MusicoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private MusicoRepository musicoRepository;

    public List<Cancion> obtenerTodasLasCanciones() {
        return cancionRepository.findAll();
    }

    public List<Cancion> obtenerCancionesPorMusico(Long musicoId) {
        return cancionRepository.findByMusicoId(musicoId);
    }

    public Cancion crearCancion(Cancion cancion, Long musicoId) {
        Musico musico = musicoRepository.findById(musicoId)
                .orElseThrow(() -> new EntityNotFoundException("Músico no encontrado"));
        cancion.setMusico(musico);
        return cancionRepository.save(cancion);
    }

    public Cancion actualizarCancion(Long id, Cancion cancionActualizada) {
        Cancion cancion = cancionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Canción no encontrada"));
        cancion.setTitulo(cancionActualizada.getTitulo());
        cancion.setArtistaOriginal(cancionActualizada.getArtistaOriginal());
        cancion.setTonalidad(cancionActualizada.getTonalidad());
        cancion.setGenero(cancionActualizada.getGenero());
        return cancionRepository.save(cancion);
    }

    public void eliminarCancion(Long id) {
        if (!cancionRepository.existsById(id)) {
            throw new EntityNotFoundException("Canción no encontrada");
        }
        cancionRepository.deleteById(id);
    }
}

package andresvaz.dev.buskapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import andresvaz.dev.buskapp.entities.Cancion;
import andresvaz.dev.buskapp.entities.Musico;
import andresvaz.dev.buskapp.repositories.CancionRepository;
import andresvaz.dev.buskapp.repositories.MusicoRepository;

import andresvaz.dev.buskapp.services.*;

@RestController
@RequestMapping("/canciones")
public class CancionController {
    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private MusicoRepository musicoRepository;

    @GetMapping
    public List<Cancion> listarCanciones() {
        return cancionRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('MUSICO')")
    public Cancion crearCancion(@RequestBody Cancion cancion, Authentication auth) {
        Musico musico = musicoRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        cancion.setMusico(musico);
        return cancionRepository.save(cancion);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MUSICO')")
    public Cancion actualizarCancion(@PathVariable Long id, @RequestBody Cancion cancionActualizada, Authentication auth) {
        Cancion cancion = cancionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!cancion.getMusico().getEmail().equals(auth.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        cancion.setTitulo(cancionActualizada.getTitulo());
        cancion.setArtistaOriginal(cancionActualizada.getArtistaOriginal());
        cancion.setTonalidad(cancionActualizada.getTonalidad());
        cancion.setGenero(cancionActualizada.getGenero());
        return cancionRepository.save(cancion);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MUSICO')")
    public void eliminarCancion(@PathVariable Long id, Authentication auth) {
        Cancion cancion = cancionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!cancion.getMusico().getEmail().equals(auth.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        cancionRepository.delete(cancion);
    }
}

package andresvaz.dev.buskapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andresvaz.dev.buskapp.entities.Musico;
import andresvaz.dev.buskapp.services.MusicoService;
import jakarta.persistence.EntityNotFoundException;



@RestController
@RequestMapping("/api/musicos")
public class MusicoController {

    @Autowired
    private MusicoService musicoService;

    @GetMapping
    public ResponseEntity<List<Musico>> obtenerTodosLosMusicos() {
        return ResponseEntity.ok(musicoService.obtenerTodosLosMusicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musico> obtenerMusicoPorId(@PathVariable Long id) {
        return musicoService.obtenerMusicoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Musico> crearMusico(@RequestBody Musico musico) {
        return new ResponseEntity<>(musicoService.crearMusico(musico), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musico> actualizarMusico(@PathVariable Long id, @RequestBody Musico musico) {
        try {
            return ResponseEntity.ok(musicoService.actualizarMusico(id, musico));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMusico(@PathVariable Long id) {
        try {
            musicoService.eliminarMusico(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

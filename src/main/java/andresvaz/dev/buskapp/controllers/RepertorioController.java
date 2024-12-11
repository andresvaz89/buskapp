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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import andresvaz.dev.buskapp.entities.Repertorio;
import andresvaz.dev.buskapp.services.RepertorioService;
import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/repertorios")
public class RepertorioController {

    @Autowired
    private RepertorioService repertorioService;

    @GetMapping
    public ResponseEntity<List<Repertorio>> obtenerTodosLosRepertorios() {
        return ResponseEntity.ok(repertorioService.obtenerTodosLosRepertorios());
    }

    @GetMapping("/musico/{musicoId}")
    public ResponseEntity<List<Repertorio>> obtenerRepertoriosPorMusico(@PathVariable Long musicoId) {
        return ResponseEntity.ok(repertorioService.obtenerRepertoriosPorMusico(musicoId));
    }

    @PostMapping("/musico/{musicoId}")
    public ResponseEntity<Repertorio> crearRepertorio(
            @PathVariable Long musicoId,
            @RequestBody Repertorio repertorio,
            @RequestParam List<Long> cancionesIds) {
        try {
            Repertorio nuevoRepertorio = repertorioService.crearRepertorio(repertorio, musicoId, cancionesIds);
            return new ResponseEntity<>(nuevoRepertorio, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repertorio> actualizarRepertorio(
            @PathVariable Long id,
            @RequestBody Repertorio repertorioActualizado,
            @RequestParam List<Long> cancionesIds) {
        try {
            return ResponseEntity.ok(repertorioService.actualizarRepertorio(id, repertorioActualizado, cancionesIds));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRepertorio(@PathVariable Long id) {
        try {
            repertorioService.eliminarRepertorio(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

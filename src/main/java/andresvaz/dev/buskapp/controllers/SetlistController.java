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

import andresvaz.dev.buskapp.entities.Setlist;
import andresvaz.dev.buskapp.services.SetlistService;
import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/setlists")
public class SetlistController {

    @Autowired
    private SetlistService setlistService;

    @GetMapping
    public ResponseEntity<List<Setlist>> getAllSetlists() {
        return ResponseEntity.ok(setlistService.getAllSetlists());
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<Setlist>> getAllSetlistsByArtist(@PathVariable Long artistId) {
        return ResponseEntity.ok(setlistService.getAllSetlistsByArtist(artistId));
    }

    @PostMapping("/artist/{artistId}")
    public ResponseEntity<Setlist> createSetlist(
            @PathVariable Long artistId,
            @RequestBody Setlist Setlist,
            @RequestParam List<Long> songId) {
        try {
            Setlist newSetlist = setlistService.createSetlist(Setlist, artistId, songId);
            return new ResponseEntity<>(newSetlist, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setlist> updateSetlist(
            @PathVariable Long id,
            @RequestBody Setlist updatedSetlist,
            @RequestParam List<Long> songId) {
        try {
            return ResponseEntity.ok(setlistService.updateSetlist(id, updatedSetlist, songId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetlist(@PathVariable Long id) {
        try {
            setlistService.deleteSetlist(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

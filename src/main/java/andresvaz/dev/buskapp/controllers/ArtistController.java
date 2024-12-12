package andresvaz.dev.buskapp.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import andresvaz.dev.buskapp.entities.Artist;
import andresvaz.dev.buskapp.services.ArtistService;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    /**
     * Crear un nuevo artista (registro).
     */
    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist createdArtist = artistService.registerArtist(artist);
        return ResponseEntity.ok(createdArtist);
    }

    /**
     * Obtener la lista de todos los artistas.
     */
    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    /**
     * Obtener un artista por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Artist artist = artistService.getArtist(id);
        if (artist != null) {
            return ResponseEntity.ok(artist);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Actualizar un artista por su ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @RequestBody Artist artistDetails) {
        Artist updatedArtist = artistService.updateArtist(id, artistDetails);
        if (updatedArtist != null) {
            return ResponseEntity.ok(updatedArtist);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Eliminar un artista por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        boolean deleted = artistService.deleteArtist(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
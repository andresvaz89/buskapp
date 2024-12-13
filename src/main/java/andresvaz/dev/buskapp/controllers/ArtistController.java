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

  
    @PostMapping
    public ResponseEntity<Artist> registerArtist(@RequestBody Artist artist) {
        Artist createdArtist = artistService.registerArtist(artist);
        return ResponseEntity.ok(createdArtist);
    }

   
    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Artist artist = artistService.getArtist(id);
        if (artist != null) {
            return ResponseEntity.ok(artist);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }
    @GetMapping("/adios")
    public String sayAdios() {
        return "Adios";
    }
    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @RequestBody Artist artistDetails) {
        Artist updatedArtist = artistService.updateArtist(id, artistDetails);
        if (updatedArtist != null) {
            return ResponseEntity.ok(updatedArtist);
        }
        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        boolean deleted = artistService.deleteArtist(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
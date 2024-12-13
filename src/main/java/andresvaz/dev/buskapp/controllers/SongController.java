package andresvaz.dev.buskapp.controllers;

import andresvaz.dev.buskapp.entities.Song;
import andresvaz.dev.buskapp.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping()
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        return ResponseEntity.ok(songService.addSong(song));
    }

    @GetMapping()
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Optional<Song> song = songService.getSongById(id);
        return song.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSong(@PathVariable Long id, @RequestBody Song songDetails) {
        try {
            Song updatedSong = songService.updateSong(id, songDetails);
            return ResponseEntity.ok(updatedSong);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        try {
            songService.deleteSong(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
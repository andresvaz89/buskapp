/* package andresvaz.dev.buskapp.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import andresvaz.dev.buskapp.entities.Song;
import andresvaz.dev.buskapp.services.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping
    public List<Song> listSongs() {
        return songService.getAllSongs();
    }

    @PostMapping
    @PreAuthorize("hasRole('MUSICO')")
    public Song createSong(@RequestBody Song song, Authentication auth) {
        return songService.createSong(song, auth.getName());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MUSICO')")
    public Song updateSong(@PathVariable Long id, @RequestBody Song updatedSong, Authentication auth) {
        return songService.updateSong(id, updatedSong, auth.getName());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MUSICO')")
    public void deleteSong(@PathVariable Long id, Authentication auth) {
        songService.deleteSong(id, auth.getName());
    }
}
 */
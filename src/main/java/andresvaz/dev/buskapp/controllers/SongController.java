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

import andresvaz.dev.buskapp.entities.Song;
import andresvaz.dev.buskapp.entities.Artist;
import andresvaz.dev.buskapp.repositories.SongRepository;
import andresvaz.dev.buskapp.repositories.ArtistRepository;

import andresvaz.dev.buskapp.services.*;

@RestController
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping
    public List<Song> listarSongs() {
        return songRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('MUSICO')")
    public Song crearSong(@RequestBody Song song, Authentication auth) {
        Artist artist = artistRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        song.setArtist(artist);
        return songRepository.save(song);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MUSICO')")
    public Song actualizarSong(@PathVariable Long id, @RequestBody Song updatedSong, Authentication auth) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!song.getArtist().getEmail().equals(auth.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        song.setTitle(updatedSong.getTitle());
        song.setOriginalArtist(updatedSong.getOriginalArtist());
        song.setKey(updatedSong.getKey());
        song.setGenre(updatedSong.getGenre());
        return songRepository.save(song);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MUSICO')")
    public void eliminarSong(@PathVariable Long id, Authentication auth) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!song.getArtist().getEmail().equals(auth.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        songRepository.delete(song);
    }
}

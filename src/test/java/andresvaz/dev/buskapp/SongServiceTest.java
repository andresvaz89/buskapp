package andresvaz.dev.buskapp;



import andresvaz.dev.buskapp.entities.Song;
import andresvaz.dev.buskapp.repositories.SongRepository;
import andresvaz.dev.buskapp.services.SongService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SongServiceTest {

    @InjectMocks
    private SongService songService;

    @Mock
    private SongRepository songRepository;

    private Song song;
    private List<Song> songList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear una canción de prueba
        song = new Song();
        song.setId(1L);
        song.setTitle("Test Song");
        song.setSongKey("C");
        song.setOriginalArtist("Test Artist");

        // Crear una lista de canciones
        songList = Arrays.asList(song);
    }

    @Test
    void testAddSong() {
        // Mock para la llamada de guardado
        when(songRepository.save(any(Song.class))).thenReturn(song);

        Song result = songService.addSong(song);

        assertNotNull(result);
        assertEquals(song.getTitle(), result.getTitle());
        assertEquals(song.getSongKey(), result.getSongKey());
        assertEquals(song.getOriginalArtist(), result.getOriginalArtist());

        verify(songRepository, times(1)).save(any(Song.class));
    }

    @Test
    void testGetAllSongs() {
        when(songRepository.findAll()).thenReturn(songList);

        List<Song> result = songService.getAllSongs();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(song.getId(), result.get(0).getId());

        verify(songRepository, times(1)).findAll();
    }

    @Test
    void testGetSongById() {
        when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        Optional<Song> result = songService.getSongById(1L);

        assertTrue(result.isPresent());
        assertEquals(song.getId(), result.get().getId());

        verify(songRepository, times(1)).findById(1L);
    }

    @Test
    void testGetSongById_NotFound() {
        when(songRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Song> result = songService.getSongById(2L);

        assertFalse(result.isPresent());

        verify(songRepository, times(1)).findById(2L);
    }

    @Test
    void testUpdateSong() {
        Song updatedSongDetails = new Song();
        updatedSongDetails.setTitle("Updated Song");
        updatedSongDetails.setSongKey("D");
        updatedSongDetails.setOriginalArtist("Updated Artist");

        when(songRepository.findById(1L)).thenReturn(Optional.of(song));
        when(songRepository.save(any(Song.class))).thenReturn(song);

        Song result = songService.updateSong(1L, updatedSongDetails);

        assertNotNull(result);
        assertEquals("Updated Song", result.getTitle());
        assertEquals("D", result.getSongKey());
        assertEquals("Updated Artist", result.getOriginalArtist());

        verify(songRepository, times(1)).findById(1L);
        verify(songRepository, times(1)).save(any(Song.class));
    }

    @Test
    void testUpdateSong_NotFound() {
        Song updatedSongDetails = new Song();
        updatedSongDetails.setTitle("Nonexistent Song");
        updatedSongDetails.setSongKey("F");
        updatedSongDetails.setOriginalArtist("Artist");

        when(songRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            songService.updateSong(2L, updatedSongDetails);
        });

        assertEquals("Song with id 2 not found", exception.getMessage());

        verify(songRepository, times(1)).findById(2L);
        verify(songRepository, never()).save(any(Song.class));
    }

    @Test
    void testDeleteSong() throws Exception {
        when(songRepository.existsById(1L)).thenReturn(true);
        doNothing().when(songRepository).deleteById(1L);

        assertDoesNotThrow(() -> songService.deleteSong(1L));

        verify(songRepository, times(1)).existsById(1L);
        verify(songRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteSong_NotFound() throws Exception {
        when(songRepository.existsById(2L)).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () -> {
            songService.deleteSong(2L);
        });

        assertEquals("Song not found", exception.getMessage());

        verify(songRepository, times(1)).existsById(2L);
        verify(songRepository, never()).deleteById(2L);
    }
}

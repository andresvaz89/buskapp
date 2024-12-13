package andresvaz.dev.buskapp;



import andresvaz.dev.buskapp.entities.Artist;
import andresvaz.dev.buskapp.repositories.ArtistRepository;
import andresvaz.dev.buskapp.services.ArtistService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArtistServiceTest {

    @InjectMocks
    private ArtistService artistService;

    @Mock
    private ArtistRepository artistRepository;

    private Artist artist;
    private List<Artist> artistList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear un artista de prueba
        artist = new Artist();
        artist.setId(1L);
        artist.setName("Test Artist");
        artist.setEmail("test@artist.com");
        artist.setPassword("password123");

        // Lista de prueba
        artistList = Arrays.asList(artist);
    }

    @Test
    void testRegisterArtist() {
        when(artistRepository.save(any(Artist.class))).thenReturn(artist);

        Artist result = artistService.registerArtist(artist);

        assertNotNull(result);
        assertEquals("Test Artist", result.getName());
        assertEquals("test@artist.com", result.getEmail());
        assertEquals("password123", result.getPassword());

        verify(artistRepository, times(1)).save(any(Artist.class));
    }

    @Test
    void testGetAllArtists() {
        when(artistRepository.findAll()).thenReturn(artistList);

        List<Artist> result = artistService.getAllArtists();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Artist", result.get(0).getName());

        verify(artistRepository, times(1)).findAll();
    }

    @Test
    void testGetArtist() {
        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));

        Artist result = artistService.getArtist(1L);

        assertNotNull(result);
        assertEquals("Test Artist", result.getName());
        assertEquals("test@artist.com", result.getEmail());

        verify(artistRepository, times(1)).findById(1L);
    }

    @Test
    void testGetArtist_NotFound() {
        when(artistRepository.findById(2L)).thenReturn(Optional.empty());

        Artist result = artistService.getArtist(2L);

        assertNull(result);

        verify(artistRepository, times(1)).findById(2L);
    }

    @Test
    void testUpdateArtist() {
        Artist updatedDetails = new Artist();
        updatedDetails.setName("Updated Artist");
        updatedDetails.setEmail("updated@artist.com");
        updatedDetails.setPassword("newPassword");

        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        when(artistRepository.save(any(Artist.class))).thenReturn(artist);

        Artist result = artistService.updateArtist(1L, updatedDetails);

        assertNotNull(result);
        assertEquals("Updated Artist", result.getName());
        assertEquals("updated@artist.com", result.getEmail());
        assertEquals("newPassword", result.getPassword());

        verify(artistRepository, times(1)).findById(1L);
        verify(artistRepository, times(1)).save(any(Artist.class));
    }

    @Test
    void testUpdateArtist_NotFound() {
        Artist updatedDetails = new Artist();
        updatedDetails.setName("Nonexistent Artist");
        updatedDetails.setEmail("nonexistent@artist.com");
        updatedDetails.setPassword("password");

        when(artistRepository.findById(2L)).thenReturn(Optional.empty());

        Artist result = artistService.updateArtist(2L, updatedDetails);

        assertNull(result);

        verify(artistRepository, times(1)).findById(2L);
        verify(artistRepository, never()).save(any(Artist.class));
    }

    @Test
    void testDeleteArtist() {
        when(artistRepository.existsById(1L)).thenReturn(true);
        doNothing().when(artistRepository).deleteById(1L);

        boolean result = artistService.deleteArtist(1L);

        assertTrue(result);

        verify(artistRepository, times(1)).existsById(1L);
        verify(artistRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteArtist_NotFound() {
        when(artistRepository.existsById(2L)).thenReturn(false);

        boolean result = artistService.deleteArtist(2L);

        assertFalse(result);

        verify(artistRepository, times(1)).existsById(2L);
        verify(artistRepository, never()).deleteById(2L);
    }
}

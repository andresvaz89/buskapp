package andresvaz.dev.buskapp;

import andresvaz.dev.buskapp.controllers.ArtistController;
import andresvaz.dev.buskapp.entities.Artist;
import andresvaz.dev.buskapp.services.ArtistService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ArtistControllerTest {

    @InjectMocks
    private ArtistController artistController;

    @Mock
    private ArtistService artistService;

    private Artist artist;
    private List<Artist> artistList;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        artist = new Artist();
        artist.setId(1L);
        artist.setName("Artist X");
        artist.setEmail("artistx@email.com");
        artist.setPassword("12345");
        artist.setRole("USER");

        artistList = Arrays.asList(artist);
    }

    @Test
    public void testCreateArtist() {
        Mockito.when(artistService.registerArtist(any(Artist.class))).thenReturn(artist);

        ResponseEntity<Artist> response = artistController.createArtist(artist);

        assertNotNull(response);
        assertEquals(artist, response.getBody());
        verify(artistService, times(1)).registerArtist(any(Artist.class));
    }

    @Test
    public void testGetAllArtists() {
        Mockito.when(artistService.getAllArtists()).thenReturn(artistList);

        ResponseEntity<List<Artist>> response = artistController.getAllArtists();

        assertNotNull(response);
        assertEquals(1, response.getBody().size());
        verify(artistService, times(1)).getAllArtists();
    }

    @Test
    public void testGetArtistById_Found() {
        Mockito.when(artistService.getArtist(1L)).thenReturn(artist);

        ResponseEntity<Artist> response = artistController.getArtistById(1L);

        assertNotNull(response);
        assertEquals(artist, response.getBody());
        verify(artistService, times(1)).getArtist(1L);
    }

    @Test
    public void testGetArtistById_NotFound() {
        Mockito.when(artistService.getArtist(1L)).thenReturn(null);

        ResponseEntity<Artist> response = artistController.getArtistById(1L);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateArtist_Found() {
        Mockito.when(artistService.updateArtist(eq(1L), any(Artist.class))).thenReturn(artist);

        ResponseEntity<Artist> response = artistController.updateArtist(1L, artist);

        assertNotNull(response);
        assertEquals(artist, response.getBody());
        verify(artistService, times(1)).updateArtist(eq(1L), any(Artist.class));
    }

    @Test
    public void testUpdateArtist_NotFound() {
        Mockito.when(artistService.updateArtist(eq(1L), any(Artist.class))).thenReturn(null);

        ResponseEntity<Artist> response = artistController.updateArtist(1L, artist);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteArtist_Found() {
        Mockito.when(artistService.deleteArtist(1L)).thenReturn(true);

        ResponseEntity<Void> response = artistController.deleteArtist(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(artistService, times(1)).deleteArtist(1L);
    }

    @Test
    public void testDeleteArtist_NotFound() {
        Mockito.when(artistService.deleteArtist(1L)).thenReturn(false);

        ResponseEntity<Void> response = artistController.deleteArtist(1L);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testSayHello() {
        String response = artistController.sayHello();
        assertEquals("hello", response);
    }

    @Test
    public void testSayAdios() {
        String response = artistController.sayAdios();
        assertEquals("Adios", response);
    }
}

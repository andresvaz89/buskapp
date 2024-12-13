package andresvaz.dev.buskapp;

import andresvaz.dev.buskapp.entities.Setlist;
import andresvaz.dev.buskapp.repositories.SetlistRepository;
import andresvaz.dev.buskapp.services.SetlistService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SetlistServiceTest {

    @Mock
    private SetlistRepository setlistRepository;

    @InjectMocks
    private SetlistService setlistService;

    private Setlist sampleSetlist;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear objeto de prueba
        sampleSetlist = new Setlist();
        sampleSetlist.setId(1L);
        sampleSetlist.setArtist(null); // Puedes personalizar el artista
        sampleSetlist.setSong(null);   // Puedes personalizar la canción
    }

    @Test
    public void testCreateSetlist() {
        when(setlistRepository.save(any(Setlist.class))).thenReturn(sampleSetlist);

        Setlist response = setlistService.createSetlist(sampleSetlist);

        assertNotNull(response);
        assertEquals(sampleSetlist.getId(), response.getId());
        verify(setlistRepository, times(1)).save(sampleSetlist);
    }

    @Test
    public void testGetAllSetlists() {
        List<Setlist> setlistList = Arrays.asList(sampleSetlist, new Setlist());

        when(setlistRepository.findAll()).thenReturn(setlistList);

        List<Setlist> response = setlistService.getAllSetlists();

        assertNotNull(response);
        assertEquals(2, response.size());
        verify(setlistRepository, times(1)).findAll();
    }

    @Test
    public void testGetSetlistById() {
        when(setlistRepository.findById(1L)).thenReturn(Optional.of(sampleSetlist));

        Optional<Setlist> response = setlistService.getSetlistById(1L);

        assertTrue(response.isPresent());
        assertEquals(sampleSetlist.getId(), response.get().getId());
        verify(setlistRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateSetlist() {
        when(setlistRepository.existsById(1L)).thenReturn(true);
        when(setlistRepository.save(any(Setlist.class))).thenReturn(sampleSetlist);

        Setlist response = setlistService.updateSetlist(1L, sampleSetlist);

        assertNotNull(response);
        assertEquals(sampleSetlist.getId(), response.getId());
        verify(setlistRepository, times(1)).existsById(1L);
        verify(setlistRepository, times(1)).save(sampleSetlist);
    }

    @Test
    public void testDeleteSetlist() {
        when(setlistRepository.existsById(1L)).thenReturn(true);
        doNothing().when(setlistRepository).deleteById(1L);

        boolean response = setlistService.deleteSetlist(1L);

        assertTrue(response);
        verify(setlistRepository, times(1)).existsById(1L);
        verify(setlistRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteSetlist_NotFound() {
        when(setlistRepository.existsById(2L)).thenReturn(false);

        boolean response = setlistService.deleteSetlist(2L);

        assertFalse(response);
        verify(setlistRepository, times(1)).existsById(2L);
        verify(setlistRepository, never()).deleteById(any());
    }
}

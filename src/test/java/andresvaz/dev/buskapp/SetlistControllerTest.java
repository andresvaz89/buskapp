package andresvaz.dev.buskapp;

import andresvaz.dev.buskapp.controllers.SetlistController;
import andresvaz.dev.buskapp.entities.Setlist;
import andresvaz.dev.buskapp.services.SetlistService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SetlistControllerTest {

    @InjectMocks
    private SetlistController setlistController;

    @Mock
    private SetlistService setlistService;

    public SetlistControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSetlist_ShouldReturnCreatedSetlist() {
        Setlist setlist = new Setlist();
        when(setlistService.createSetlist(any(Setlist.class))).thenReturn(setlist);

        ResponseEntity<Setlist> response = setlistController.createSetlist(setlist);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(setlist, response.getBody());
        verify(setlistService, times(1)).createSetlist(setlist);
    }

    @Test
    void getAllSetlists_ShouldReturnListOfSetlists() {
        List<Setlist> setlists = new ArrayList<>();
        setlists.add(new Setlist());
        setlists.add(new Setlist());
        when(setlistService.getAllSetlists()).thenReturn(setlists);

        ResponseEntity<List<Setlist>> response = setlistController.getAllSetlists();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(setlists, response.getBody());
        verify(setlistService, times(1)).getAllSetlists();
    }

    @Test
    void getSetlist_ShouldReturnSetlist_WhenExists() {
        Long id = 1L;
        Setlist setlist = new Setlist();
        when(setlistService.getSetlistById(id)).thenReturn(Optional.of(setlist));

        ResponseEntity<Setlist> response = setlistController.getSetlist(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(setlist, response.getBody());
        verify(setlistService, times(1)).getSetlistById(id);
    }

    @Test
    void getSetlist_ShouldReturnNotFound_WhenDoesNotExist() {
        Long id = 1L;
        when(setlistService.getSetlistById(id)).thenReturn(Optional.empty());

        ResponseEntity<Setlist> response = setlistController.getSetlist(id);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(setlistService, times(1)).getSetlistById(id);
    }

    @Test
    void updateSetlist_ShouldReturnUpdatedSetlist_WhenSuccessful() {
        Long id = 1L;
        Setlist setlist = new Setlist();
        when(setlistService.updateSetlist(eq(id), any(Setlist.class))).thenReturn(setlist);

        ResponseEntity<Setlist> response = setlistController.updateSetlist(id, setlist);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(setlist, response.getBody());
        verify(setlistService, times(1)).updateSetlist(id, setlist);
    }

    @Test
    void updateSetlist_ShouldReturnNotFound_WhenUpdateFails() {
        Long id = 1L;
        Setlist setlist = new Setlist();
        when(setlistService.updateSetlist(eq(id), any(Setlist.class))).thenReturn(null);

        ResponseEntity<Setlist> response = setlistController.updateSetlist(id, setlist);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(setlistService, times(1)).updateSetlist(id, setlist);
    }

    @Test
    void deleteSetlist_ShouldReturnOk_WhenDeleted() {
        Long id = 1L;
        when(setlistService.deleteSetlist(id)).thenReturn(true);

        ResponseEntity<String> response = setlistController.deleteSetlist(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Setlist eliminado correctamente.", response.getBody());
        verify(setlistService, times(1)).deleteSetlist(id);
    }

    @Test
    void deleteSetlist_ShouldReturnNotFound_WhenNotDeleted() {
        Long id = 1L;
        when(setlistService.deleteSetlist(id)).thenReturn(false);

        ResponseEntity<String> response = setlistController.deleteSetlist(id);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(setlistService, times(1)).deleteSetlist(id);
    }
}

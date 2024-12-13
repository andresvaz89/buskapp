/* package andresvaz.dev.buskapp;

import andresvaz.dev.buskapp.controllers.SongController;
import andresvaz.dev.buskapp.entities.Song;
import andresvaz.dev.buskapp.services.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class SongControllerTest {

    @InjectMocks
    private SongController songController;

    @Mock
    private SongService songService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(songController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddSong() throws Exception {
        Song song = new Song("Song 1", "Key 1", "Original Artist", null);
        
        Mockito.when(songService.addSong(Mockito.any(Song.class))).thenReturn(song);
    
        mockMvc.perform(post("/songs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(song)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Song 1"));
    }
    
}
 */
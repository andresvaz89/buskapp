package andresvaz.dev.buskapp;

import andresvaz.dev.buskapp.controllers.SongController;
import andresvaz.dev.buskapp.entities.Song;
import andresvaz.dev.buskapp.services.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SongController.class)
public class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddSong() throws Exception {
        Song newSong = new Song("Song Title", "C#", "Original Artist", null);
        Song savedSong = new Song("Song Title", "C#", "Original Artist", null);
        savedSong.setId(1L);

        Mockito.when(songService.addSong(any(Song.class))).thenReturn(savedSong);

        mockMvc.perform(post("/songs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newSong)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Song Title"));
    }

    @Test
    public void testGetAllSongs() throws Exception {
        Song song1 = new Song("Song 1", "A", "Artist 1", null);
        song1.setId(1L);
        Song song2 = new Song("Song 2", "B", "Artist 2", null);
        song2.setId(2L);

        Mockito.when(songService.getAllSongs()).thenReturn(Arrays.asList(song1, song2));

        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Song 1"))
                .andExpect(jsonPath("$[1].title").value("Song 2"));
    }

    @Test
    public void testGetSongById_Found() throws Exception {
        Song song = new Song("Song Title", "C#", "Original Artist", null);
        song.setId(1L);

        Mockito.when(songService.getSongById(1L)).thenReturn(Optional.of(song));

        mockMvc.perform(get("/songs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Song Title"));
    }

    @Test
    public void testGetSongById_NotFound() throws Exception {
        Mockito.when(songService.getSongById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/songs/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateSong_Success() throws Exception {
        Song updatedSong = new Song("Updated Title", "G", "Updated Artist", null);
        updatedSong.setId(1L);

        Mockito.when(songService.updateSong(eq(1L), any(Song.class))).thenReturn(updatedSong);

        mockMvc.perform(put("/songs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedSong)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    public void testUpdateSong_NotFound() throws Exception {
        Mockito.when(songService.updateSong(eq(1L), any(Song.class)))
                .thenThrow(new RuntimeException("Song with id 1 not found"));

        mockMvc.perform(put("/songs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Song())))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Song with id 1 not found"));
    }

    @Test
    public void testDeleteSong_Success() throws Exception {
        Mockito.doNothing().when(songService).deleteSong(1L);

        mockMvc.perform(delete("/songs/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteSong_NotFound() throws Exception {
        Mockito.doThrow(new Exception("Song not found")).when(songService).deleteSong(1L);

        mockMvc.perform(delete("/songs/1"))
                .andExpect(status().isNotFound());
    }
}

/* package andresvaz.dev.buskapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import andresvaz.dev.buskapp.entities.Artist;
import andresvaz.dev.buskapp.entities.Song;

import static org.junit.jupiter.api.Assertions.*;

public class SongTest {

    private Song song;
    private Artist artist;

    @BeforeEach
    public void setUp() {
        artist = new Artist();
        artist.setId(1L);
        artist.setName("Artist Name");
        artist.setGenre("Pop");

        song = new Song();
        song.setTitle("Song Title");
        song.setSongKey("Key A");
        song.setOriginalArtist("Original Artist");
        song.setArtist(artist);
    }

    @Test
    public void testSongCreationWithArtist() {
        assertNotNull(song.getArtist());
        assertEquals("Artist Name", song.getArtist().getName());
    }

    @Test
    public void testSongCreationWithNullArtist() {
        Song songWithoutArtist = new Song();
        songWithoutArtist.setTitle("Song Title");
        songWithoutArtist.setSongKey("Key B");
        songWithoutArtist.setOriginalArtist("Original Artist");
        songWithoutArtist.setArtist(null);

        assertNull(songWithoutArtist.getArtist());
        assertEquals("Song Title", songWithoutArtist.getTitle());
        assertEquals("Key B", songWithoutArtist.getSongKey());
    }

    @Test
    public void testGetters() {
        assertEquals("Song Title", song.getTitle());
        assertEquals("Key A", song.getSongKey());
        assertEquals("Original Artist", song.getOriginalArtist());
        assertNotNull(song.getArtist());
    }

    @Test
    public void testSetters() {
        Song newSong = new Song();
        newSong.setTitle("New Song Title");
        newSong.setSongKey("Key C");
        newSong.setOriginalArtist("Another Original Artist");

        assertEquals("New Song Title", newSong.getTitle());
        assertEquals("Key C", newSong.getSongKey());
        assertEquals("Another Original Artist", newSong.getOriginalArtist());
    }

    @Test
    public void testSetArtistWithNull() {
        song.setArtist(null);

        assertNull(song.getArtist());
    }
}
 */
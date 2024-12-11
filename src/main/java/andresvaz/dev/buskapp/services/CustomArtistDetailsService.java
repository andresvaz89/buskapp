package andresvaz.dev.buskapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import andresvaz.dev.buskapp.repositories.ArtistRepository;

@Service
public class CustomArtistDetailsService implements UserDetailsService {

    private final ArtistRepository artistRepository;

    public CustomArtistDetailsService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return artistRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Artist not found with username: " + username));
    }
}

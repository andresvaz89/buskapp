package andresvaz.dev.buskapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import andresvaz.dev.buskapp.repositories.MusicoRepository;

@Service
public class CustomMusicoDetailsService implements UserDetailsService {

    private final MusicoRepository musicoRepository;

    public CustomMusicoDetailsService(MusicoRepository musicoRepository) {
        this.musicoRepository = musicoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return musicoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Musico not found with username: " + username));
    }
}

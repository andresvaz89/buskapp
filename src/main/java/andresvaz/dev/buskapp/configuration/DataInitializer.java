package andresvaz.dev.buskapp.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import andresvaz.dev.buskapp.entities.Artist;
import andresvaz.dev.buskapp.repositories.ArtistRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ArtistRepository artistRepository;
 
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        try {

            if (!artistRepository.existsByEmail("andres@gmail.com")) {

                Artist root = new Artist();
                root.setName("andres");
                root.setEmail("andres@gmail.com");
                root.setRole("USER");
                root.setPassword(passwordEncoder.encode("1234"));
                artistRepository.save(root);
                System.out.println("usuario andres creado en la base de datos.");

            } else {
                System.out.println("usuario andres ya.");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

}
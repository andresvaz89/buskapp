package andresvaz.dev.buskapp.configuration; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> 
                authz.requestMatchers("/artist/**").permitAll()
                .requestMatchers("/songs/**").permitAll() // Acceso abierto a todos
                .requestMatchers("/setlist/**").permitAll() // Acceso abierto a todos
                   /*  .requestMatchers("/artist/adios").hasAnyAuthority("USER")  */
                    .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
}

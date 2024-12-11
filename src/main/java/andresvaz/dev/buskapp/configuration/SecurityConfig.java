package andresvaz.dev.buskapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import andresvaz.dev.buskapp.services.CustomArtistDetailsService;

@Configuration
public class SecurityConfig {

    private final CustomArtistDetailsService artistDetailsService;

    public SecurityConfig(CustomArtistDetailsService artistDetailsService) {
        this.artistDetailsService = artistDetailsService;
    }

    @Bean
   
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(authorizeRequests -> 
                authorizeRequests
                    .requestMatchers("/api/**").permitAll() // Permitir acceso sin autenticación
                    .anyRequest().authenticated()
            )
            .formLogin().disable(); // Desactiva la página de inicio de sesión completamente para evitar el redireccionamiento
        return http.build();
    }
    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(artistDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}

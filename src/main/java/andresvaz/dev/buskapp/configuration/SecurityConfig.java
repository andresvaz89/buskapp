package andresvaz.dev.buskapp.configuration; 


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Configurar la seguridad HTTP.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/artist/**").permitAll() // Rutas abiertas para registro y vista
                                .requestMatchers("/songs/**").hasRole("USER") // Se requiere autenticación para canciones
                )
                .formLogin(form -> form
                                .loginPage("/login").permitAll()
                )
                .logout(logout -> logout
                                .logoutUrl("/logout").permitAll()
                );
        
        return http.build();
    }

    /**
     * Crear un PasswordEncoder para cifrar contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Crear el AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(username -> null) // Puedes implementar aquí tu lógica de validación
                   .passwordEncoder(passwordEncoder)
                   .and()
                   .build();
    }
}
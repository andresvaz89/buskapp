package andresvaz.dev.buskapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "mi_secreto_seguro";

    // Genera el token JWT con un tiempo de expiración y roles
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // Cambia la firma
                .compact();
    }

    // Valida el token JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes()) // Cambia el método de clave
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // Si el token es inválido o ha expirado
        }
    }

    // Recupera la autenticación del token
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes()) // Cambia el método de clave
                .parseClaimsJws(token)
                .getBody();

        // Obtiene los roles desde las claims
        List<String> roles = claims.get("authorities", List.class);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }

        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }
}

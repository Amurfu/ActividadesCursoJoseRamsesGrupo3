package com.amurfu.tienda.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// Clase para filtrar los tokens JWT en las solicitudes HTTP.
public class JwtTokenFilter extends OncePerRequestFilter {

    // Variable para almacenar la clave secreta usada para verificar el token JWT.
    private String secret;

    // Constructor que inicializa el filtro con una clave secreta.
    public JwtTokenFilter(String s) {
        this.secret = s;
    }

    // Método sobreescrito de OncePerRequestFilter para filtrar las solicitudes internamente.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Obtiene el encabezado de autorización de la solicitud HTTP.
        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Verifica si el encabezado de autorización no es nulo y si comienza con "Bearer ".
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            // Extrae el token JWT del encabezado.
            String token = tokenHeader.split(" ")[1].trim();

            try {
                // Convierte la clave secreta a bytes usando UTF-8.
                byte[] secretKeyBytes = secret.getBytes(StandardCharsets.UTF_8);

                // Parsea y valida el token JWT.
                Jws<Claims> claims = Jwts.parserBuilder()
                        .setSigningKey(secretKeyBytes)
                        .build()
                        .parseClaimsJws(token);

                // Obtiene el nombre de usuario (subject) del cuerpo del token.
                String username = claims.getBody().getSubject();

                // Si el nombre de usuario no es nulo y no hay una autenticación actual,
                // establece una nueva autenticación con el nombre de usuario.
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            username, null, Collections.emptyList());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                // En caso de error (como token inválido), imprime la pila de errores y limpia el contexto de seguridad.
                e.printStackTrace();
                SecurityContextHolder.clearContext();
            }
        }

        // Continúa con el resto de la cadena de filtros.
        chain.doFilter(request, response);
    }
}

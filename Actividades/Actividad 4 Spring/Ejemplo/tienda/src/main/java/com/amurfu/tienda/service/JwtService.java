package com.amurfu.tienda.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

// Anotación que indica que esta clase es un componente de servicio en Spring.
@Service
public class JwtService {

    // Inyección del valor de la clave secreta para la firma de tokens JWT.
    @Value("${jwt.secret}")
    private String secret;

    // Método para generar un token JWT para un nombre de usuario.
    public String generateToken(String correo) {
        // Tiempo de expiración del token en milisegundos (1 hora en este caso).
        long expirationTimeInMillis = 3600000;

        // Convierte la clave secreta en bytes usando UTF-8.
        byte[] secretKeyBytes = secret.getBytes(StandardCharsets.UTF_8);

        // Construye el token JWT.
        return Jwts.builder()
                .setSubject(correo)  // Establece el nombre de usuario como sujeto del token.
                .setIssuedAt(new Date())  // Establece la fecha y hora de emisión del token.
                // Establece la fecha y hora de expiración del token (actual + tiempo de expiración).
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                // Firma el token con el algoritmo HS512 y la clave secreta.
                .signWith(SignatureAlgorithm.HS512, secretKeyBytes)
                .compact();  // Compacta el JWT en una cadena de texto.
    }
}

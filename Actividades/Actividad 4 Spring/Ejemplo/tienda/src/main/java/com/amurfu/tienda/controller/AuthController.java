package com.amurfu.tienda.controller;


import com.amurfu.tienda.data.dto.CredencialesDto;
import com.amurfu.tienda.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// Anotación que indica que esta clase es un controlador REST.
@RestController
public class AuthController {

    // Inyección de valores de propiedades para el nombre de usuario y contraseña.
    @Value("${myapp.username}")
    private String configuredUsername;

    @Value("${myapp.password}")
    private String configuredPassword;

    // Inyección automática de la dependencia JwtService.
    @Autowired
    private final JwtService jwtService;

    // Constructor para inyectar JwtService.
    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    // Método para manejar las solicitudes POST a "/auth/login".
    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody CredencialesDto credentials) {

        // Comprueba si las credenciales proporcionadas coinciden con las configuradas.
        if (configuredUsername.equals(credentials.getUsuario()) &&
                configuredPassword.equals(credentials.getContraseña())) {
            // Genera un token JWT si las credenciales son correctas.
            String token = jwtService.generateToken(credentials.getUsuario());

            // Crea un mapa para la respuesta con el token JWT.
            Map<String, String> response = new HashMap<>();
            response.put("access_token", token);

            // Devuelve la respuesta con el token y estado HTTP 200 (OK).
            return ResponseEntity.ok(response);
        }

        // Devuelve una respuesta con estado HTTP 400 (Bad Request) si las credenciales no son correctas.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

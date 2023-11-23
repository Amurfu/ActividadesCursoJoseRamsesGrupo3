package com.amurfu.tienda.controller;


import com.amurfu.tienda.data.dto.CredencialesDto;
import com.amurfu.tienda.data.dto.RespuestGenerica;
import com.amurfu.tienda.service.AutenticacionService;
import com.amurfu.tienda.service.JwtService;
import jakarta.validation.Valid;
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

    @Autowired
    private UsuariosController usuariosController;

    @Autowired
    private AutenticacionService autenticacionService;

    // Constructor para inyectar JwtService.
    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    // Método para manejar las solicitudes POST a "/auth/login".
    @PostMapping("/auth/login")
    public ResponseEntity<RespuestGenerica> authenticate(@Valid @RequestBody CredencialesDto credentials) {
            RespuestGenerica respuesta = autenticacionService.getTokenUsuario(credentials);
            HttpStatus status = null;
            if(respuesta.isExito()){
                status =  HttpStatus.OK;
                respuesta.setCodigo(status.value());
            }else{
                status = HttpStatus.BAD_REQUEST;
                respuesta.setCodigo(status.value());
            }
            return new ResponseEntity<>(respuesta,status);
    }
}

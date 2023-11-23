package com.amurfu.tienda.service;

import com.amurfu.tienda.data.Usuario;
import com.amurfu.tienda.data.dto.CredencialesDto;
import com.amurfu.tienda.data.dto.RespuestGenerica;
import com.amurfu.tienda.exceptions.EntityNotFoundException;
import com.amurfu.tienda.repository.UsuarioRepository;
import com.amurfu.tienda.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public RespuestGenerica getTokenUsuario(CredencialesDto credencialesDto){
        RespuestGenerica respuesta = new RespuestGenerica();
        //validamos que el usuario exita en la BD si no detonamos una exepcion
        Usuario usuarioCompra = usuarioRepository.findBycorreo(credencialesDto.getCorreo()).
                orElseThrow(() -> new EntityNotFoundException(Constantes.USUARIO_CONTRASENA_INCORRECTA));
        // validamos que las contraseñas sean las correctas
        boolean contraseñasIguales = passwordEncoder.matches(credencialesDto.getContraseña(), usuarioCompra.getContrasena());
        if(contraseñasIguales){
            String token = jwtService.generateToken(credencialesDto.getCorreo());
            respuesta.setMensaje(Constantes.MENSAJE_TOKEN_EXITO);
            respuesta.getDatos().add(token);
            respuesta.setExito(true);
        }else{
            respuesta.setExito(false);
            respuesta.setMensaje(Constantes.USUARIO_CONTRASENA_INCORRECTA);
        }

        return respuesta;
    }

}

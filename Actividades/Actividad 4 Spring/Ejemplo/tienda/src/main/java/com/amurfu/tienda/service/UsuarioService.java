package com.amurfu.tienda.service;

import com.amurfu.tienda.data.Usuario;
import com.amurfu.tienda.data.dto.RespuestGenerica;
import com.amurfu.tienda.data.dto.UsuarioDto;
import com.amurfu.tienda.repository.UsuarioRepository;
import com.amurfu.tienda.utils.Constantes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDto> getUsuarios(){
        List<UsuarioDto> listaUsuarios = new ArrayList<>();

        for(Usuario user: usuarioRepository.findAll()){
           UsuarioDto usuarioDto = new UsuarioDto();
           usuarioDto.setId(user.getId());
           usuarioDto.setNombre(user.getNombre());
           usuarioDto.setApellidoPaterno(user.getApellidoPaterno());
           usuarioDto.setApellidoMaterno(user.getApellidoMaterno());
           usuarioDto.setCorreo(user.getCorreo());
           listaUsuarios.add(usuarioDto);
        }
        return listaUsuarios;
    }

    public RespuestGenerica guardarUsuario(@Valid UsuarioDto dto){
        RespuestGenerica respuestGenerica = new RespuestGenerica();

        if (dto.getCorreo().isBlank() || dto.getContrasena().isBlank()){
            respuestGenerica.setMensaje(Constantes.MENSAJE_USUARIO_CORREO);
            respuestGenerica.setExito(false);
        }else{
            Usuario usuario = new Usuario();
            usuario.setNombre(dto.getNombre());
            usuario.setApellidoPaterno(dto.getApellidoPaterno());
            usuario.setApellidoMaterno(dto.getApellidoMaterno());
            usuario.setCorreo(dto.getCorreo());
            usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
            usuario = usuarioRepository.save(usuario);
            dto.setId(usuario.getId());
        }
        respuestGenerica.setMensaje(Constantes.MENSAJE_USUARIO_EXITO);
        respuestGenerica.setExito(true);
        return respuestGenerica;
    }


}

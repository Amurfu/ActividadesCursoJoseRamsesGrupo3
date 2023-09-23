package com.amurfu.tienda.service;

import com.amurfu.tienda.data.Usuario;
import com.amurfu.tienda.data.dto.UsuarioDto;
import com.amurfu.tienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

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

    public UsuarioDto guardarUsuario(UsuarioDto dto){
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setCorreo(dto.getCorreo());
        usuario = usuarioRepository.save(usuario);
        dto.setId(usuario.getId());
        return dto;
    }
}

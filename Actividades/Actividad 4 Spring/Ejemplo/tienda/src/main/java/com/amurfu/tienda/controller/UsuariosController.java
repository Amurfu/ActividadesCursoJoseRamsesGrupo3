package com.amurfu.tienda.controller;


import com.amurfu.tienda.data.Usuario;
import com.amurfu.tienda.data.dto.RespuestGenerica;
import com.amurfu.tienda.data.dto.UsuarioDto;
import com.amurfu.tienda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService  usuarioService;


    @GetMapping("/getUsuarios")
    public List<UsuarioDto> getTodosUsuarios(){
        return usuarioService.getUsuarios();
    }

    @PostMapping("/guardarUsuarios")
    public ResponseEntity<RespuestGenerica> guardarUsuario(@RequestBody UsuarioDto dto){
        RespuestGenerica respuesta = usuarioService.guardarUsuario(dto);
        HttpStatus status = null;
        if(respuesta.isExito()){
            status = HttpStatus.OK;
            respuesta.setCodigo(status.value());
        }else{
            status = HttpStatus.BAD_REQUEST;
            respuesta.setCodigo(status.value());
        }
        return new ResponseEntity<>(respuesta,status);
    }
}

package com.amurfu.tienda.controller;


import com.amurfu.tienda.data.Usuario;
import com.amurfu.tienda.data.dto.UsuarioDto;
import com.amurfu.tienda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UsuarioDto guardarUsuario(@RequestBody UsuarioDto dto){
        return usuarioService.guardarUsuario(dto);
    }
}

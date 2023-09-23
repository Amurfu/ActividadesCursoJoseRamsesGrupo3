package com.amurfu.tienda.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.amurfu.tienda.data.Usuario}
 */

@Getter
@Setter
public class UsuarioDto implements Serializable {
    Integer id;
    String nombre;
    String apellidoPaterno;
    String apellidoMaterno;
    String correo;

    public UsuarioDto() {

    }

}
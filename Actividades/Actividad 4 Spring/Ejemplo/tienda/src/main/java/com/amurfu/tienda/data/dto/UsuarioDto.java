package com.amurfu.tienda.data.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.amurfu.tienda.data.Usuario}
 */

@Getter
@Setter
@Valid
public class UsuarioDto implements Serializable {
    private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    @Email(message = "Debe ingresar un correo valido")
    private String correo;
    @NotBlank
    private String contrasena;

    public UsuarioDto() {

    }

}
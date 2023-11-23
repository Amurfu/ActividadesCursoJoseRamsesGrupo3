package com.amurfu.tienda.data.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
public class CredencialesDto {
    @NotNull(message = "Debe ingresar un correo valido")
    private String correo;
    @NotNull(message = "Debe ingresar una contraseña valida")
    private String contraseña;
}

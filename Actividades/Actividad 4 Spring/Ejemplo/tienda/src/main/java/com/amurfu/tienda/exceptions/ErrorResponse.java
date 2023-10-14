package com.amurfu.tienda.exceptions;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {
    private String mensaje;
    private int codigo;
    private String estatus;


    public ErrorResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public ErrorResponse(HttpStatus httpStatus, String mensaje){
        this.codigo = httpStatus.value();
        this.estatus = httpStatus.name();
        this.mensaje = mensaje;
    }


}

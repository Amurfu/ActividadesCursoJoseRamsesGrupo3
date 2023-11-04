package com.amurfu.tienda.data.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class RespuestGenerica {

    private int codigo;
    private boolean exito;
    private String mensaje;
    private List<Object> datos;

    public RespuestGenerica(){
        this.datos = new ArrayList<>();
    }

    public RespuestGenerica(int codigo, boolean exito, String mensaje) {
        super();
        this.codigo = codigo;
        this.exito = exito;
        this.mensaje = mensaje;
    }
}

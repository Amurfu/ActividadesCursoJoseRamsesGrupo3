package com.amurfu.tienda.data.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductoDTO {

    private Integer id;
    private String nombre;;
    private String descripcion;
    private Double precioUnitario;
    private int idCategoria;


}

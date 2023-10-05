package com.amurfu.tienda.data.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductoAddDTO {

    private  int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double total;
}

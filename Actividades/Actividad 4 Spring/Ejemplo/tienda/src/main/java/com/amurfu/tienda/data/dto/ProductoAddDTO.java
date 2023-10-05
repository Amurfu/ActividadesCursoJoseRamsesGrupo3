package com.amurfu.tienda.data.dto;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductoAddDTO {

    Integer idProducto;
    Integer cantidad;
    Double precioUnitario;
    Double total;
}

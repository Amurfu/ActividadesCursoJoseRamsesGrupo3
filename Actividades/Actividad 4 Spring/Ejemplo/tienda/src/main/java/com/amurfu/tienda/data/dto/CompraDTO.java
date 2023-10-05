package com.amurfu.tienda.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter@Getter
public class CompraDTO {

    Integer idCompra;
    Date fecha;
    Integer cantidadProductos;
    Double total;
    Integer idUsuario;
    Integer formaPago;
    List<ProductoAddDTO> productos;
}

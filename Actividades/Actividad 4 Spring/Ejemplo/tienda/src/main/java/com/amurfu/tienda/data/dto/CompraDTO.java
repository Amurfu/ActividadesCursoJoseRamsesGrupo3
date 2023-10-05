package com.amurfu.tienda.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Setter
@Getter
public class CompraDTO {

    private int idCompra;
    private int idUsuario;
    private int idFormaPago;
    private double total;
    private int cantidadProductos;
    private Date fecha;
    private List<ProductoAddDTO> productos;
}

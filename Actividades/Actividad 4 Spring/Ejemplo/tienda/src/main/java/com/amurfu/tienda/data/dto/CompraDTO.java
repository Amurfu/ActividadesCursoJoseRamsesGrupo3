package com.amurfu.tienda.data.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@Validated
public class CompraDTO {

    private int idCompra;
    @NotNull(message = "Debe ingresar un usuario")
    @Positive(message = "Debe ingresar un usuario valido")
    private int idUsuario;
    @NotNull(message = "Debe ingresar una forma de pago")
    @Positive(message = "Debe ingresar una forma de pago valida")
    private int idFormaPago;
    private double total;
    private int cantidadProductos;
    private Date fecha;
    @Valid
    private List<ProductoAddDTO> productos;
}

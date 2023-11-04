package com.amurfu.tienda.data.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
public class ProductoAddDTO {

    @NotNull(message = "Debe ingresar un id de producto valido")
    @Positive(message = "Debe ingresar un id de producto valido")
    private  int idProducto;
    @NotNull(message = "Debe ingresar una cantidad valida")
    @Positive(message = "Debe ingresar una cantidad valida")
    private int cantidad;
    private double precioUnitario;
    private double total;

    public  ProductoAddDTO(){

    }

    public ProductoAddDTO(int idProducto, int cantidad){
        super();
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
}

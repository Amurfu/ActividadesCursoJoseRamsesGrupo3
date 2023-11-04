package com.amurfu.tienda.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productos_compra")
public class ProductosCompra {

    @EmbeddedId
    private ProductosCompraId id;

    @MapsId("idCompra")
    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @MapsId("idProducto")
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "total", nullable = false)
    private Double total;

}
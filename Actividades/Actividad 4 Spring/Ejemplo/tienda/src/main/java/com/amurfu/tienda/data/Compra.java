package com.amurfu.tienda.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @Column(name = "idcompra", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcompra;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "cantidad_productos", nullable = false)
    private Integer cantidadProductos;

    @Column(name = "total", nullable = false)
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_forma_pago", nullable = false)
    private FormasPago idFormaPago;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<ProductosCompra> productosCompra;
}
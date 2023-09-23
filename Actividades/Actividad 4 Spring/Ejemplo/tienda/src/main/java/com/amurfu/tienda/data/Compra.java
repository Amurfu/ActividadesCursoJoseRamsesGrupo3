package com.amurfu.tienda.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @Column(name = "idcompra", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private Instant fecha;

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

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "idcompra"),
            inverseJoinColumns = @JoinColumn(name = "idproducto"))
    private Set<Producto> productos = new LinkedHashSet<>();
}
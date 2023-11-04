package com.amurfu.tienda.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProductosCompraId implements Serializable {
    private static final long serialVersionUID = 7920772106078036867L;

    private Integer idCompra;
    private Integer idProducto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductosCompraId entity = (ProductosCompraId) o;
        return Objects.equals(this.idCompra, entity.idCompra) &&
                Objects.equals(this.idProducto, entity.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompra, idProducto);
    }

}
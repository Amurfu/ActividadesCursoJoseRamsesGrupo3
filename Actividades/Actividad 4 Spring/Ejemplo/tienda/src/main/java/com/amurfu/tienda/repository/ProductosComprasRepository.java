package com.amurfu.tienda.repository;

import com.amurfu.tienda.data.ProductosCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosComprasRepository extends JpaRepository<ProductosCompra,Integer> {
}

package com.amurfu.tienda.repository;

import com.amurfu.tienda.data.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

}

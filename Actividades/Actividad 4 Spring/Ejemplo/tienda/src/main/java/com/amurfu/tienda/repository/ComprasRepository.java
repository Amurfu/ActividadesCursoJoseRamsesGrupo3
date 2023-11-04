package com.amurfu.tienda.repository;

import com.amurfu.tienda.data.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprasRepository extends JpaRepository<Compra,Integer> {
}

package com.amurfu.tienda.repository;

import com.amurfu.tienda.data.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {


}

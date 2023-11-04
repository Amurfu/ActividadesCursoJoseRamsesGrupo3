package com.amurfu.tienda.repository;

import com.amurfu.tienda.data.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

   

}

package com.amurfu.tienda.repository;

import com.amurfu.tienda.data.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {


    Optional<Usuario> findBycorreo(String correo);

    Optional<Usuario> findBynombre(String nombre);
}

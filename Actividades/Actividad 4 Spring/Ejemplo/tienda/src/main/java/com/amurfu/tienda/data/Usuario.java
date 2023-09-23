package com.amurfu.tienda.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "idusuario", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 45)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 45)
    private String apellidoMaterno;

    @Column(name = "correo", nullable = false, length = 45)
    private String correo;


}
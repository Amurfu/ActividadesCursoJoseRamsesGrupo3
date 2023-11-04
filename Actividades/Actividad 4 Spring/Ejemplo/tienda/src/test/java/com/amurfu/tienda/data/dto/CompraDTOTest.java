package com.amurfu.tienda.data.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompraDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidCompraDTO() {
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setIdCompra(1);
        compraDTO.setIdUsuario(2);
        compraDTO.setIdFormaPago(3);
        compraDTO.setTotal(100.0);
        compraDTO.setCantidadProductos(5);
        compraDTO.setFecha(new Date());

        List<ProductoAddDTO> productos = new ArrayList<>();
        ProductoAddDTO producto1 = new ProductoAddDTO();
        producto1.setIdProducto(1);
        producto1.setCantidad(3);

        ProductoAddDTO producto2 = new ProductoAddDTO();
        producto2.setIdProducto(2);
        producto2.setCantidad(2);

        productos.add(producto1);
        productos.add(producto2);

        compraDTO.setProductos(productos);

        Set<ConstraintViolation<CompraDTO>> violations = validator.validate(compraDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidCompraDTO() {
        CompraDTO compraDTO = new CompraDTO();
        // Los campos requeridos están en blanco, lo que debería generar violaciones de validación
        Set<ConstraintViolation<CompraDTO>> violations = validator.validate(compraDTO);

        assertEquals(2, violations.size());
    }
}
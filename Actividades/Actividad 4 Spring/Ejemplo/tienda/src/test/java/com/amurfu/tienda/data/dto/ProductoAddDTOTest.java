package com.amurfu.tienda.data.dto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductoAddDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidProductoAddDTO() {
        ProductoAddDTO productoAddDTO = new ProductoAddDTO();
        productoAddDTO.setIdProducto(1);
        productoAddDTO.setCantidad(5);
        // Puedes establecer otros campos si es necesario

        Set<ConstraintViolation<ProductoAddDTO>> violations = validator.validate(productoAddDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidProductoAddDTO() {
        ProductoAddDTO productoAddDTO = new ProductoAddDTO();
        // Los campos requeridos están en blanco, lo que debería generar violaciones de validación
        Set<ConstraintViolation<ProductoAddDTO>> violations = validator.validate(productoAddDTO);

        assertEquals(2, violations.size());
    }
}
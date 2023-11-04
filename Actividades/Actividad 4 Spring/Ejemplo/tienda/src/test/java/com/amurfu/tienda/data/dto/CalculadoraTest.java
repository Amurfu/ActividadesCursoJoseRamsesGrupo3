package com.amurfu.tienda.data.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    private void init(){
        calculadora = new Calculadora();
    }

    @Test
    public void testSuma(){
        int resultado = calculadora.sumar(2,5);

        assertEquals(7,resultado);
    }

    @Test
    public void testRestar(){
        int resultado = calculadora.restar(10,5);

        assertEquals(5,resultado);
    }
}

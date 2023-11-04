package com.amurfu.tienda.data.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RespuestaGenericaTest {

    private RespuestGenerica respuestaGenerica;

    @BeforeEach
    public void setUp() {
        respuestaGenerica = new RespuestGenerica();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, respuestaGenerica.getCodigo());
        assertFalse(respuestaGenerica.isExito());
        assertNull(respuestaGenerica.getMensaje());
        assertNotNull(respuestaGenerica.getDatos());
        assertTrue(respuestaGenerica.getDatos().isEmpty());
    }

    @Test
    public void testParametrizedConstructor() {
        RespuestGenerica respuesta = new RespuestGenerica(200, true, "Éxito");
        assertEquals(200, respuesta.getCodigo());
        assertTrue(respuesta.isExito());
        assertEquals("Éxito", respuesta.getMensaje());
        List<String> listaObjetos = new ArrayList<>();
        listaObjetos.add("Esto es un objeto mensaje");
        respuesta.setDatos(Arrays.asList(listaObjetos.toArray()));
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
    }

    @Test
    public void testSettersAndGetters() {
        respuestaGenerica.setCodigo(404);
        respuestaGenerica.setExito(false);
        respuestaGenerica.setMensaje("No encontrado");
        List<Object> datos = new ArrayList<>();
        datos.add("Dato 1");
        datos.add("Dato 2");
        respuestaGenerica.setDatos(datos);

        assertEquals(404, respuestaGenerica.getCodigo());
        assertFalse(respuestaGenerica.isExito());
        assertEquals("No encontrado", respuestaGenerica.getMensaje());
        assertNotNull(respuestaGenerica.getDatos());
        assertEquals(2, respuestaGenerica.getDatos().size());
    }
}

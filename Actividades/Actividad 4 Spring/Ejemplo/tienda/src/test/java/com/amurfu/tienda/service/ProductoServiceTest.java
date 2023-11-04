package com.amurfu.tienda.service;

import com.amurfu.tienda.data.Categoria;
import com.amurfu.tienda.data.Producto;
import com.amurfu.tienda.data.dto.ProductoDTO;
import com.amurfu.tienda.data.dto.RespuestGenerica;
import com.amurfu.tienda.repository.CategoriaRepository;
import com.amurfu.tienda.repository.ProductoRepository;
import com.amurfu.tienda.utils.Constantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoService productoService;

    private ProductoDTO productoDTO;
    private Producto producto;
    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Electrónica");

        productoDTO = new ProductoDTO();
        productoDTO.setDescripcion("Smartphone de última generación");
        productoDTO.setNombre("Smartphone XYZ");
        productoDTO.setPrecioUnitario(999.99);
        productoDTO.setIdCategoria(categoria.getId());

        producto = new Producto();
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecioUnitario(productoDTO.getPrecioUnitario());
        producto.setIdCategoria(categoria);
    }

    @Test
    void guardarProductoDebeRetornarRespuestaExitosa() {
        // Simular el comportamiento de los métodos del repositorio
        when(categoriaRepository.getReferenceById(any(Integer.class))).thenReturn(categoria);
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> {
            Producto p = invocation.getArgument(0);
            p.setId(1); // Simular ID generado
            return p;
        });

        // Ejecutar el método a probar
        RespuestGenerica respuesta = productoService.guardarProducto(productoDTO);

        // Verificar los resultados
        assertTrue(respuesta.isExito());
        assertNotNull(respuesta.getDatos());
        assertFalse(respuesta.getDatos().isEmpty());
        assertEquals(Constantes.MENSAJE_REGISTRO_PRODUCTO + "1", respuesta.getMensaje());

        // Verificar la interacción con los mocks
        verify(categoriaRepository).getReferenceById(any(Integer.class));
        verify(productoRepository).save(any(Producto.class));
    }
}

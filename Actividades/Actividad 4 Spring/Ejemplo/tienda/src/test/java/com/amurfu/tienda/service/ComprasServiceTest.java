package com.amurfu.tienda.service;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import com.amurfu.tienda.data.*;
import com.amurfu.tienda.data.dto.CompraDTO;
import com.amurfu.tienda.data.dto.ProductoAddDTO;
import com.amurfu.tienda.data.dto.RespuestGenerica;
import com.amurfu.tienda.exceptions.EntityNotFoundException;
import com.amurfu.tienda.exceptions.ProductoSinStockException;
import com.amurfu.tienda.repository.*;
import com.amurfu.tienda.utils.Constantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ComprasServiceTest {

    @Mock
    private ComprasRepository comprasRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private FormaPagoRepository formaPagoRepository;

    @Mock
    private ProductosComprasRepository productosComprasRepository;

    @InjectMocks
    private ComprasService comprasService;

    private CompraDTO compraDto;
    private Usuario usuario;
    private FormasPago formaPago;
    private Producto producto;

    @BeforeEach
    void setUp() {
        // Crear datos de prueba
        usuario = new Usuario();
        usuario.setId(1);

        formaPago = new FormasPago();
        formaPago.setId(1);

        producto = new Producto();
        producto.setId(1);
        producto.setPrecioUnitario(100.0);
        producto.setCantidadActual(10);

        ProductoAddDTO productoAddDTO = new ProductoAddDTO();
        productoAddDTO.setIdProducto(1);
        productoAddDTO.setCantidad(1);

        compraDto = new CompraDTO();
        compraDto.setIdUsuario(1);
        compraDto.setIdFormaPago(1);
        compraDto.setProductos(Arrays.asList(productoAddDTO));
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void generarCompraExitosa() {
        // Configurar comportamiento del mock
        when(usuarioRepository.findById(any(Integer.class))).thenReturn(Optional.of(usuario));
        when(formaPagoRepository.findById(any(Integer.class))).thenReturn(Optional.of(formaPago));
        when(productoRepository.findById(any(Integer.class))).thenReturn(Optional.of(producto));
        when(comprasRepository.save(any(Compra.class))).then(i -> {
            Compra compra = i.getArgument(0);
            // Simula la generación de un ID para la compra
            compra.setIdcompra(1); // Asumiendo que el tipo del ID es Integer
            return compra;
        });


        // Ejecutar el servicio a probar
        RespuestGenerica respuesta = comprasService.generarCompra(compraDto);

        // Verificaciones y aserciones
        assertTrue(respuesta.isExito());
        assertEquals(Constantes.MENSAJE_EXITO_COMPRA, respuesta.getMensaje());
        assertNotNull(compraDto.getIdCompra());
        assertEquals(100.0 * 1, compraDto.getTotal());

        // Verificar interacciones con los mocks
        verify(comprasRepository).save(any(Compra.class));
        verify(productosComprasRepository).save(any(ProductosCompra.class));
    }

    @Test
    void generarCompraConUsuarioInexistente() {
        // Configurar comportamiento del mock para lanzar la excepción
        when(usuarioRepository.findById(any(Integer.class))).thenThrow(new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));

        // Ejecutar y verificar que se lanza la excepción
        assertThrows(EntityNotFoundException.class, () -> comprasService.generarCompra(compraDto));
    }

    @Test
    void generarCompraConProductoSinStock() {
        // ...
        when(usuarioRepository.findById(any(Integer.class))).thenReturn(Optional.of(usuario));
        when(formaPagoRepository.findById(any(Integer.class))).thenReturn(Optional.of(formaPago));

        //Mandamos a probar la exception
        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> comprasService.generarCompra(compraDto),
                Constantes.MENSAJE_STOCK_NO_DISPONIBLE
        );

        assertTrue(thrown.getMessage().contains("El producto con id 1 No existe."));
    }


}


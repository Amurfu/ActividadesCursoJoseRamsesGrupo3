package com.amurfu.tienda.service;

import com.amurfu.tienda.data.*;

import com.amurfu.tienda.data.dto.CompraDTO;
import com.amurfu.tienda.data.dto.ProductoAddDTO;
import com.amurfu.tienda.data.dto.RespuestGenerica;
import com.amurfu.tienda.exceptions.EntityNotFoundException;
import com.amurfu.tienda.exceptions.ProductoSinStockException;
import com.amurfu.tienda.exceptions.ProductosNoDisponiblesException;
import com.amurfu.tienda.repository.*;
import com.amurfu.tienda.utils.Constantes;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ComprasService {

    @Autowired
    private  ComprasRepository comprasRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FormaPagoRepository formaPagoRepository;

    @Autowired
    private ProductosComprasRepository productosComprasRepository;





    @Transactional
    public RespuestGenerica generarCompra(@Valid CompraDTO compraDto){
        RespuestGenerica respuesta = new RespuestGenerica();

        Compra compraHeader = new Compra();
        compraHeader.setFecha(new Date());
        compraHeader.setCantidadProductos(compraDto.getProductos().size());
        //guardamos el usuario, primero vamos a la BD por el usuario y luego lo asignamos a la compra
        Usuario usuarioCompra = usuarioRepository.findById(compraDto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));
        compraHeader.setIdUsuario(usuarioCompra);
        //Buscamos la forma de pago y se la asignamos a la entidad de la compra

        //FormasPago formasPago = formaPagoRepository.getReferenceById(compraDto.getIdFormaPago());
        FormasPago formasPago = formaPagoRepository.findById(compraDto.getIdFormaPago())
                .orElseThrow(() -> new EntityNotFoundException(Constantes.FORMA_PAGO_NO_EXISTENTE));

        compraHeader.setIdFormaPago(formasPago);
        //Calculamos el total recorriendo cada producto, obteniendo su precio unitario y multiplicandolo por la cantidad
        double totalCompra = 0.0;

        /*
        validar la cantidad de productos existentes
        throw new ProductosNoDisponiblesException("Algunos productos no están disponibles");
        //validar si la forma de pago existe
        FormasPago formasPago = formaPagoRepository.findById(compraDto.getIdFormaPago())
                .orElseThrow(() -> new EntityNotFoundException("La entidad no se encuentra en la base de datos"));
        */

        for(ProductoAddDTO productoJson : compraDto.getProductos()){
            //Por cada id encontrado en el json vamos a la BD por el objeto producto para hjacer los calculos
            Producto productoBD = productoRepository.findById(productoJson.getIdProducto())
                    .orElseThrow(() -> new EntityNotFoundException("El producto con id "+productoJson.getIdProducto()+" No existe."));
            totalCompra += productoBD.getPrecioUnitario() * productoJson.getCantidad();
            //Actualizamos la informacion en el json tambien, para mantener al usuario retroalimientado
            //validar si mi cantidad actual cumple con la que piden
            if(productoBD.getCantidadActual() < productoJson.getCantidad()){
                throw  new ProductoSinStockException(Constantes.MENSAJE_STOCK_NO_DISPONIBLE+" "+productoBD.getId());
            }
            productoJson.setPrecioUnitario(productoBD.getPrecioUnitario());
            productoJson.setTotal(productoBD.getPrecioUnitario() * productoJson.getCantidad());
        }
        //asignamos el total a la compra que se guardara en la BD
        compraHeader.setTotal(totalCompra);
        //Guardamos la compra para despues guardar la tabla de productos_compra
        comprasRepository.save(compraHeader);
        //Si la compra se guarda, descontamos la cantidad del stock
        for(ProductoAddDTO productoJson : compraDto.getProductos()){
        //Actualizamos el stock
            Producto productoBD = productoRepository.findById(productoJson.getIdProducto())
                    .orElseThrow(() -> new EntityNotFoundException("El producto con id "+productoJson.getIdProducto()+" No existe."));
            productoBD.setCantidadActual(productoBD.getCantidadActual() - productoJson.getCantidad());
            productoRepository.save(productoBD);
        }

        //Una vez guardada la compra procedemos a guardar los productos en la tabla cruzada
        for(ProductoAddDTO productoJson : compraDto.getProductos()){
            //Traemos el producto de la bd
            Producto productoBd = productoRepository.findById(productoJson.getIdProducto())
                    .orElseThrow(() -> new EntityNotFoundException("El producto con id "+productoJson.getIdProducto()+" No existe."));
            //Creamos la entidad que guardara en la BD
            ProductosCompra productosCompra = new ProductosCompra();
            //Guardamos el id compuesto cvreando una nueva entidad que representa ese ID compuesto
            ProductosCompraId id = new ProductosCompraId();
            id.setIdCompra(compraHeader.getIdcompra());
            id.setIdProducto(productoBd.getId());
            productosCompra.setId(id);
            //guardamos la entidad producto y la entidad compra para que se relacione
            productosCompra.setCompra(compraHeader);
            productosCompra.setProducto(productoBd);
            //Guardamos los datros calculados
            productosCompra.setCantidad(productoJson.getCantidad());
            productosCompra.setPrecioUnitario(productoJson.getPrecioUnitario());
            productosCompra.setTotal(productoBd.getPrecioUnitario() * productoJson.getCantidad());
            // Guardar la relación ProductosCompra en la base de datos
            productosComprasRepository.save(productosCompra);
        }
        //Asignamos los valores al dtro para retroalimentar al usuario en al compra
        compraDto.setIdCompra(compraHeader.getIdcompra());
        compraDto.setTotal(compraHeader.getTotal());
        compraDto.setCantidadProductos(compraHeader.getCantidadProductos());
        compraDto.setFecha(compraHeader.getFecha());

        respuesta.setExito(true);
        respuesta.setMensaje(Constantes.MENSAJE_EXITO_COMPRA);
        respuesta.getDatos().add(compraDto);
        return  respuesta;
    }

}

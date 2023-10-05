package com.amurfu.tienda.service;

import com.amurfu.tienda.data.*;
import com.amurfu.tienda.data.dto.CompraDTO;
import com.amurfu.tienda.data.dto.ProductoAddDTO;
import com.amurfu.tienda.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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





    public CompraDTO generarCompra(CompraDTO compraDto){
        Compra compraPrincipal = new Compra();
        compraPrincipal.setFecha(new Date());
        compraPrincipal.setCantidadProductos(compraDto.getProductos().size());
        Usuario usuarioCompra = usuarioRepository.getReferenceById(compraDto.getIdUsuario());
        compraPrincipal.setIdUsuario(usuarioCompra);
        FormasPago formaPago = formaPagoRepository.getReferenceById(compraDto.getFormaPago());
        compraPrincipal.setIdFormaPago(formaPago);
        Double totalGeneral = 0.0;
        for(ProductoAddDTO dtoProducto : compraDto.getProductos()){
            ProductosCompra productoCarrito = new ProductosCompra();
            Producto productoBd = productoRepository.getReferenceById(dtoProducto.getIdProducto());
            dtoProducto.setPrecioUnitario(productoBd.getPrecioUnitario());
            dtoProducto.setTotal(dtoProducto.getCantidad() * productoBd.getPrecioUnitario());
            totalGeneral += dtoProducto.getTotal();
        }
        compraDto.setTotal(totalGeneral);
        compraDto.setCantidadProductos(compraPrincipal.getCantidadProductos());
        compraDto.setFecha(compraPrincipal.getFecha());
        compraPrincipal.setTotal(totalGeneral);
        comprasRepository.save(compraPrincipal);
        compraDto.setIdCompra(compraPrincipal.getId());
        return  compraDto;
    }

}

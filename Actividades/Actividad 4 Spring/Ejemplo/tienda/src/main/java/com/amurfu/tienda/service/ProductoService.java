package com.amurfu.tienda.service;

import com.amurfu.tienda.data.Categoria;
import com.amurfu.tienda.data.Producto;
import com.amurfu.tienda.data.dto.ProductoDTO;
import com.amurfu.tienda.repository.CategoriaRepository;
import com.amurfu.tienda.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {


    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;



    public ProductoDTO guardarProducto(ProductoDTO productoDTO){
        Producto productoNuevo = new Producto();
        productoNuevo.setDescripcion(productoDTO.getDescripcion());
        productoNuevo.setNombre(productoDTO.getNombre());
        productoNuevo.setPrecioUnitario(productoDTO.getPrecioUnitario());
        Categoria categoria = buscarCategoriaPorId(productoDTO.getIdCategoria());
        productoNuevo.setIdCategoria(categoria);
        productoRepository.save(productoNuevo);
        productoDTO.setId(productoNuevo.getId());
        return productoDTO;
    }

    private Categoria buscarCategoriaPorId(int idCategoria) {
        Categoria cat = categoriaRepository.getReferenceById(idCategoria);
        return cat;
    }
}

package com.amurfu.tienda.controller;


import com.amurfu.tienda.data.dto.ProductoDTO;
import com.amurfu.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @PostMapping("/guardar")
    public ProductoDTO guardarProducto(@RequestBody  ProductoDTO productoDTO){
        return productoService.guardarProducto(productoDTO);
    }
}

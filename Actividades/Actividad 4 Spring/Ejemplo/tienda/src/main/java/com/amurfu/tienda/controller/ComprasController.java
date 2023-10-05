package com.amurfu.tienda.controller;


import com.amurfu.tienda.data.dto.CompraDTO;
import com.amurfu.tienda.service.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    private ComprasService comprasService;


    @PostMapping("/guardar")
    public CompraDTO guardarCompra(@RequestBody CompraDTO compra){
        return comprasService.generarCompra(compra);
    }
}

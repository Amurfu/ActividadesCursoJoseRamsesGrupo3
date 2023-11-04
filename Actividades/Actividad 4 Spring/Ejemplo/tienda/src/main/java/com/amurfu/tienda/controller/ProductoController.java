package com.amurfu.tienda.controller;


import com.amurfu.tienda.data.dto.ProductoDTO;
import com.amurfu.tienda.data.dto.RespuestGenerica;
import com.amurfu.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RespuestGenerica> guardarProducto(@RequestBody  ProductoDTO productoDTO){

        RespuestGenerica respuesta = productoService.guardarProducto(productoDTO);
        HttpStatus status = null;
        if(respuesta.isExito()){
            status =  HttpStatus.OK;
            respuesta.setCodigo(status.value());
        }else{
            status = HttpStatus.BAD_REQUEST;
            respuesta.setCodigo(status.value());
        }
       return new ResponseEntity<>(respuesta,status);
    }
}

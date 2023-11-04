package com.amurfu.tienda.controller;


import com.amurfu.tienda.data.dto.CompraDTO;
import com.amurfu.tienda.data.dto.RespuestGenerica;
import com.amurfu.tienda.exceptions.ErrorResponse;
import com.amurfu.tienda.exceptions.ProductosNoDisponiblesException;
import com.amurfu.tienda.service.ComprasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
@Validated
public class ComprasController {


    @Autowired
    private ComprasService comprasService;



    @PostMapping("/guardar")
    public ResponseEntity<RespuestGenerica> guardarCompra(@Valid @RequestBody CompraDTO compraDTO){

        RespuestGenerica respuesta = comprasService.generarCompra(compraDTO);
        HttpStatus status = null;
        if(respuesta.isExito()){
            status =HttpStatus.OK;
            respuesta.setCodigo(status.value());
        }else{
            status = HttpStatus.BAD_REQUEST;
            respuesta.setCodigo(status.value());
        }
        return new ResponseEntity<>(respuesta,status);
    }


}

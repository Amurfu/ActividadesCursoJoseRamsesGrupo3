package com.amurfu.tienda.controller;


import com.amurfu.tienda.data.dto.CompraDTO;
import com.amurfu.tienda.exceptions.ErrorResponse;
import com.amurfu.tienda.service.ComprasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public CompraDTO guardarCompra(@Valid @RequestBody CompraDTO compraDTO){
        return comprasService.generarCompra(compraDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError.getDefaultMessage();
        return new ErrorResponse(errorMessage);
    }
}

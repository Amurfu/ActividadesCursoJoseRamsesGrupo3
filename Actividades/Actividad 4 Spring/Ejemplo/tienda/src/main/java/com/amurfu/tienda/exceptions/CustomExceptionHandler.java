package com.amurfu.tienda.exceptions;

import com.amurfu.tienda.data.dto.RespuestGenerica;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        HttpStatus status =  HttpStatus.BAD_REQUEST;
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError.getDefaultMessage();
        return new ResponseEntity<>(new ErrorResponse(status,errorMessage),status);
    }

    @ExceptionHandler(ProductosNoDisponiblesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleProductosNoDisponiblesException(ProductosNoDisponiblesException ex) {
        HttpStatus status =  HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ErrorResponse(status,ex.getMessage()),status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<RespuestGenerica> handleEntityNotFoundException(EntityNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        RespuestGenerica respuesta = new RespuestGenerica();
        respuesta.setExito(false);
        respuesta.setCodigo(status.value());
        respuesta.setMensaje(ex.getMessage());
        return new ResponseEntity<>(respuesta,status);
    }
}

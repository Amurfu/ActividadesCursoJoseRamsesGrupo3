package com.amurfu.tienda.exceptions;

public class ProductoSinStockException extends RuntimeException{

    public ProductoSinStockException(String message) {
        super(message);
    }
}

package com.ramazan.readingisgood.exception;

public class StockNotFoundException extends RuntimeException{

    public StockNotFoundException(String message) {
        super(message);
    }
}

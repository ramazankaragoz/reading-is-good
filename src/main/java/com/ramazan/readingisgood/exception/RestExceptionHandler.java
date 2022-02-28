package com.ramazan.readingisgood.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req){
        RestError exceptionResponse = new RestError(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public final ResponseEntity<Object> handleCustomerAlreadyExistException(Exception ex, WebRequest req){
        RestError exceptionResponse = new RestError(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookAlreadyExistException.class)
    public final ResponseEntity<Object> handleBookAlreadyExistException(Exception ex, WebRequest req){
        RestError exceptionResponse = new RestError(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StockInvalidQuantityException.class)
    public final ResponseEntity<Object> handleStockInvalidQuantityException(Exception ex, WebRequest req){
        RestError exceptionResponse = new RestError(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StockNotFoundException.class)
    public final ResponseEntity<Object> handleStockNotFoundException(Exception ex, WebRequest req){
        RestError exceptionResponse = new RestError(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<Object> handleCustomerNotFoundException(Exception ex, WebRequest req){
        RestError exceptionResponse = new RestError(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        RestError exceptionResponse = new RestError(new Date(),"Validation Failed.", ex.getBindingResult().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}

package com.ecom.productservicejune24.ControllerAdvice;

import com.ecom.productservicejune24.DTOs.ExceptionDTO;
import com.ecom.productservicejune24.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(ArithmeticException.class)
//    public ResponseEntity<String> handleArithmeticException(){
//        ResponseEntity<String> response = new ResponseEntity<>(
//                "Arithmetic Exception has happened inside the controller advice",
//                HttpStatus.NOT_FOUND
//        );
//        return response;
//    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDTO> handleArithmeticException(){

        ExceptionDTO exceptionDTO = new ExceptionDTO();

        exceptionDTO.setMessage("ArithmeticException");
        exceptionDTO.setSolution("I don't know what to do");

        ResponseEntity<ExceptionDTO> response = new ResponseEntity<>(
                exceptionDTO,
                HttpStatus.NOT_FOUND
        );
        return response;
    }


    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBoundsException(){
        ResponseEntity<String> response = new ResponseEntity<>(
                "Array Index Out of bound Exception has happened inside the controller advice",
                HttpStatus.NOT_FOUND
        );
        return response;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(){
        ExceptionDTO exceptionDTO = new ExceptionDTO();

        exceptionDTO.setMessage("Product Not Found");
        exceptionDTO.setSolution("I don't know what to do, Please try again with a valid productId");

        ResponseEntity<ExceptionDTO> response = new ResponseEntity<>(
                exceptionDTO, // REM this thing
                HttpStatus.NOT_FOUND
        );
        return response;
    }
}

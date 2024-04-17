package org.andreu.tarea2.msvcintervinientes.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Manejador de excepciones globales que devuelve un mensaje de error personalizado cuando se produce una excepción
// de tipo HttpMessageNotReadableException (cuando el cuerpo de la solicitud no es válido)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Se requiere un cuerpo de solicitud válido en formato JSON", HttpStatus.BAD_REQUEST);
    }
}


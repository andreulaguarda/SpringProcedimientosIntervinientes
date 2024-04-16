package org.andreu.tarea2.msvcprocedimientos.config;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleHttpMessageNotReadableException() {
        HttpInputMessage inputMessage = new MockHttpInputMessage(new byte[0]);
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Test message", inputMessage);

        var responseEntity = globalExceptionHandler.handleHttpMessageNotReadableException(exception);

        assertEquals("Se requiere un cuerpo de solicitud válido en formato JSON", responseEntity.getBody());
        assertEquals(400, responseEntity.getStatusCodeValue());
    }
}

package com.florian.ceramicaswari.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ERRORES DE VALIDACION -> 400 BAD REQUEST
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErroresValidacion(
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> errores = new LinkedHashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errores.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ex.getBindingResult()
                .getGlobalErrors()
                .forEach(error ->
                        errores.put(
                                "validacion",
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .badRequest()
                .body(errores);
    }

    // RECURSO NO ENCONTRADO -> 404 NOT FOUND
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> manejarRecursoNoEncontrado(
            RecursoNoEncontradoException ex
    ) {

        Map<String, String> respuesta = new LinkedHashMap<>();

        respuesta.put(
                "mensaje",
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(respuesta);
    }

    // CONFLICTO CONTROLADO POR NUESTRA LOGICA -> 409
    @ExceptionHandler(ConflictoDatosException.class)
    public ResponseEntity<Map<String, String>> manejarConflictoDatos(
            ConflictoDatosException ex
    ) {

        Map<String, String> respuesta = new LinkedHashMap<>();

        respuesta.put(
                "mensaje",
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(respuesta);
    }

    // VIOLACION DE RESTRICCIONES DE BASE DE DATOS -> 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> manejarIntegridadDatos(
            DataIntegrityViolationException ex
    ) {

        Map<String, String> respuesta = new LinkedHashMap<>();

        respuesta.put(
                "mensaje",
                "La operación no puede realizarse porque entra en conflicto con los datos existentes"
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(respuesta);
    }
}
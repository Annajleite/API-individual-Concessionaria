package br.com.concessionaria.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> erros = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            erros.add(error.getField() + ": " + error.getDefaultMessage());
        }

        int codigoStatus = status.value();

        ErroResposta erroResposta = new ErroResposta(codigoStatus, "Existem campos inválidos. Verifique o preenchimento.", LocalDateTime.now(), erros);
        return ResponseEntity.status(status).body(erroResposta);
    }


    @ExceptionHandler(CpfJaCadastradoException.class)
    public ResponseEntity<Object> handleCpfJaCadastrado(CpfJaCadastradoException ex) {
        ErroResposta erroResposta = new ErroResposta(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroResposta);
    }


    @ExceptionHandler(PlacaJaCadastradaException.class)
    public ResponseEntity<Object> handlePlacaJaCadastrada(PlacaJaCadastradaException ex) {
        ErroResposta erroResposta = new ErroResposta(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroResposta);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        ErroResposta erroResposta = new ErroResposta(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResposta);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntime(RuntimeException ex) {

        if (ex.getMessage() != null && ex.getMessage().contains("não encontrado")) {
            ErroResposta erroResposta = new ErroResposta(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResposta);
        }


        ErroResposta erroResposta = new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno no servidor: " + ex.getMessage(), LocalDateTime.now(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResposta);
    }
}
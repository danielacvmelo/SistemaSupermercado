package com.jb.supermercado.config.exception.handler;

import com.jb.supermercado.config.exception.BusinessException;
import com.jb.supermercado.config.exception.RecursoNaoEncontradoException;
import com.jb.supermercado.config.exception.response.ErroPadraoResponse;
import com.jb.supermercado.config.exception.response.ErroValidacaoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroPadraoResponse> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErroPadraoResponse> handleBusinessException(BusinessException ex) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<ErroValidacaoResponse.ErroDetalhe> erros = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                erros.add(new ErroValidacaoResponse.ErroDetalhe(
                        error.getField(),
                        error.getDefaultMessage()
                ))
        );

        ErroValidacaoResponse erro = new ErroValidacaoResponse(
                "Erro de validacao",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                erros
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadraoResponse> handleGenericException(Exception ex) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                "Erro interno no servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}


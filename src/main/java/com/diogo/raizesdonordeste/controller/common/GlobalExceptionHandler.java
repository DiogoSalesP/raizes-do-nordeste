package com.diogo.raizesdonordeste.controller.common;

import com.diogo.raizesdonordeste.dto.ErroCampo;
import com.diogo.raizesdonordeste.dto.ErroResponse;
import com.diogo.raizesdonordeste.exception.OperacaoNaoPermitidaException;
import com.diogo.raizesdonordeste.exception.RegistroDuplicadoException;
import com.diogo.raizesdonordeste.exception.RegistroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponse handleOperacaoNaoPermitidaException(OperacaoNaoPermitidaException e) {
        return ErroResponse.respostaPadrao(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        return ErroResponse.respostaPadrao(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponse handleMethodArgumentTypeMismatch(HttpMessageNotReadableException e) {
        return ErroResponse.respostaPadrao(e.getMessage());
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResponse handleRegistroDuplicado(RegistroDuplicadoException e) {
        return ErroResponse.conflito(e.getMessage());
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResponse handleRegistroNaoEncontrado(RegistroNaoEncontradoException e) {
        return ErroResponse.naoEncontrado(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErroResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<FieldError> fieldError = e.getFieldErrors();
        List<ErroCampo> list = fieldError
                .stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return new ErroResponse(HttpStatus.UNPROCESSABLE_CONTENT.value(), "erro de validação", list);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResponse handleErrorNaoTratados(RuntimeException e) {
        System.out.println(e.getMessage());
        return new ErroResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro inesperado, Entre em contato com a administração",
                List.of()
        );
    }
}

package com.diogo.raizesdonordeste.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResponse(int status, String mensagem, List<ErroCampo> erros) {

    public static ErroResponse respostaPadrao(String mensagem) {
        return new ErroResponse(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static ErroResponse naoEncontrado(String mensagem) {
        return new ErroResponse(HttpStatus.NOT_FOUND.value(), mensagem, List.of());
    }

    public static ErroResponse conflito(String mensagem) {
        return new ErroResponse(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}

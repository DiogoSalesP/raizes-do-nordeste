package com.diogo.raizesdonordeste.exception;

import java.util.UUID;

public class RegistroNaoEncontradoException extends RuntimeException{

    public RegistroNaoEncontradoException(String message, UUID id) {
        super(message + " com id '" + id + "' não encontrado.");
    }
}

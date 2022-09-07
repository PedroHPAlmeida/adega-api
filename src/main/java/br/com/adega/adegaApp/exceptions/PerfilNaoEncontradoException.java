package br.com.adega.adegaApp.exceptions;

public class PerfilNaoEncontradoException extends RuntimeException{
    public PerfilNaoEncontradoException(String message) {
        super(message);
    }
}

package br.com.adega.adegaApp.execeptions;

public class PerfilNaoEncontradoException extends RuntimeException{
    public PerfilNaoEncontradoException(String message) {
        super(message);
    }
}

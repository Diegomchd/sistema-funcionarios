package br.com.gestaofuncionarios.exception;

public class FuncionarioJaCadastradoException extends RuntimeException {

    public FuncionarioJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}
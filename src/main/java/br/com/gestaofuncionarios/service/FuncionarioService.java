
package br.com.gestaofuncionarios.service;

import br.com.gestaofuncionarios.model.Funcionario;
import br.com.gestaofuncionarios.repository.FuncionarioRepository;
import br.com.gestaofuncionarios.exception.FuncionarioNaoEncontradoException;

import java.util.List;
import java.util.Locale;

public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService() {
        this.repository = new FuncionarioRepository();
    }

    public void cadastrar(Funcionario funcionario) {
        repository.adicionar(funcionario);
    }

    public Funcionario buscarFuncionario(int identificador) {

        Funcionario funcionario =
            repository.buscarPorIdentificador(identificador);

        if (funcionario == null) {
            throw new FuncionarioNaoEncontradoException(
                "Funcionário com identificador " + identificador + " não encontrado."
            );
        }

        return funcionario;
    }

    public Funcionario buscarFuncionario(String cpf) {

        if (cpf == null) {
            throw new IllegalArgumentException("Informe um CPF para a busca.");
        }

        String cpfNormalizado = cpf.trim()
            .replace(".", "")
            .replace("-", "");

        if (!cpfNormalizado.matches("\\d{11}")) {
            throw new IllegalArgumentException(
                "O CPF de busca deve conter 11 dígitos."
            );
        }

        for (Funcionario funcionario : repository.listarTodos()) {

            if (funcionario.getCpf().equals(cpfNormalizado)) {
                return funcionario;
            }
        }

        throw new FuncionarioNaoEncontradoException(
            "Funcionário com CPF " + cpfNormalizado + " não encontrado."
        );
    }

    public Funcionario buscarFuncionario(String nome, boolean buscaExata) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                "Informe um nome para realizar a busca."
            );
        }

        String nomeBusca = nome.trim().toLowerCase(Locale.ROOT);

        for (Funcionario funcionario : repository.listarTodos()) {

            String nomeFuncionario =
                funcionario.getNome().toLowerCase(Locale.ROOT);

            if (buscaExata && nomeFuncionario.equals(nomeBusca)) {
                return funcionario;
            }

            if (!buscaExata && nomeFuncionario.contains(nomeBusca)) {
                return funcionario;
            }
        }

        throw new FuncionarioNaoEncontradoException(
            "Funcionário com nome " + nome + " não encontrado."
        );
    }

    public List<Funcionario> listarFuncionarios() {
        return repository.listarTodos();
    }

    public void removerFuncionario(int identificador) {

        boolean removido = repository.remover(identificador);

        if (!removido) {
            throw new FuncionarioNaoEncontradoException(
                "Funcionário com identificador " + identificador + " não encontrado."
            );
        }
    }

    public double calcularFolhaSalarial() {

        double total = 0;

        for (Funcionario funcionario : repository.listarTodos()) {
            total += funcionario.calcularRemuneracao();
        }

        return total;
    }
}

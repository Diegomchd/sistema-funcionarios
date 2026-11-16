
package br.com.gestaofuncionarios.repository;

import br.com.gestaofuncionarios.model.Funcionario;
import br.com.gestaofuncionarios.exception.FuncionarioJaCadastradoException;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {

    private final List<Funcionario> funcionarios = new ArrayList<>();

    public void adicionar(Funcionario funcionario) {

        if (funcionario == null) {
            throw new IllegalArgumentException(
                "Não é possível cadastrar um funcionário nulo."
            );
        }

        if (buscarPorIdentificador(funcionario.getIdentificador()) != null) {

            throw new FuncionarioJaCadastradoException(
                "Já existe um funcionário com o identificador "
                + funcionario.getIdentificador() + "."
            );
        }

        funcionarios.add(funcionario);
    }

    public Funcionario buscarPorIdentificador(int identificador) {

        for (Funcionario funcionario : funcionarios) {

            if (funcionario.getIdentificador() == identificador) {
                return funcionario;
            }
        }

        return null;
    }

    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios);
    }

    public boolean remover(int identificador) {

        Funcionario funcionario = buscarPorIdentificador(identificador);

        if (funcionario == null) {
            return false;
        }

        funcionarios.remove(funcionario);

        return true;
    }
}

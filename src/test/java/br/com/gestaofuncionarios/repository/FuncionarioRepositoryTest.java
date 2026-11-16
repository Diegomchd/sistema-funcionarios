package br.com.gestaofuncionarios.repository;

import br.com.gestaofuncionarios.exception.FuncionarioJaCadastradoException;
import br.com.gestaofuncionarios.model.Funcionario;
import br.com.gestaofuncionarios.model.FuncionarioCLT;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioRepositoryTest {

    @Test
    void deveAdicionarEBuscarFuncionarioPorIdentificador() {
        FuncionarioRepository repository = new FuncionarioRepository();

        Funcionario funcionario = new FuncionarioCLT(
                1, "Carlos", "12345678909", 3000.0, 500.0
        );

        repository.adicionar(funcionario);

        assertEquals(funcionario, repository.buscarPorIdentificador(1));
    }

    @Test
    void deveListarTodosOsFuncionarios() {
        FuncionarioRepository repository = new FuncionarioRepository();

        repository.adicionar(new FuncionarioCLT(
                1, "Carlos", "12345678909", 3000.0, 500.0
        ));

        repository.adicionar(new FuncionarioCLT(
                2, "Ana", "98765432100", 2500.0, 300.0
        ));

        assertEquals(2, repository.listarTodos().size());
    }

    @Test
    void deveRemoverFuncionario() {
        FuncionarioRepository repository = new FuncionarioRepository();

        repository.adicionar(new FuncionarioCLT(
                1, "Carlos", "12345678909", 3000.0, 500.0
        ));

        assertTrue(repository.remover(1));
        assertEquals(0, repository.listarTodos().size());
    }

    @Test
void naoDevePermitirIdentificadorDuplicado() {
    FuncionarioRepository repository = new FuncionarioRepository();

    repository.adicionar(new FuncionarioCLT(
            1, "Carlos", "12345678909", 3000.0, 500.0
    ));

    assertThrows(
            FuncionarioJaCadastradoException.class,
            () -> repository.adicionar(new FuncionarioCLT(
                    1, "Ana", "98765432100", 2500.0, 300.0
            ))
    );
}

@Test
void naoDeveAdicionarFuncionarioNulo() {

    FuncionarioRepository repository = new FuncionarioRepository();

    assertThrows(
        IllegalArgumentException.class,
        () -> repository.adicionar(null)
    );
}

@Test
void deveRetornarFalseAoRemoverFuncionarioInexistente() {

    FuncionarioRepository repository = new FuncionarioRepository();

    assertFalse(repository.remover(99));
}

}
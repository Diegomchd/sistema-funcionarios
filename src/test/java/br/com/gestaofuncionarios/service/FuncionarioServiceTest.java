
package br.com.gestaofuncionarios.service;

import br.com.gestaofuncionarios.model.Funcionario;
import br.com.gestaofuncionarios.model.FuncionarioCLT;
import br.com.gestaofuncionarios.model.FuncionarioPJ;
import br.com.gestaofuncionarios.model.FuncionarioComissionado;

import br.com.gestaofuncionarios.exception.FuncionarioNaoEncontradoException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioServiceTest {

    @Test
    void deveCadastrarEBuscarFuncionarioPorIdentificador() {
        FuncionarioService service = new FuncionarioService();

        Funcionario funcionario = new FuncionarioCLT(
            1, "Carlos Silva", "12345678909", 3000.0, 500.0
        );

        service.cadastrar(funcionario);

        assertEquals(funcionario, service.buscarFuncionario(1));
    }

    @Test
    void deveBuscarFuncionarioPorCpf() {
        FuncionarioService service = new FuncionarioService();

        Funcionario funcionario = new FuncionarioCLT(
            1, "Carlos Silva", "12345678909", 3000.0, 500.0
        );

        service.cadastrar(funcionario);

        assertEquals(funcionario, service.buscarFuncionario("12345678909"));
    }

    @Test
    void deveBuscarFuncionarioPorNomeExato() {
        FuncionarioService service = new FuncionarioService();

        Funcionario funcionario = new FuncionarioCLT(
            1, "Carlos Silva", "12345678909", 3000.0, 500.0
        );

        service.cadastrar(funcionario);

        assertEquals(funcionario, service.buscarFuncionario("Carlos Silva", true));
    }

    @Test
    void deveBuscarFuncionarioPorParteDoNome() {
        FuncionarioService service = new FuncionarioService();

        Funcionario funcionario = new FuncionarioCLT(
            1, "Carlos Silva", "12345678909", 3000.0, 500.0
        );

        service.cadastrar(funcionario);

        assertEquals(funcionario, service.buscarFuncionario("Carlos", false));
    }

    @Test
    void deveLancarExcecaoQuandoFuncionarioNaoForEncontrado() {
        FuncionarioService service = new FuncionarioService();

        assertThrows(
            FuncionarioNaoEncontradoException.class,
            () -> service.buscarFuncionario(99)
        );
    }

    @Test
    void deveRemoverFuncionario() {
        FuncionarioService service = new FuncionarioService();

        Funcionario funcionario = new FuncionarioCLT(
            1, "Carlos Silva", "12345678909", 3000.0, 500.0
        );

        service.cadastrar(funcionario);
        service.removerFuncionario(1);

        assertTrue(service.listarFuncionarios().isEmpty());
    }

    @Test
    void deveCalcularFolhaSalarialDeTodosOsFuncionarios() {
        FuncionarioService service = new FuncionarioService();

        service.cadastrar(new FuncionarioCLT(
            1, "Carlos", "12345678909", 3000.0, 500.0
        ));

        service.cadastrar(new FuncionarioPJ(
            2, "Ana", "98765432100", 0.0, 200.0, 2
        ));

        service.cadastrar(new FuncionarioComissionado(
            3, "João", "11122233396", 1000.0, 10.0, 5000.0
        ));

        assertEquals(5400.0, service.calcularFolhaSalarial(), 0.001);
    }

@Test
void naoDeveAceitarBuscaPorNomeNuloOuVazio() {

    FuncionarioService service = new FuncionarioService();

    assertThrows(
        IllegalArgumentException.class,
        () -> service.buscarFuncionario(null, false)
    );

    assertThrows(
        IllegalArgumentException.class,
        () -> service.buscarFuncionario("   ", false)
    );
}

@Test
void naoDeveAceitarCpfDeBuscaInvalido() {

    FuncionarioService service = new FuncionarioService();

    assertThrows(
        IllegalArgumentException.class,
        () -> service.buscarFuncionario(null)
    );

    assertThrows(
        IllegalArgumentException.class,
        () -> service.buscarFuncionario("123")
    );
}

}

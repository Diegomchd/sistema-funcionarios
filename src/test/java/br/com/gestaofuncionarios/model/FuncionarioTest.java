
package br.com.gestaofuncionarios.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

    private Funcionario criarFuncionario(
            int identificador,
            String nome,
            String cpf,
            double salario) {

        return new Funcionario(identificador, nome, cpf, salario) {

            @Override
            public double calcularRemuneracao() {
                return getSalarioBase();
            }
        };
    }

    @Test
    void deveCriarFuncionarioComDadosValidos() {

        Funcionario funcionario = criarFuncionario(
            1, " Ana ", "123.456.789-09", 2000.0
        );

        assertEquals(1, funcionario.getIdentificador());
        assertEquals("Ana", funcionario.getNome());
        assertEquals("12345678909", funcionario.getCpf());
        assertEquals(2000.0, funcionario.getSalarioBase());
        assertEquals(2000.0, funcionario.calcularRemuneracao());
    }

    @Test
    void naoDeveAceitarIdentificadorInvalido() {

        assertThrows(IllegalArgumentException.class,
            () -> criarFuncionario(0, "Ana", "12345678909", 2000.0));

        assertThrows(IllegalArgumentException.class,
            () -> criarFuncionario(-1, "Ana", "12345678909", 2000.0));
    }

    @Test
    void naoDeveAceitarNomeInvalido() {

        assertThrows(IllegalArgumentException.class,
            () -> criarFuncionario(1, null, "12345678909", 2000.0));

        assertThrows(IllegalArgumentException.class,
            () -> criarFuncionario(1, "   ", "12345678909", 2000.0));
    }

    @Test
    void naoDeveAceitarCpfInvalido() {

        assertThrows(IllegalArgumentException.class,
            () -> criarFuncionario(1, "Ana", null, 2000.0));

        assertThrows(IllegalArgumentException.class,
            () -> criarFuncionario(1, "Ana", "12345678901", 2000.0));

        assertThrows(IllegalArgumentException.class,
            () -> criarFuncionario(1, "Ana", "11111111111", 2000.0));

        assertThrows(IllegalArgumentException.class,
            () -> criarFuncionario(1, "Ana", "12345", 2000.0));
    }

    @Test
    void naoDeveAceitarSalarioInvalido() {

        assertThrows(IllegalArgumentException.class,
            () -> criarFuncionario(1, "Ana", "12345678909", -100.0));

        assertThrows(IllegalArgumentException.class,
            () -> criarFuncionario(1, "Ana", "12345678909", Double.NaN));

        assertThrows(IllegalArgumentException.class,
            () -> criarFuncionario(
                1, "Ana", "12345678909", Double.POSITIVE_INFINITY
            ));
    }
}

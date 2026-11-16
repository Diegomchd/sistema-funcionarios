package br.com.gestaofuncionarios.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TributavelTest {

    @Test
    void deveCalcularDescontoPercentual() {
        FuncionarioCLT funcionario = new FuncionarioCLT(
                1, "Carlos", "12345678909", 3000.0, 500.0
        );

        Tributavel tributavel = funcionario;

        assertEquals(350.0, tributavel.calcularDesconto(10.0), 0.001);
    }

    @Test
    void naoDeveAceitarPercentualInvalido() {
        FuncionarioCLT funcionario = new FuncionarioCLT(
                1, "Carlos", "12345678909", 3000.0, 500.0
        );

        Tributavel tributavel = funcionario;

        assertThrows(IllegalArgumentException.class,
                () -> tributavel.calcularDesconto(110.0));
    }

@Test
void deveCalcularDescontoNasTresCategoriasPorPolimorfismo() {

    Tributavel[] funcionarios = {
        new FuncionarioCLT(
            1, "Carlos", "12345678909", 3000.0, 500.0
        ),
        new FuncionarioPJ(
            2, "Mariana", "98765432100", 0.0, 200.0, 2
        ),
        new FuncionarioComissionado(
            3, "Roberto", "11122233396", 1000.0, 10.0, 5000.0
        )
    };

    assertEquals(350.0, funcionarios[0].calcularDesconto(10.0), 0.001);
    assertEquals(40.0, funcionarios[1].calcularDesconto(10.0), 0.001);
    assertEquals(150.0, funcionarios[2].calcularDesconto(10.0), 0.001);
}

}
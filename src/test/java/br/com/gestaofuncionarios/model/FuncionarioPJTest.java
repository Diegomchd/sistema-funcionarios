
package br.com.gestaofuncionarios.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FuncionarioPJTest {

    @Test
    void deveCalcularRemuneracaoPorProjetosConcluidos() {

        FuncionarioPJ funcionario = new FuncionarioPJ(
                2,
                "Mariana",
                "98765432100",
                0.0,
                1500.0,
                3
        );

        assertEquals(4500.0, funcionario.calcularRemuneracao(), 0.001);
    }
    @Test
void deveCalcularDescontoPercentual() {
    FuncionarioPJ funcionario = new FuncionarioPJ(
            2, "Mariana", "98765432100", 0.0, 1500.0, 3
    );

    assertEquals(450.0, funcionario.calcularDesconto(10.0), 0.001);
}


@Test
void naoDeveAceitarValoresInvalidos() {

    assertThrows(IllegalArgumentException.class, () ->
        new FuncionarioPJ(
            2, "Mariana", "98765432100", 0.0,
            Double.NaN, 3
        )
    );

    assertThrows(IllegalArgumentException.class, () ->
        new FuncionarioPJ(
            2, "Mariana", "98765432100", 0.0,
            1500.0, -1
        )
    );

    assertThrows(IllegalArgumentException.class, () ->
        new FuncionarioPJ(
            2, "Mariana", "98765432100", 0.0,
            Double.POSITIVE_INFINITY, 3
        )
    );
}

@Test
void naoDeveAceitarPercentualDeDescontoInvalido() {

    FuncionarioPJ funcionario = new FuncionarioPJ(
        2, "Mariana", "98765432100", 0.0, 1500.0, 3
    );

    assertThrows(IllegalArgumentException.class,
        () -> funcionario.calcularDesconto(-1.0));

    assertThrows(IllegalArgumentException.class,
        () -> funcionario.calcularDesconto(101.0));

    assertThrows(IllegalArgumentException.class,
        () -> funcionario.calcularDesconto(Double.NaN));
}

}

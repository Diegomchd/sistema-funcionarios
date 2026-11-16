
package br.com.gestaofuncionarios.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FuncionarioComissionadoTest {

    @Test
    void deveCalcularSalarioBaseMaisComissaoSobreVendas() {

        FuncionarioComissionado funcionario = new FuncionarioComissionado(
                3,
                "Roberto",
                "11122233396",
                3000.0,
                10.0,
                10000.0
        );

        assertEquals(4000.0, funcionario.calcularRemuneracao(), 0.001);
    }
    @Test
void deveCalcularDescontoPercentual() {
    FuncionarioComissionado funcionario = new FuncionarioComissionado(
            3, "Roberto", "11122233396", 3000.0, 10.0, 10000.0
    );

    assertEquals(400.0, funcionario.calcularDesconto(10.0), 0.001);
}

@Test
void naoDeveAceitarValoresInvalidos() {

    assertThrows(IllegalArgumentException.class, () ->
        new FuncionarioComissionado(
            3, "Roberto", "11122233396",
            3000.0, Double.NaN, 10000.0
        )
    );

    assertThrows(IllegalArgumentException.class, () ->
        new FuncionarioComissionado(
            3, "Roberto", "11122233396",
            3000.0, 10.0, Double.POSITIVE_INFINITY
        )
    );

    assertThrows(IllegalArgumentException.class, () ->
        new FuncionarioComissionado(
            3, "Roberto", "11122233396",
            3000.0, 10.0, -100.0
        )
    );
}

@Test
void naoDeveAceitarPercentualDeDescontoInvalido() {

    FuncionarioComissionado funcionario =
        new FuncionarioComissionado(
            3, "Roberto", "11122233396",
            3000.0, 10.0, 10000.0
        );

    assertThrows(IllegalArgumentException.class,
        () -> funcionario.calcularDesconto(-1.0));

    assertThrows(IllegalArgumentException.class,
        () -> funcionario.calcularDesconto(101.0));

    assertThrows(IllegalArgumentException.class,
        () -> funcionario.calcularDesconto(Double.NaN));
}

}

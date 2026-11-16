
package br.com.gestaofuncionarios.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FuncionarioCLTTest {

    @Test
    void deveCalcularRemuneracaoComSalarioBaseEBeneficios() {

        FuncionarioCLT funcionario = new FuncionarioCLT(
                1,
                "Carlos",
                "12345678909",
                3000.0,
                500.0
        );

        double remuneracao = funcionario.calcularRemuneracao();

        assertEquals(3500.0, remuneracao);
    }

    @Test
void naoDeveAceitarBeneficiosNegativos() {

    assertThrows(IllegalArgumentException.class, () ->
            new FuncionarioCLT(
                    4,
                    "Ana",
                    "12345678909",
                    3000.0,
                    -100.0
            )
    );
}
@Test
void deveCalcularDescontoPercentual() {
    FuncionarioCLT funcionario = new FuncionarioCLT(
            1, "Carlos", "12345678909", 3000.0, 500.0
    );

    assertEquals(350.0, funcionario.calcularDesconto(10.0), 0.001);
}


@Test
void naoDeveAceitarBeneficiosNaoFinitos() {

    assertThrows(IllegalArgumentException.class, () ->
        new FuncionarioCLT(
            1, "Carlos", "12345678909", 3000.0, Double.NaN
        )
    );

    assertThrows(IllegalArgumentException.class, () ->
        new FuncionarioCLT(
            1, "Carlos", "12345678909", 3000.0,
            Double.POSITIVE_INFINITY
        )
    );
}

@Test
void naoDeveAceitarPercentualDeDescontoInvalido() {

    FuncionarioCLT funcionario = new FuncionarioCLT(
        1, "Carlos", "12345678909", 3000.0, 500.0
    );

    assertThrows(IllegalArgumentException.class,
        () -> funcionario.calcularDesconto(-1.0));

    assertThrows(IllegalArgumentException.class,
        () -> funcionario.calcularDesconto(101.0));

    assertThrows(IllegalArgumentException.class,
        () -> funcionario.calcularDesconto(Double.NaN));

    assertThrows(IllegalArgumentException.class,
        () -> funcionario.calcularDesconto(Double.POSITIVE_INFINITY));
}

}

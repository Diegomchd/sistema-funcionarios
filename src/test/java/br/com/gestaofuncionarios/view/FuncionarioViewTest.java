
package br.com.gestaofuncionarios.view;

import br.com.gestaofuncionarios.model.Funcionario;
import br.com.gestaofuncionarios.model.FuncionarioCLT;
import br.com.gestaofuncionarios.model.FuncionarioPJ;
import br.com.gestaofuncionarios.model.FuncionarioComissionado;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioViewTest {

    @Test
    void deveGerarRelatorioComTodosOsFuncionarios() {
        FuncionarioView view = new FuncionarioView();

        List<Funcionario> funcionarios = List.of(
            new FuncionarioCLT(
                1, "Carlos", "12345678909", 3000.0, 500.0
            ),
            new FuncionarioPJ(
                2, "Ana", "98765432100", 0.0, 200.0, 2
            ),
            new FuncionarioComissionado(
                3, "João", "11122233396", 1000.0, 10.0, 5000.0
            )
        );

        String relatorio = view.gerarRelatorio(funcionarios, 5400.0);

        assertTrue(relatorio.contains("Carlos"));
        assertTrue(relatorio.contains("Ana"));
        assertTrue(relatorio.contains("João"));
        assertTrue(relatorio.contains("3500.00"));
        assertTrue(relatorio.contains("400.00"));
        assertTrue(relatorio.contains("1500.00"));
        assertTrue(relatorio.contains("5400.00"));
    }

    @Test
    void deveGerarRelatorioMesmoSemFuncionarios() {
        FuncionarioView view = new FuncionarioView();

        String relatorio = view.gerarRelatorio(List.of(), 0.0);

        assertNotNull(relatorio);
        assertTrue(relatorio.contains("0.00"));
    }
}

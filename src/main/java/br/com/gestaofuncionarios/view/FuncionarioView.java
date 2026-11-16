
package br.com.gestaofuncionarios.view;

import br.com.gestaofuncionarios.model.Funcionario;

import java.util.List;
import java.util.Locale;

public class FuncionarioView {

    public String gerarRelatorio(
        List<Funcionario> funcionarios,
        double totalFolha
    ) {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("\n========== RELATÓRIO DE FUNCIONÁRIOS ==========\n");

        if (funcionarios.isEmpty()) {
            relatorio.append("Nenhum funcionário cadastrado.\n");
        } else {
            for (Funcionario funcionario : funcionarios) {

                relatorio.append("\nIdentificador: ")
                    .append(funcionario.getIdentificador());

                relatorio.append("\nNome: ")
                    .append(funcionario.getNome());

                relatorio.append("\nCPF: ")
                    .append(funcionario.getCpf());

                relatorio.append("\nRemuneração: R$ ")
                    .append(String.format(
                        Locale.US,
                        "%.2f",
                        funcionario.calcularRemuneracao()
                    ));

                relatorio.append("\n----------------------------------------------\n");
            }
        }

        relatorio.append("\nTotal da folha salarial: R$ ")
            .append(String.format(Locale.US, "%.2f", totalFolha));

        relatorio.append("\n==============================================\n");

        return relatorio.toString();
    }
}

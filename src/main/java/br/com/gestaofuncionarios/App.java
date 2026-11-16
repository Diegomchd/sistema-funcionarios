
package br.com.gestaofuncionarios;

import br.com.gestaofuncionarios.model.Funcionario;
import br.com.gestaofuncionarios.model.FuncionarioCLT;
import br.com.gestaofuncionarios.model.FuncionarioPJ;
import br.com.gestaofuncionarios.model.FuncionarioComissionado;

import br.com.gestaofuncionarios.service.FuncionarioService;
import br.com.gestaofuncionarios.view.FuncionarioView;

import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);

    private static final FuncionarioService service = new FuncionarioService();

    private static final FuncionarioView view = new FuncionarioView();

    public static void main(String[] args) {

        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> cadastrarFuncionario();
                    case 2 -> listarFuncionarios();
                    case 3 -> buscarFuncionario();
                    case 4 -> removerFuncionario();
                    case 5 -> exibirFolhaSalarial();
                    case 6 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 6);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n======================================");
        System.out.println("   SISTEMA DE GESTÃO DE FUNCIONÁRIOS");
        System.out.println("======================================");
        System.out.println("1 - Cadastrar funcionário");
        System.out.println("2 - Listar funcionários");
        System.out.println("3 - Buscar funcionário");
        System.out.println("4 - Remover funcionário");
        System.out.println("5 - Exibir folha salarial");
        System.out.println("6 - Sair");
        System.out.println("======================================");
    }

    private static void cadastrarFuncionario() {

        System.out.println("\n--- CADASTRO DE FUNCIONÁRIO ---");

        int identificador = lerInteiro("Identificador: ");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("CPF (11 dígitos): ");
        String cpf = scanner.nextLine().trim();

        System.out.println("\nTipo de funcionário:");
        System.out.println("1 - CLT");
        System.out.println("2 - PJ");
        System.out.println("3 - Comissionado");

        int tipo = lerInteiro("Escolha: ");

        Funcionario funcionario;

        switch (tipo) {
            case 1 -> {
                double salarioBase = lerDouble("Salário base: ");
                double beneficios = lerDouble("Benefícios: ");

                funcionario = new FuncionarioCLT(
                    identificador, nome, cpf, salarioBase, beneficios
                );
            }

            case 2 -> {
                double valorProjeto = lerDouble("Valor por projeto: ");
                int projetos = lerInteiro("Projetos concluídos: ");

                funcionario = new FuncionarioPJ(
                    identificador, nome, cpf, 0.0, valorProjeto, projetos
                );
            }

            case 3 -> {
                double salarioBase = lerDouble("Salário base: ");
                double percentual = lerDouble("Percentual de comissão: ");
                double vendas = lerDouble("Total de vendas: ");

                funcionario = new FuncionarioComissionado(
                    identificador, nome, cpf, salarioBase, percentual, vendas
                );
            }

            default -> {
                System.out.println("Tipo de funcionário inválido.");
                return;
            }
        }

        service.cadastrar(funcionario);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private static void listarFuncionarios() {
        System.out.println(
            view.gerarRelatorio(
                service.listarFuncionarios(),
                service.calcularFolhaSalarial()
            )
        );
    }

    private static void buscarFuncionario() {

        System.out.println("\n--- BUSCAR FUNCIONÁRIO ---");
        System.out.println("1 - Buscar por identificador");
        System.out.println("2 - Buscar por CPF");
        System.out.println("3 - Buscar por nome");

        int tipo = lerInteiro("Escolha: ");

        Funcionario funcionario;

        switch (tipo) {
            case 1 -> {
                int identificador = lerInteiro("Identificador: ");
                funcionario = service.buscarFuncionario(identificador);
            }

            case 2 -> {
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                funcionario = service.buscarFuncionario(cpf);
            }

            case 3 -> {
                System.out.print("Nome ou parte do nome: ");
                String nome = scanner.nextLine();
                funcionario = service.buscarFuncionario(nome, false);
            }

            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        System.out.println(
            view.gerarRelatorio(
                java.util.List.of(funcionario),
                funcionario.calcularRemuneracao()
            )
        );
    }

    private static void removerFuncionario() {

        int identificador = lerInteiro(
            "Identificador do funcionário que deseja remover: "
        );

        service.removerFuncionario(identificador);

        System.out.println("Funcionário removido com sucesso!");
    }

    private static void exibirFolhaSalarial() {

        System.out.printf(
            java.util.Locale.US,
            "%nTotal da folha salarial: R$ %.2f%n",
            service.calcularFolhaSalarial()
        );
    }

    private static int lerInteiro(String mensagem) {

        while (true) {
            System.out.print(mensagem);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    
private static double lerDouble(String mensagem) {

    while (true) {
        System.out.print(mensagem);

        try {
            String entrada = scanner.nextLine()
                .trim()
                .replace(",", ".");

            double valor = Double.parseDouble(entrada);

            if (!Double.isFinite(valor)) {
                System.out.println(
                    "Digite um valor numérico finito."
                );
                continue;
            }

            return valor;

        } catch (NumberFormatException e) {
            System.out.println(
                "Digite um valor numérico válido."
            );
        }
    }
}

}

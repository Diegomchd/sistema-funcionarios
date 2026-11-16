
package br.com.gestaofuncionarios.model;

public class FuncionarioPJ extends Funcionario implements Tributavel {

    private double valorPorProjeto;
    private int projetosConcluidos;

    public FuncionarioPJ(
            int identificador,
            String nome,
            String cpf,
            double salarioBase,
            double valorPorProjeto,
            int projetosConcluidos) {

        super(identificador, nome, cpf, salarioBase);

        if (!Double.isFinite(valorPorProjeto) || valorPorProjeto < 0) {
            throw new IllegalArgumentException(
                "O valor por projeto deve ser finito e não pode ser negativo."
            );
        }

        if (projetosConcluidos < 0) {
            throw new IllegalArgumentException(
                "A quantidade de projetos não pode ser negativa."
            );
        }

        this.valorPorProjeto = valorPorProjeto;
        this.projetosConcluidos = projetosConcluidos;
    }

    public double getValorPorProjeto() {
        return valorPorProjeto;
    }

    public int getProjetosConcluidos() {
        return projetosConcluidos;
    }

    @Override
    public double calcularRemuneracao() {
        return valorPorProjeto * projetosConcluidos;
    }

    @Override
    public double calcularDesconto(double percentual) {

        if (!Double.isFinite(percentual) || percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException(
                "O percentual deve estar entre 0 e 100."
            );
        }

        return calcularRemuneracao() * (percentual / 100.0);
    }
}

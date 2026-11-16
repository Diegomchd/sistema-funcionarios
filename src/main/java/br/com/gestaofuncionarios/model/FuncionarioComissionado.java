
package br.com.gestaofuncionarios.model;

public class FuncionarioComissionado extends Funcionario implements Tributavel {

    private double percentualComissao;
    private double totalVendas;

    public FuncionarioComissionado(
            int identificador,
            String nome,
            String cpf,
            double salarioBase,
            double percentualComissao,
            double totalVendas) {

        super(identificador, nome, cpf, salarioBase);

        if (!Double.isFinite(percentualComissao)
                || percentualComissao < 0
                || percentualComissao > 100) {

            throw new IllegalArgumentException(
                "O percentual de comissão deve estar entre 0 e 100."
            );
        }

        if (!Double.isFinite(totalVendas) || totalVendas < 0) {
            throw new IllegalArgumentException(
                "O total de vendas deve ser finito e não pode ser negativo."
            );
        }

        this.percentualComissao = percentualComissao;
        this.totalVendas = totalVendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    @Override
    public double calcularRemuneracao() {

        double comissao = totalVendas * (percentualComissao / 100.0);

        return getSalarioBase() + comissao;
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

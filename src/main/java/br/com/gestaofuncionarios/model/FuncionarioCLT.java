
package br.com.gestaofuncionarios.model;

public class FuncionarioCLT extends Funcionario implements Tributavel {

    private double beneficios;

    public FuncionarioCLT(
            int identificador,
            String nome,
            String cpf,
            double salarioBase,
            double beneficios) {

        super(identificador, nome, cpf, salarioBase);

        if (!Double.isFinite(beneficios) || beneficios < 0) {
            throw new IllegalArgumentException(
                "Os benefícios devem ser finitos e não podem ser negativos."
            );
        }

        this.beneficios = beneficios;
    }

    public double getBeneficios() {
        return beneficios;
    }

    @Override
    public double calcularRemuneracao() {
        return getSalarioBase() + beneficios;
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

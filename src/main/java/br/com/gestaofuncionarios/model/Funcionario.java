
package br.com.gestaofuncionarios.model;

public abstract class Funcionario implements Identificavel {

    private int identificador;
    private String nome;
    private String cpf;
    private double salarioBase;

    public Funcionario(int identificador, String nome, String cpf, double salarioBase) {

        if (identificador <= 0) {
            throw new IllegalArgumentException("O identificador deve ser positivo.");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        String cpfNormalizado = cpf == null ? "" : cpf.trim();

        if (cpfNormalizado.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            cpfNormalizado = cpfNormalizado.replace(".", "").replace("-", "");
        }

        if (!cpfValido(cpfNormalizado)) {
            throw new IllegalArgumentException("O CPF informado é inválido.");
        }

        if (!Double.isFinite(salarioBase) || salarioBase < 0) {
            throw new IllegalArgumentException("O salário deve ser finito e não pode ser negativo.");
        }

        this.identificador = identificador;
        this.nome = nome.trim();
        this.cpf = cpfNormalizado;
        this.salarioBase = salarioBase;
    }

    private static boolean cpfValido(String cpf) {

        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int primeiroDigito = calcularDigito(cpf, 9);

        int segundoDigito = calcularDigito(
            cpf.substring(0, 9) + primeiroDigito,
            10
        );

        return cpf.charAt(9) - '0' == primeiroDigito
            && cpf.charAt(10) - '0' == segundoDigito;
    }

    private static int calcularDigito(String cpf, int quantidadeDigitos) {

        int soma = 0;

        for (int i = 0; i < quantidadeDigitos; i++) {
            int numero = cpf.charAt(i) - '0';
            int peso = quantidadeDigitos + 1 - i;

            soma += numero * peso;
        }

        int resto = soma % 11;

        return resto < 2 ? 0 : 11 - resto;
    }

    @Override
    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public abstract double calcularRemuneracao();
}

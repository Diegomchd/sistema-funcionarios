
# Sistema de Gestão de Funcionários

Projeto desenvolvido em Java como atividade prática de Programação Orientada a Objetos (POO).

O sistema permite cadastrar e gerenciar funcionários de diferentes modalidades de contratação, calcular remunerações e consultar a folha salarial.

## Funcionalidades

- Cadastro de funcionários.
- Listagem de funcionários cadastrados.
- Busca por identificador, CPF ou nome.
- Remoção de funcionários.
- Cálculo de remuneração conforme o tipo de contratação.
- Cálculo da folha salarial total.
- Validação de dados e tratamento de exceções.

## Tipos de funcionários

- **CLT:** salário-base acrescido de benefícios.
- **PJ:** valor por projeto multiplicado pela quantidade de projetos realizados.
- **Comissionado:** salário-base acrescido de comissão sobre as vendas.

## Tecnologias utilizadas

- Java 21
- Maven
- JUnit 5
- Git e GitHub

## Conceitos de POO aplicados

- Abstração
- Encapsulamento
- Herança
- Polimorfismo
- Interfaces
- Sobrecarga de métodos
- Tratamento de exceções

## Estrutura do projeto

- `model`: classes de funcionários e interfaces.
- `repository`: armazenamento e acesso aos funcionários.
- `service`: regras de negócio.
- `view`: apresentação dos relatórios.
- `exception`: exceções personalizadas.

## Como executar

É necessário ter Java 21 e Maven instalados.

Clone o repositório:

```bash
git clone https://github.com/Diegomchd/sistema-funcionarios.git```

Entre na pasta do projeto:

```bash
cd sistema-funcionarios
```

Execute a aplicação:

```bash
mvn exec:java "-Dexec.mainClass=br.com.gestaofuncionarios.App"
```

## Como executar os testes

Para executar os testes automatizados:

```bash
mvn clean test
```

Os testes utilizam JUnit 5.

## Observação

Os dados dos funcionários são armazenados em memória durante a execução. Ao encerrar o programa, os dados cadastrados não são persistidos.

---

Projeto acadêmico desenvolvido para praticar conceitos de Programação Orientada a Objetos em Java.

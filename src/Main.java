import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Empresa empresa = new Empresa();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Busca por Nome ou CPF");
            System.out.println("2. Busca por Salário");
            System.out.println("3. Inserir Funcionário");
            System.out.println("4. Imprimir Funcionários (Nome, Cargo e Salário)");
            System.out.println("5. Imprimir Todos os Dados dos Funcionários");
            System.out.println("6. Funcionário com Maior Salário ");
            System.out.println("7. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome ou CPF a ser buscado: ");
                    String valor = scanner.nextLine();
                    List<Funcionario> resultados = empresa.buscaPorNomeOuCpf(valor);
                    if (!resultados.isEmpty()) {
                        System.out.println("\nResultados da busca:");
                        for (Funcionario funcionario : resultados) {
                            System.out.println(funcionario.getAllInfo());
                        }
                    } else {
                        System.out.println("Nenhum funcionário encontrado com esse nome ou CPF.");
                    }
                    break;

                case 2:
                    System.out.print("Salário mínimo: ");
                    double salarioMin = scanner.nextDouble();
                    System.out.print("Salário máximo: ");
                    double salarioMax = scanner.nextDouble();
                    List<Funcionario> resultadosSalario = empresa.buscaPorSalario(salarioMin, salarioMax);
                    if (!resultadosSalario.isEmpty()) {
                        System.out.println("\nFuncionários com salário no intervalo:");
                        for (Funcionario funcionario : resultadosSalario) {
                            System.out.println(funcionario.getAllInfo());
                        }
                    } else {
                        System.out.println("Nenhum funcionário encontrado nesse intervalo de salário.");
                    }
                    break;

                case 3:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    System.out.print("Sexo (M/F): ");
                    char sexo = scanner.next().charAt(0);
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Cargo: ");
                    String cargo = scanner.nextLine();
                    System.out.print("Salário: ");
                    double salario = scanner.nextDouble();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Data de Nascimento: ");
                    String dataNascimento = scanner.nextLine();

                    Funcionario novoFuncionario = new Funcionario(nome, idade, sexo, cpf, cargo, salario, dataNascimento);
                    empresa.insereFuncionario(novoFuncionario);
                    break;

                case 4:
                    empresa.imprimeFuncionarios(1);
                    break;

                case 5:
                    empresa.imprimeFuncionarios(2);
                    break;

                case 6:
                    Funcionario funcionarioMaiorSalario = empresa.funcionarioComMaiorSalario();
                    if (funcionarioMaiorSalario != null) {
                        System.out.println("Funcionário Com Maior Salário: ");
                        System.out.println(funcionarioMaiorSalario.getInfo());
                    } else {
                        System.out.println("Não há funcionários cadastrados.");
                    }
                    break;

                case 7:
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
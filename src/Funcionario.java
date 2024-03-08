public class Funcionario {
    private String nome;
    private int idade;
    private char sexo;
    private String cpf;
    private String cargo;
    private double salario;
    private String dataNascimento;

    public Funcionario(String nome, int idade, char sexo, String cpf, String cargo, double salario, String dataNascimento) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
        this.dataNascimento = dataNascimento;
    }

    // Getters para acessar os atributos privados
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalario() {
        return salario;
    }

    // Método para retornar informações básicas do funcionário (nome, cargo e salário)
    public String getInfo() {
        return "Nome: " + nome + ", Cargo: " + cargo + ", Salário: " + salario;
    }

    // Método para retornar todas as informações do funcionário
    public String getAllInfo() {
        return "Nome: " + nome + ", Idade: " + idade + ", Sexo: " + sexo + ", CPF: " + cpf + ", Cargo: " + cargo + ", Salário: " + salario + ", Data de Nascimento: " + dataNascimento;
    }
}
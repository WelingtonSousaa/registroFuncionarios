import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private List<Funcionario> funcionarios;
    public Empresa() {
        this.funcionarios = new ArrayList<>();
    }

    public List<Funcionario> buscaPorNomeOuCpf(String valor) {
        List<Funcionario> encontrados = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().toLowerCase().contains(valor.toLowerCase()) || funcionario.getCpf().contains(valor)) {
                encontrados.add(funcionario);
            }
        }
        return encontrados;
    }

    public List<Funcionario> buscaPorSalario(double salarioMin, double salarioMax) {
        List<Funcionario> encontrados = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getSalario() >= salarioMin && funcionario.getSalario() <= salarioMax) {
                encontrados.add(funcionario);
            }
        }
        return encontrados;
    }

    public void insereFuncionario(Funcionario funcionario) {
        if (funcionarios.size() < 100) {
            funcionarios.add(funcionario);
            System.out.println("Funcionário inserido com sucesso.");
        } else {
            System.out.println("A capacidade máxima de funcionários foi atingida.");
        }
    }

    // Método utilizado para retornar os funcionários cadastrados para a interface
    public List<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }
    public void imprimeFuncionarios(int opcao) {
        if (opcao == 1) {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario.getInfo());
            }
        } else if (opcao == 2) {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario.getAllInfo());
            }
        }
    }

    public Funcionario funcionarioComMaiorSalario() {
        if (funcionarios.isEmpty()) {
            return null;
        }

        Funcionario maiorSalario = funcionarios.get(0);
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getSalario() > maiorSalario.getSalario()) {
                maiorSalario = funcionario;
            }
        }
        System.out.println(maiorSalario);
        return maiorSalario;
    }
}
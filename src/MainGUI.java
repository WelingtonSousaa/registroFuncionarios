import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class MainGUI extends JFrame {
    private Empresa empresa = new Empresa();
    private JTextField inputField;
    private JTextArea outputArea;
    private List<Funcionario> funcionarios = new ArrayList<>(); // Usando java.util.List e ArrayList para funcionários

    private void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    public MainGUI() {
        setTitle("Gerenciamento de Funcionários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridLayout(7, 1));
        add(menuPanel, BorderLayout.WEST);

        JButton btnBuscaNomeCpf = new JButton("Busca por Nome ou CPF");
        btnBuscaNomeCpf.addActionListener(e -> buscarPorNomeOuCpf()); // Vincula o ActionListener ao método buscaPorNomeOuCpf
        menuPanel.add(btnBuscaNomeCpf);

        JButton btnBuscaSalario = new JButton("Busca por Salário");
        btnBuscaSalario.addActionListener(e -> buscaPorSalario()); // Vincula o ActionListener ao método buscaPorSalario
        menuPanel.add(btnBuscaSalario);

        JButton btnInserirFuncionario = new JButton("Inserir Funcionário");
        btnInserirFuncionario.addActionListener(e -> exibirSubMenuInserirFuncionario());
        menuPanel.add(btnInserirFuncionario);

        JButton btnImprimirFunc = new JButton("Imprimir Funcionários");
        btnImprimirFunc.addActionListener(e -> imprimirFuncionarios(1));
        menuPanel.add(btnImprimirFunc);

        JButton btnImprimirTodos = new JButton("Imprimir Todos os Dados");
        btnImprimirTodos.addActionListener(e -> imprimirFuncionarios(2));
        menuPanel.add(btnImprimirTodos);

        JButton btnFuncMaiorSalario = new JButton("Funcionário com Maior Salário");
        btnFuncMaiorSalario.addActionListener(e -> funcionarioMaiorSalario());
        menuPanel.add(btnFuncMaiorSalario);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> encerrarPrograma());
        menuPanel.add(btnSair);

        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void buscarPorNomeOuCpf(){
        String valor = JOptionPane.showInputDialog("Digite o nome ou CPF a ser buscado:");
        List<Funcionario> funcionariosEncontrados = empresa.buscaPorNomeOuCpf(valor);
        outputArea.setText("");
        if(funcionariosEncontrados.isEmpty()){
            outputArea.append("Nenhum funcionário encontrado com o nome ou CPF forncido.");
        } else {
            for (Funcionario funcionario : funcionariosEncontrados) {
                outputArea.append(funcionario.getInfo() + "\n");
            }
        }
    }

    private void buscaPorSalario() {
        String salarioMinString = JOptionPane.showInputDialog("Digite o salário mínimo:");
        String salarioMaxString = JOptionPane.showInputDialog("Digite o salário máximo:");

        try {
            double salarioMin = Double.parseDouble(salarioMinString);
            double salarioMax = Double.parseDouble(salarioMaxString);

            List<Funcionario> funcionariosEncontrados = empresa.buscaPorSalario(salarioMin, salarioMax);

            outputArea.setText("");

            if (funcionariosEncontrados.isEmpty()) {
                outputArea.append("Nenhum funcionário encontrado na faixa salarial fornecida.");
            } else {
                for (Funcionario funcionario : funcionariosEncontrados) {
                    outputArea.append(funcionario.getInfo() + "\n");
                }
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Por favor, insira valores numéricos válidos para a faixa salarial.");
        }
    }

    private void exibirSubMenuInserirFuncionario() {
        JFrame inserirFuncionarioFrame = new JFrame("Inserir Funcionário");
        inserirFuncionarioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inserirFuncionarioFrame.setSize(300, 300);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Idade:"));
        JTextField idadeField = new JTextField();
        panel.add(idadeField);

        panel.add(new JLabel("Sexo (M/F):"));
        JTextField sexoField = new JTextField();
        panel.add(sexoField);

        panel.add(new JLabel("CPF:"));
        JTextField cpfField = new JTextField();
        panel.add(cpfField);

        panel.add(new JLabel("Cargo:"));
        JTextField cargoField = new JTextField();
        panel.add(cargoField);

        panel.add(new JLabel("Salário:"));
        JTextField salarioField = new JTextField();
        panel.add(salarioField);

        panel.add(new JLabel("Data de Nascimento:"));
        JTextField dataNascimentoField = new JTextField();
        panel.add(dataNascimentoField);

        JButton btnInserir = new JButton("Inserir");
        btnInserir.addActionListener(e -> {
            String nome = nomeField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            char sexo = sexoField.getText().charAt(0);
            String cpf = cpfField.getText();
            String cargo = cargoField.getText();
            double salario = Double.parseDouble(salarioField.getText());
            String dataNascimento = dataNascimentoField.getText();

            Funcionario novoFuncionario = new Funcionario(nome, idade, sexo, cpf, cargo, salario, dataNascimento);
            empresa.insereFuncionario(novoFuncionario);

            exibirMensagem("Usuário inserido com sucesso!");

            inserirFuncionarioFrame.dispose();
        });
        panel.add(btnInserir);

        inserirFuncionarioFrame.add(panel);
        inserirFuncionarioFrame.setVisible(true);
    }

    private void imprimirFuncionarios(int opcao) {
        List<Funcionario> funcionarios = empresa.getFuncionarios();
        outputArea.setText(""); // Limpa o conteúdo da JTextArea

        if (opcao == 1) {
            for (Funcionario funcionario : funcionarios) {
                outputArea.append(funcionario.getInfo() + "\n");
            }
        } else if (opcao == 2) {
            for (Funcionario funcionario : funcionarios) {
                outputArea.append(funcionario.getAllInfo() + "\n");
            }
        }
    }

    private void funcionarioMaiorSalario() {
        Funcionario funcionarioMaiorSalario = empresa.funcionarioComMaiorSalario();
        if (funcionarioMaiorSalario != null) {
            outputArea.setText("Funcionário com maior salário: " + funcionarioMaiorSalario.getInfo());
        } else {
            outputArea.setText("Nenhum funcionário cadastrado.");
        }
    }

    private void encerrarPrograma() {
        int confirm = JOptionPane.showConfirmDialog(null, "Deseja encerrar o programa?", "Encerrar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Fecha a janela
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI mainGUI = new MainGUI();
            mainGUI.setVisible(true);
        });
    }
}

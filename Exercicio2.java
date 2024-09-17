import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Funcionario {
    private String nome;
    private int idade;
    private double salario;

    public Funcionario(String nome, int idade, double salario) {
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade + ", Salário: R$" + String.format("%.2f", salario);
    }
}

public class GerenciamentoFuncionarios {

    private List<Funcionario> funcionarios;

    public GerenciamentoFuncionarios() {
        funcionarios = new ArrayList<>();
    }

    public void adicionarFuncionario(String nome, int idade, double salario) {
        Funcionario funcionario = new Funcionario(nome, idade, salario);
        funcionarios.add(funcionario);
        System.out.println("Funcionário adicionado com sucesso.");
    }

    public void removerFuncionario(String nome) {
        Iterator<Funcionario> iterator = funcionarios.iterator();
        boolean encontrado = false;
        
        while (iterator.hasNext()) {
            Funcionario funcionario = iterator.next();
            if (funcionario.getNome().equalsIgnoreCase(nome)) {
                iterator.remove();
                encontrado = true;
                System.out.println("Funcionário removido com sucesso.");
                break;
            }
        }
        
        if (!encontrado) {
            System.out.println("Funcionário não encontrado.");
        }
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    public void calcularMediaSalarial() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado para calcular a média salarial.");
            return;
        }
        
        double somaSalarios = 0;
        for (Funcionario funcionario : funcionarios) {
            somaSalarios += funcionario.getSalario();
        }
        
        double mediaSalarial = somaSalarios / funcionarios.size();
        System.out.println("Média salarial dos funcionários: R$" + String.format("%.2f", mediaSalarial));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciamentoFuncionarios sistema = new GerenciamentoFuncionarios();
        
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Funcionário");
            System.out.println("2. Remover Funcionário");
            System.out.println("3. Listar Funcionários");
            System.out.println("4. Calcular Média Salarial");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            
            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do funcionário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a idade do funcionário: ");
                    int idade = scanner.nextInt();
                    System.out.print("Digite o salário do funcionário: ");
                    double salario = scanner.nextDouble();
                    scanner.nextLine(); // Consumir a nova linha
                    sistema.adicionarFuncionario(nome, idade, salario);
                    break;
                case 2:
                    System.out.print("Digite o nome do funcionário a ser removido: ");
                    nome = scanner.nextLine();
                    sistema.removerFuncionario(nome);
                    break;
                case 3:
                    sistema.listarFuncionarios();
                    break;
                case 4:
                    sistema.calcularMediaSalarial();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}

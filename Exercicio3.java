import java.util.InputMismatchException;
import java.util.Scanner;

// Classe base para operações matemáticas
abstract class Operacao {
    public abstract double calcular(double a, double b) throws ArithmeticException;
}

// Subclasse para soma
class Soma extends Operacao {
    @Override
    public double calcular(double a, double b) {
        return a + b;
    }
}

// Subclasse para subtração
class Subtracao extends Operacao {
    @Override
    public double calcular(double a, double b) {
        return a - b;
    }
}

// Subclasse para multiplicação
class Multiplicacao extends Operacao {
    @Override
    public double calcular(double a, double b) {
        return a * b;
    }
}

// Subclasse para divisão
class Divisao extends Operacao {
    @Override
    public double calcular(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }
        return a / b;
    }
}

// Subclasse para raiz quadrada
class RaizQuadrada extends Operacao {
    @Override
    public double calcular(double a, double b) throws ArithmeticException {
        if (a < 0) {
            throw new ArithmeticException("Não é possível calcular a raiz quadrada de um número negativo.");
        }
        return Math.sqrt(a);
    }
}

public class CalculadoraAvancada {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\nMenu:");
            System.out.println("1. Soma");
            System.out.println("2. Subtração");
            System.out.println("3. Multiplicação");
            System.out.println("4. Divisão");
            System.out.println("5. Raiz Quadrada");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = 0;
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next(); // Limpar o buffer
                continue;
            }

            switch (opcao) {
                case 1:
                    realizarOperacao(new Soma(), scanner);
                    break;
                case 2:
                    realizarOperacao(new Subtracao(), scanner);
                    break;
                case 3:
                    realizarOperacao(new Multiplicacao(), scanner);
                    break;
                case 4:
                    realizarOperacao(new Divisao(), scanner);
                    break;
                case 5:
                    realizarRaizQuadrada(scanner);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }

    private static void realizarOperacao(Operacao operacao, Scanner scanner) {
        try {
            System.out.print("Digite o primeiro número: ");
            double num1 = scanner.nextDouble();
            System.out.print("Digite o segundo número: ");
            double num2 = scanner.nextDouble();
            double resultado = operacao.calcular(num1, num2);
            System.out.println("Resultado: " + resultado);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.next(); // Limpar o buffer
        } catch (ArithmeticException e) {
            System.out.println("Erro matemático: " + e.getMessage());
        }
    }

    private static void realizarRaizQuadrada(Scanner scanner) {
        try {
            System.out.print("Digite o número: ");
            double num = scanner.nextDouble();
            Operacao raizQuadrada = new RaizQuadrada();
            double resultado = raizQuadrada.calcular(num, 0); // O segundo parâmetro não é utilizado para raiz quadrada
            System.out.println("Resultado: " + resultado);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.next(); // Limpar o buffer
        } catch (ArithmeticException e) {
            System.out.println("Erro matemático: " + e.getMessage());
        }
    }
}

import java.util.Scanner;

// Exceção personalizada para valores inválidos
class ValorInvalidoException extends Exception {
    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }
}

public class CalculadoraFatorial {

    // Método recursivo para calcular o fatorial
    public static long calcularFatorial(int n) throws ValorInvalidoException {
        if (n < 0) {
            throw new ValorInvalidoException("Número negativo não é permitido.");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * calcularFatorial(n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.print("Digite um número inteiro positivo para calcular o fatorial (ou -1 para sair): ");
            int numero = 0;
            try {
                numero = scanner.nextInt();
                if (numero == -1) {
                    System.out.println("Saindo...");
                    continuar = false;
                    continue;
                }
                if (numero < 0) {
                    throw new ValorInvalidoException("Número negativo não é permitido.");
                }
                long fatorial = calcularFatorial(numero);
                System.out.println("Fatorial de " + numero + " é " + fatorial);
            } catch (ValorInvalidoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.next(); // Limpar o buffer
            }
        }
        scanner.close();
    }
}

import java.util.Scanner;

public class SistemaAprovacao {

    // Função para calcular o status do aluno com base na média
    public static String calcularStatus(double media) {
        if (media >= 7) {
            return "Aprovado";
        } else if (media >= 5) {
            return "Recuperação";
        } else {
            return "Reprovado";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] notas = new double[4];
        int numDisciplinas = 4;
        boolean bonusAplicado = true;

        // Captura as notas das disciplinas
        for (int i = 0; i < numDisciplinas; i++) {
            while (true) {
                System.out.print("Digite a nota da disciplina " + (i + 1) + ": ");
                try {
                    double nota = Double.parseDouble(scanner.nextLine());
                    if (nota >= 0 && nota <= 10) {
                        notas[i] = nota;
                        break;
                    } else {
                        System.out.println("Nota inválida. A nota deve estar entre 0 e 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                }
            }
        }

        // Calcula a média
        double somaNotas = 0;
        for (double nota : notas) {
            somaNotas += nota;
            if (nota <= 9) {
                bonusAplicado = false;
            }
        }
        double media = somaNotas / numDisciplinas;

        // Verifica se o aluno tem nota maior que 9 em todas as disciplinas
        if (bonusAplicado) {
            media *= 1.10; // Aplica o bônus de 10%
        }

        // Calcula o status do aluno
        String status = calcularStatus(media);

        // Exibe o resultado
        System.out.printf("Média final: %.2f%n", media);
        System.out.println("Status do aluno: " + status);

        scanner.close();
    }
}

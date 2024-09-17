import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe para representar um item de estoque
class Item {
    private String nome;
    private int quantidade;

    public Item(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return nome + ":" + quantidade;
    }
}

// Classe para gerenciar o estoque
public class SistemaEstoque {

    private static final String ARQUIVO_ESTOQUE = "estoque.txt";
    private List<Item> estoque;

    public SistemaEstoque() {
        estoque = new ArrayList<>();
        carregarEstoque();
    }

    // Adiciona um item ao estoque
    public void adicionarItem(String nome, int quantidade) {
        for (Item item : estoque) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                salvarEstoque();
                return;
            }
        }
        estoque.add(new Item(nome, quantidade));
        salvarEstoque();
    }

    // Remove um item do estoque
    public void removerItem(String nome) {
        for (int i = 0; i < estoque.size(); i++) {
            if (estoque.get(i).getNome().equalsIgnoreCase(nome)) {
                estoque.remove(i);
                salvarEstoque();
                return;
            }
        }
        System.out.println("Item não encontrado.");
    }

    // Lista todos os itens do estoque
    public void listarItens() {
        if (estoque.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            for (Item item : estoque) {
                System.out.println(item);
            }
        }
    }

    // Salva o estoque em um arquivo
    private void salvarEstoque() {
        try (FileWriter writer = new FileWriter(ARQUIVO_ESTOQUE)) {
            for (Item item : estoque) {
                writer.write(item.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o estoque: " + e.getMessage());
        }
    }

    // Carrega o estoque a partir de um arquivo
    private void carregarEstoque() {
        File arquivo = new File(ARQUIVO_ESTOQUE);
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo de estoque: " + e.getMessage());
                return;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ESTOQUE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(":");
                if (partes.length == 2) {
                    String nome = partes[0];
                    int quantidade = Integer.parseInt(partes[1]);
                    estoque.add(new Item(nome, quantidade));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o estoque: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Formato inválido no arquivo de estoque.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaEstoque sistema = new SistemaEstoque();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Item");
            System.out.println("2. Remover Item");
            System.out.println("3. Listar Itens");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = 0;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next(); // Limpar o buffer
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do item: ");
                    String nomeAdicionar = scanner.nextLine();
                    System.out.print("Digite a quantidade do item: ");
                    int quantidadeAdicionar = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    sistema.adicionarItem(nomeAdicionar, quantidadeAdicionar);
                    break;
                case 2:
                    System.out.print("Digite o nome do item a ser removido: ");
                    String nomeRemover = scanner.nextLine();
                    sistema.removerItem(nomeRemover);
                    break;
                case 3:
                    sistema.listarItens();
                    break;
                case 4:
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
}

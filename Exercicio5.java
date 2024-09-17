import java.util.Scanner;

// Classe para representar um contato
class Contato {
    private String nome;
    private String telefone;

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Telefone: " + telefone;
    }
}

// Exceção personalizada para quando o contato não é encontrado
class ContatoNaoEncontradoException extends Exception {
    public ContatoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

// Exceção personalizada para quando a agenda está cheia
class AgendaCheiaException extends Exception {
    public AgendaCheiaException(String mensagem) {
        super(mensagem);
    }
}

// Classe para gerenciar a agenda telefônica
public class AgendaTelefonica {
    private static final int CAPACIDADE_MAXIMA = 100;
    private Contato[] contatos;
    private int contador;

    public AgendaTelefonica() {
        contatos = new Contato[CAPACIDADE_MAXIMA];
        contador = 0;
    }

    // Adiciona um contato na agenda
    public void adicionarContato(String nome, String telefone) throws AgendaCheiaException {
        if (contador >= CAPACIDADE_MAXIMA) {
            throw new AgendaCheiaException("A agenda está cheia. Não é possível adicionar mais contatos.");
        }
        contatos[contador++] = new Contato(nome, telefone);
        System.out.println("Contato adicionado com sucesso.");
    }

    // Remove um contato pelo nome
    public void removerContato(String nome) throws ContatoNaoEncontradoException {
        int indice = -1;
        for (int i = 0; i < contador; i++) {
            if (contatos[i].getNome().equalsIgnoreCase(nome)) {
                indice = i;
                break;
            }
        }
        if (indice == -1) {
            throw new ContatoNaoEncontradoException("Contato não encontrado.");
        }
        for (int i = indice; i < contador - 1; i++) {
            contatos[i] = contatos[i + 1];
        }
        contatos[--contador] = null; // Remove o último contato da lista
        System.out.println("Contato removido com sucesso.");
    }

    // Busca um contato pelo nome
    public void buscarContato(String nome) throws ContatoNaoEncontradoException {
        for (int i = 0; i < contador; i++) {
            if (contatos[i].getNome().equalsIgnoreCase(nome)) {
                System.out.println(contatos[i]);
                return;
            }
        }
        throw new ContatoNaoEncontradoException("Contato não encontrado.");
    }

    // Lista todos os contatos
    public void listarContatos() {
        if (contador == 0) {
            System.out.println("Nenhum contato cadastrado.");
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println(contatos[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgendaTelefonica agenda = new AgendaTelefonica();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Remover Contato");
            System.out.println("3. Buscar Contato");
            System.out.println("4. Listar Contatos");
            System.out.println("5. Sair");
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
                    System.out.print("Digite o nome do contato: ");
                    String nomeAdicionar = scanner.nextLine();
                    System.out.print("Digite o telefone do contato: ");
                    String telefoneAdicionar = scanner.nextLine();
                    try {
                        agenda.adicionarContato(nomeAdicionar, telefoneAdicionar);
                    } catch (AgendaCheiaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Digite o nome do contato a ser removido: ");
                    String nomeRemover = scanner.nextLine();
                    try {
                        agenda.removerContato(nomeRemover);
                    } catch (ContatoNaoEncontradoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Digite o nome do contato a ser buscado: ");
                    String nomeBuscar = scanner.nextLine();
                    try {
                        agenda.buscarContato(nomeBuscar);
                    } catch (ContatoNaoEncontradoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 4:
                    agenda.listarContatos();
                    break;
                case 5:
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

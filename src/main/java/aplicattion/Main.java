package aplicattion;
import exceptions.LivroExistente;
import exceptions.ItemInexistente;
import exceptions.OpcaoInvalida;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Biblioteca biblioteca = new Biblioteca();
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws OpcaoInvalida {
        int opcao = 1;
        while(opcao != 3){
            System.out.println("-------------------------------------------------");
            System.out.println("|O que deseja fazer?");
            System.out.println("|1 - Gerir livros.");
            System.out.println("|2 - Gerir usuários.");
            System.out.println("|3 - Sair");
            System.out.print("|Digite uma opção: ");

            int escolha = scan.nextInt();
            try {
                switch (escolha) {
                    case 1:
                        opcao = 1;
                        gerirLivros();
                        break;
                    case 2:
                        opcao = 2;
                        gerirUsuarios();
                        break;
                    case 3:
                        opcao = 3;
                        break;
                    default:
                        throw new OpcaoInvalida("Opção escolhida é inválida!");
                }
            } catch (OpcaoInvalida | LivroExistente ex){
                System.out.println("-------------------------------------------------");
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void gerirLivros() throws OpcaoInvalida, LivroExistente {
        int opcao = 1;
        while (opcao != 4) {
            System.out.println("-------------------------------------------------");
            System.out.println("|O que deseja fazer?");
            System.out.println("|1 - Adicionar livro");
            System.out.println("|2 - Remover livro");
            System.out.println("|3 - Ver livros");
            System.out.println("|4 - Sair");
            System.out.print("|Digite sua escolha: ");

            int escolha = scan.nextInt();
            try {
                switch (escolha) {
                    case 1:
                        opcao = 1;
                        scan.nextLine();
                        adicionarLivro();
                        break;
                    case 2:
                        opcao = 2;
                        removerLivro();
                        break;
                    case 3:
                        opcao = 3;
                        listarLivros();
                        break;
                    case 4:
                        opcao = 4;
                        break;
                    default:
                        throw new OpcaoInvalida("Opção escolhida é inválida!");
                }
            } catch (OpcaoInvalida | LivroExistente e) {
                System.out.println("-------------------------------------------------");
                System.out.println(e.getMessage());
            }
        }
    }

    public static void adicionarLivro() throws LivroExistente {
        System.out.println("-------------------------------------------------");
        System.out.print("|Digite a quantidade de livros a serem adicionados: ");
        String quantidadeStr = scan.nextLine();

        int quantidade;
        try {
            if (!ehInteiro(quantidadeStr)) {
                throw new InputMismatchException("Entrada inválida. Digite um número inteiro maior que zero!");
            }
            quantidade = Integer.parseInt(quantidadeStr);
        } catch (InputMismatchException e) {
            System.out.println("-------------------------------------------------");
            System.out.println(e.getMessage());
            return;
        }

        int contador = 1;
        while (contador <= quantidade) {
            String titulo = "";
            try {
                System.out.println();
                System.out.print("Digite o título do livro " + contador + ": ");
                titulo = scan.nextLine();
                if (biblioteca.localizarLivro(titulo)) {
                    throw new LivroExistente("Já existe um livro com este título na biblioteca!");
                }
            } catch (LivroExistente e) {
                System.out.println();
                System.out.println(e.getMessage());
                continue;
            }

            System.out.print("Digite o nome do autor: ");
            String autor = scan.nextLine();
            biblioteca.adicionarLivro(new Livro(titulo, autor));
            contador++;
        }

        if (contador == 1) {
            System.out.println("Livro adicionado com sucesso!");
        } else {
            System.out.println("Livros adicionados com sucesso!");
        }
    }

    public static boolean ehInteiro(String str) {
        try {
            int num = Integer.parseInt(str);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void removerLivro(){
        System.out.println("-------------------------------------------------");
        System.out.print("Digite o ID do livro a ser removido: ");
        int id = scan.nextInt();
        try{
            if(!biblioteca.validarIdRemocaoLivro(id)){
                throw new ItemInexistente("Este livro não existe!");
            }
        } catch (ItemInexistente e) {
            System.out.println();
            System.out.println(e.getMessage());
        }
        biblioteca.removerLivro(id);
    }

    public static void listarLivros(){
        biblioteca.listar();
    }

    public static void gerirUsuarios() throws OpcaoInvalida {
        System.out.println("-------------------------------------------------");
        System.out.println("|O que deseja fazer?");
        System.out.println("|1 - Cadastrar usuário");
        System.out.println("|2 - Remover usuário");
        System.out.println("|3 - Listar usuários");
        System.out.println("|4 - Sair");
        System.out.print("|Digite sua opção:");
        int opcao = scan.nextInt();

        switch (opcao){
            case 1:
                cadastrarUsuario();
                break;
            case 2:
                removerUsuario();
                break;
            case 3:
                listarUsuarios();
            case 4:
                break;
            default:
                throw new OpcaoInvalida("Opção inválida!");
        }
    }

    public static void cadastrarUsuario() throws OpcaoInvalida {
        System.out.println("-------------------------------------------------");
        System.out.print("Digite o nome do usuário: ");
        String nome = scan.next();
        scan.nextLine();
        int idade;
        try{
            System.out.print("Digite a idade do usuário: ");
            idade = scan.nextInt();
            scan.nextLine();
            if(idade < 18){
                throw new OpcaoInvalida("O usuário não tem uma idade permitida!");
            }
        } catch (OpcaoInvalida e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Digite o tipo do usuário(1 para cliente e 2 para admin): ");
        int numTipo = scan.nextInt();

        switch (numTipo){
            case 1:
                Usuario user = new Usuario();
                user.setNome(nome);
                user.setIdade(idade);
                user.setTipo("Cliente");
                biblioteca.adicionarUsuario(user);
                break;
            case 2:
                Administrador admin = new Administrador();
                admin.setNome(nome);
                admin.setIdade(idade);
                admin.setTipo("Admin");
                biblioteca.adicionarUsuario(admin);
                break;
            default:
                throw new OpcaoInvalida("Selecione uma opção válida(1 para Cliente ou 2 para Admin)!");
        }
    }

    public static void removerUsuario(){
        System.out.println("-------------------------------------------------");
        System.out.print("Digite o nome do usuário a ser removido: ");
        String nome = scan.nextLine();
        scan.nextLine();
        try{
            if(!biblioteca.validarIdRemocaoUsuario(nome)){
                throw new ItemInexistente("Este usuário não existe!");
            }
        } catch (ItemInexistente e) {
            System.out.println();
            System.out.println(e.getMessage());
        }
        biblioteca.removerUsuario(nome);
    }

    public static void listarUsuarios(){
        biblioteca.listarUsuarios();
    }
}
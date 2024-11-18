package aplicattion;
import exceptions.LivroExistente;
import exceptions.LivroInexistente;
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
            System.out.println("|2 - Cadastrar usuários.");
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
                        cadastrarUsuario();
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
            if(!biblioteca.validarIdRemocao(id)){
                throw new LivroInexistente("Este livro não existe!");
            }
        } catch (LivroInexistente e) {
            System.out.println();
            System.out.println(e.getMessage());
        }
        biblioteca.removerLivro(id);
    }

    public static void listarLivros(){
        biblioteca.listarLivros();
    }

    public static void cadastrarUsuario(){

    }
}
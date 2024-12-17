package aplicattion;
import exceptions.LivroExistente;
import exceptions.ItemInexistente;
import exceptions.OpcaoInvalida;
import mongodb.IniciaConexao;
import mongodb.Login;
import mongodb.VerificaConteudoBD;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Biblioteca biblioteca = new Biblioteca();
    static Scanner scan = new Scanner(System.in);
    static IniciaConexao connectDB = new IniciaConexao();

    public static void main(String[] args) {
        connectDB.iniciaConexao();
        System.out.println("-------------------------------------------------");
        System.out.println("|1 - Fazer Login");
        System.out.println("|2 - Fazer Cadastro");
        System.out.print("Digite sua escolha: ");
        int escolha = scan.nextInt();
        scan.nextLine();

        try{
            switch (escolha){
                case 1:
                    login();
                    break;
                case 2:
                    cadastro();
                    break;
                default:
                    throw new OpcaoInvalida("Digite uma opção válida!");

            }
        } catch (OpcaoInvalida ex){
            System.out.println("-------------------------------------------------");
            System.out.println(ex.getMessage());
        }
    }

    public static void login() throws OpcaoInvalida {
        try {
            if (!VerificaConteudoBD.verificaConteudoDB()) {
                System.out.println("Não existem usuários no sistema! Por favor, cadastre-se.");
                cadastro();
                return;
            }
        } catch (Exception e) {
            System.err.println("Erro ao verificar conteúdo do banco de dados: " + e.getMessage());
            return;
        }

        System.out.println("-------------------------------------------------");
        System.out.print("| Digite seu usuário: ");
        String usuario = scan.nextLine();
        System.out.print("| Digite sua senha: ");
        String senha = scan.nextLine();

        while (true) {
            if (Login.login(usuario, senha)) {
                System.out.println("Usuário logado com sucesso!");
                int id = Login.retornaIdLogin(usuario, senha);
                biblioteca.adicionaUsuarioLogado(id);
                home();
                return;
            } else {
                System.out.println("Usuário ou senha inválida! Tente novamente.");
                System.out.print("| Digite seu usuário: ");
                usuario = scan.nextLine();
                System.out.print("| Digite sua senha: ");
                senha = scan.nextLine();
            }
        }
    }

    public static void cadastro() throws OpcaoInvalida {
        scan.nextLine();
        System.out.println("-------------------------------------------------");
        System.out.print("Digite o nome do usuário: ");
        String nome = scan.nextLine();
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
        System.out.print("Digite sua senha: ");
        String senha = scan.nextLine();

        Usuario user = new Usuario();
        user.setNome(nome);
        user.setIdade(idade);
        user.setTipo("Cliente");
        user.setSenha(senha);
        biblioteca.adicionarUsuario(user);
    }

    public static void home() throws OpcaoInvalida {
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
            System.out.println("|4 - Emprestar livro");
            System.out.println("|5 - Sair");
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
//                        emprestarLivro();
                        break;
                    case 5:
                        opcao = 5;
                        return;
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
        System.out.print("Digite o ISBN do livro a ser removido: ");
        int isbn = scan.nextInt();
        biblioteca.removerLivro(isbn);
    }

    public static void listarLivros(){
        biblioteca.listarTodosLivros();
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
        scan.nextLine();

        switch (opcao){
            case 1:
                cadastro();
                break;
            case 2:
                removerUsuario();
                break;
            case 3:
                listarUsuarios();
                break;
            case 4:
                break;
            default:
                throw new OpcaoInvalida("Opção inválida!");
        }
    }

    public static void removerUsuario(){
        System.out.println("-------------------------------------------------");
        System.out.print("|Digite o nome do usuário a ser removido: ");
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

    public static void listarLivrosDisponiveis(){
        int qtdLivrosDisponiveis =  biblioteca.livrosDisponiveis.size();

        if(qtdLivrosDisponiveis > 0){
            System.out.println("Atualmente existem " + qtdLivrosDisponiveis + " livros disponiveis: ");
            for(Livro livro: biblioteca.livrosDisponiveis){
                System.out.println(livro);
            }
            biblioteca.livrosDisponiveis.clear();
        } else{
            System.out.println("Não há livros disponíveis para empréstimo!");
        }
    }

//    public static void emprestarLivro() throws OpcaoInvalida {
//        System.out.println("-------------------------------------------------");
//        if(!biblioteca.validaLivrosDisponiveis()){
//            listarLivrosDisponiveis();
//            System.out.println();
//            System.out.print("|Digite o ID do livro que você deseja: ");
//            int idLivro = scan.nextInt();
//            try{
//                if(biblioteca.validaIdLivroDisponivel(idLivro)){
//                    biblioteca.mudaEstadoLivroParaEmprestado(idLivro);
//                } else{
//                    throw new OpcaoInvalida("Este livro não existe ou não está disponível!");
//                }
//            }catch (OpcaoInvalida e){
//                System.out.println(e.getMessage());
//            }
//        } else{
//            System.out.println("Não há livros disponíveis para empréstimo!");
//        }
//    }

    public static void devolverLivro() throws OpcaoInvalida{

    }
}
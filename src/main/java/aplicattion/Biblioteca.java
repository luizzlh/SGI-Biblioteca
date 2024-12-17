package aplicattion;
import exceptions.ItemInexistente;
import interfaces.Listagem;
import mongodb.*;

import java.util.ArrayList;

public class Biblioteca implements Listagem {
    private Livro livro;
    private ArrayList<ArrayList<ArrayList<Object>>> biblioteca = new ArrayList<>();
    private ArrayList<Livro> listaGeralLivros = new ArrayList<>();
    protected ArrayList<Livro> livrosDisponiveis = new ArrayList<>();
    private ArrayList<Pessoa> usuarios = new ArrayList<>();
    private InsereLivrosBiblioteca insereLivrosBiblioteca = new InsereLivrosBiblioteca();
    private LocalizaLivro validaNome = new LocalizaLivro();
    private int idUsuarioLogado = 0;

    public void adicionaUsuarioLogado(int id){
        idUsuarioLogado = id;
    }

    public void removeUsuarioLogado(){
        idUsuarioLogado = 0;
    }

    public void adicionarLivro(Livro livro){
        insereLivrosBiblioteca.insereLivro(livro);
    }

    public boolean localizarLivro(String nome){
        if(validaNome.localizaLivro(nome)){
            return true;
        }
        return false;
    }

    public void listarTodosLivros(){
        System.out.println();
//        System.out.println("Atualmente existem " + listaGeralLivros.size() + " livros na biblioteca: ");
//        for(Livro livro: listaGeralLivros){
//            System.out.println(livro);
//        }
        ExibirLivros.exibirLivros();
    }

    public boolean validaLivrosDisponiveis(){
        for(Livro livro: listaGeralLivros){
            if(livro.getEstado().equalsIgnoreCase("Disponível")){
                livrosDisponiveis.add(livro);
            }
        }

        if(listaGeralLivros.isEmpty()){
            return true;
        }
        return false;
    }

    public void removeLivro(int isbn){
        RemoveLivro.removeLivro(isbn);
    }

//    public void mudaEstadoLivroParaEmprestado(int id){
//        for(Livro livro: livrosDisponiveis){
//            if(livro.getId() == id){
//                livro.setEstado("Emprestado");
//                livrosDisponiveis.remove(livro);
//            }
//        }
//    }

//    public void mudaEstadoLivroParaDisponivel(int id){
//        for(Livro livro: listaGeralLivros){
//            if(livro.getId() == id){
//                livro.setEstado("Disponível");
//                livrosDisponiveis.add(livro);
//            }
//        }
//    }

//    public boolean validarIdLivroDisponivel(int id){
//        for(Livro livro: listaGeralLivros){
//            if(livro.getId() == id){
//                return true;
//            }
//        }
//        return false;
//    }

    public void removerLivro(int isbn){
        RemoveLivro.removeLivro(isbn);
    }

    public void adicionarUsuario(Pessoa pessoa){
        InsereDadosUsuario.insereDadosUsuario(pessoa);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public boolean localizarUsuario(String nome){
        for(Pessoa user: usuarios){
            if(user.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }

    public void listarUsuarios(){
        System.out.println();
        System.out.println("Atualmente existe " + usuarios.size() + " usuário(s) na biblioteca: ");
        for(Pessoa user: usuarios){
            System.out.println(user);
        }
    }

    public boolean validarIdRemocaoUsuario(String nome){
        for(Pessoa user: usuarios){
            if(user.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }

    public void removerUsuario(String nome){
        for(Pessoa user: usuarios){
            if(user.getNome().equalsIgnoreCase(nome)){
                int index = usuarios.indexOf(user);
                usuarios.remove(index);
                System.out.println("Usuário " + user.getNome() + " removido com sucesso!");
            }
        }
    }

    @Override
    public void listar() {

    }
}
package aplicattion;
import interfaces.Listagem;
import mongodb.InsereLivrosBiblioteca;
import mongodb.LocalizaLivro;

import java.util.ArrayList;

public class Biblioteca implements Listagem {
    private Livro livro;
    private ArrayList<ArrayList<ArrayList<Object>>> biblioteca = new ArrayList<>();
    private ArrayList<Livro> listaGeralLivros = new ArrayList<>();
    protected ArrayList<Livro> livrosDisponiveis = new ArrayList<>();
    private ArrayList<Pessoa> usuarios = new ArrayList<>();
    private InsereLivrosBiblioteca insereLivrosBiblioteca = new InsereLivrosBiblioteca();
    private LocalizaLivro validaNome = new LocalizaLivro();


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
        System.out.println("Atualmente existem " + listaGeralLivros.size() + " livros na biblioteca: ");
        for(Livro livro: listaGeralLivros){
            System.out.println(livro);
        }
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

    public boolean validaIdLivroDisponivel(int id){
        for(Livro livro: livrosDisponiveis){
            if(livro.getId() == id){
                return true;
            }
        }
        return false;
    }

    public void mudaEstadoLivroParaEmprestado(int id){
        for(Livro livro: livrosDisponiveis){
            if(livro.getId() == id){
                livro.setEstado("Emprestado");
                livrosDisponiveis.remove(livro);
            }
        }
    }

    public void mudaEstadoLivroParaDisponivel(int id){
        for(Livro livro: listaGeralLivros){
            if(livro.getId() == id){
                livro.setEstado("Disponível");
                livrosDisponiveis.add(livro);
            }
        }
    }

    public boolean validarIdRemocaoLivro(int id){
        for(Livro livro: listaGeralLivros){
            if(livro.getId() == id){
                return true;
            }
        }
        return false;
    }

    public void removerLivro(int id){
        id -= 1;
        Livro livro = listaGeralLivros.get(id);
        String titulo = livro.getTitulo();
        System.out.println("Livro a ser removido: " + titulo);
        listaGeralLivros.remove(id);
        System.out.println("Livro removido com sucesso!");
    }

    public void adicionarUsuario(Pessoa pessoa){
        usuarios.add(pessoa);
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
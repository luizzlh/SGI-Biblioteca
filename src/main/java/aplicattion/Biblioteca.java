package aplicattion;

import java.util.ArrayList;

public class Biblioteca {
    private Livro livro;
    private Usuarios usuario;
    private ArrayList<Livro> biblioteca = new ArrayList<Livro>();
    private ArrayList<Livro> livros = new ArrayList<>();

    public void adicionarLivro(Livro livro){
        biblioteca.add(livro);
    }

    public boolean localizarLivro(String nome){
        for(Livro livro: biblioteca){
            if(livro.getTitulo().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }

    public void listarLivros(){
        System.out.println();
        System.out.println("Atualmente existem " + biblioteca.size() + " livros na biblioteca: ");
        for(Livro livro: biblioteca){
            System.out.println(livro);
        }
    }

    public boolean validarIdRemocao(int id){
        for(Livro livro: biblioteca){
            if(livro.getId() == id){
                return true;
            }
        }
        return false;
    }

    public void removerLivro(int id){
        id -= 1;
        Livro livro = biblioteca.get(id);
        String titulo = livro.getTitulo();
        System.out.println("Livro a ser removido: " + titulo);
        biblioteca.remove(id);
        System.out.println("Livro removido com sucesso!");
    }
}
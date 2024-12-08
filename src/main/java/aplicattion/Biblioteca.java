package aplicattion;
import interfaces.Listagem;
import java.util.ArrayList;

public class Biblioteca implements Listagem {
    private Livro livro;
    private ArrayList<ArrayList<ArrayList<Object>>> biblioteca = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Pessoa> usuarios = new ArrayList<>();

    public void adicionarLivro(Livro livro){
        livros.add(livro);
    }

    public boolean localizarLivro(String nome){
        for(Livro livro: livros){
            if(livro.getTitulo().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }

    public void listarTodosLivros(){
        System.out.println();
        System.out.println("Atualmente existem " + livros.size() + " livros na biblioteca: ");
        for(Livro livro: livros){
            System.out.println(livro);
        }
    }

    public void listarLivrosDisponiveis(){
        System.out.println();
        for(Livro livro: livros){
            if(livro.getEstado().equalsIgnoreCase("Disponível")){
                System.out.println(livro.getEstado());
            }
        }
    }

    public boolean validarIdRemocaoLivro(int id){
        for(Livro livro: livros){
            if(livro.getId() == id){
                return true;
            }
        }
        return false;
    }

    public void removerLivro(int id){
        id -= 1;
        Livro livro = livros.get(id);
        String titulo = livro.getTitulo();
        System.out.println("Livro a ser removido: " + titulo);
        livros.remove(id);
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
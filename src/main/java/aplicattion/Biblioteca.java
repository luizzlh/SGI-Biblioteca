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

    public void listar(){
        System.out.println();
        System.out.println("Atualmente existem " + livros.size() + " livros na biblioteca: ");
        for(Livro livro: livros){
            System.out.println(livro);
        }
    }

    public boolean validarIdRemocao(int id){
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
        System.out.println("Atualmente existem " + usuarios.size() + " usuários na biblioteca: ");
        for(Pessoa user: usuarios){
            System.out.println(user);
        }
    }
}

//{
//    usuarios{
//        luiz{
//            nome: "Luiz",
//            idade: 20,
//                tipo: cliente,
//            livro{
//
//            }
//        }
//        vinicius{
//            nome:"Vinícius",
//            idade: 20,
//                tipo: admin,
//            livro{
//
//            }
//        }
//    }
//    livros{
//        livro1{
//            nome: "Código Limpo",
//            autor: "Robert C. Martin",
//            isbn: 10001,
//            id: 01
//        }
//        livro2{
//
//        }
//    }
//
//}
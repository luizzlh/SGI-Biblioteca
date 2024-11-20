package aplicattion;

public class Administrador extends Pessoa{
    private static int identificador = 0;
    private int id;
    private int idade;

    public Administrador(){
//        super(nome, tipo, idade);
        this.id = ++identificador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome(){
        return super.getNome();
    }

    public void setNome(String nome){
        super.setNome(nome);
    }

    public String getTipo(){
        return super.getTipo();
    }

    public void setTipo(String tipo){
        super.setTipo(tipo);
    }

    public int getIdade(){
        return super.getIdade();
    }

    public void setIdade(int idade){

    }
}
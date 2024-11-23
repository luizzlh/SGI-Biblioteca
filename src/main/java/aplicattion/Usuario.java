package aplicattion;

public class Usuario extends Pessoa{
    private static int identificador = 1000;
    private int id;
    private int idade;
    private String tipo;
    private String nome;

    public Usuario(){
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
        this.nome = nome;
    }

    public String getTipo(){
        return super.getTipo();
    }

    public void setTipo(String tipo){
        super.setTipo(tipo);
    }

    @Override
    public int getIdade() {
        return idade;
    }

    @Override
    public void setIdade(int idade) {
        this.idade = idade;
    }
}
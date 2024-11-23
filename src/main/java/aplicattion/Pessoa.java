package aplicattion;

public abstract class Pessoa {
    private String nome;
    private String tipo;
    private int idade;
    private static int identificador = 1000;
    private int id;

    public Pessoa(){
//        this.nome = nome;
//        this.tipo = tipo;
//        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "Pessoa{" + '\n' +
                "   Nome: " + getNome() + '\n' +
                "   Idade: " + getIdade() + '\n' +
                "   Tipo: " + getTipo() + '\n' +
                "   ID: " + getId() + '\n' +
                '}';
    }
}
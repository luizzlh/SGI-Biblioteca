package aplicattion;

public class Livro {
    private static int contador = 0;
    private String titulo;
    private String autor;
    private static int contadorISBN = 10000;
    private int isbn;
    private int id;
    private String estado;
    private Usuarios usuario;

    public Livro(String titulo, String autor){
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = ++contadorISBN;
        this.estado = "Disponível";
        this.id = ++contador;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Livro.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Livro{" + '\n' +
                "   Título: " + titulo + '\n' +
                "   Autor: " + autor + '\n' +
                "   Estado: " + estado + '\n' +
                "   Usuário: " + usuario + '\n' +
                "   Isbn: " + this.isbn + '\n' +
                "   Id: " + this.id + '\n' +
                '}' + '\n';
    }
}
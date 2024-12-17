package aplicattion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Livro {
    private String titulo;
    private String autor;
    private int isbn;
    private String estado;
    private Usuario usuario;

    public void incrementaIsbn(){
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")){
            MongoDatabase database = mongoClient.getDatabase("catalogo");
            MongoCollection<Document> collection = database.getCollection("isbn");

            Document filtro = new Document("identificador", 1);
            Document documento = collection.find(filtro).first();

            if (documento != null) {
                int valor = documento.getInteger("ISBN");
                valor = valor + 1;
                Document atualizacao = new Document("$set", new Document("ISBN", valor));
                collection.updateOne(filtro, atualizacao);
            } else{
                System.out.println("Documento não existe!");
            }
        }
    }

    public int buscaIsbn() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")) {
            MongoDatabase database = mongoClient.getDatabase("catalogo");
            MongoCollection<Document> collection = database.getCollection("isbn");

            Document filtro = new Document("identificador", 1);
            Document documento = collection.find(filtro).first();

            if (documento != null) {
                incrementaIsbn();
                return documento.getInteger("ISBN");
            } else {
                return -1;
            }

        }
    }

    public Livro(String titulo, String autor){
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = buscaIsbn();
        this.estado = "Disponível";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
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

    @Override
    public String toString() {
        return "Livro{" + '\n' +
                "   Título: " + titulo + '\n' +
                "   Autor: " + autor + '\n' +
                "   Estado: " + estado + '\n' +
                "   Usuário: " + usuario + '\n' +
                "   Isbn: " + this.isbn + '\n' +
                '}' + '\n';
    }
}
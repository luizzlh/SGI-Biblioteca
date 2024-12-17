package aplicattion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Usuario extends Pessoa{
    private static int identificador = 1000;
    private int id;
    private int idade;
    private String tipo;
    private String nome;
    private String senha;

    public void incrementaIdentificador(){
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")){
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collection = database.getCollection("identificador");

            Document filtro = new Document("identificador", 1);
            Document documento = collection.find(filtro).first();

            if (documento != null) {
                int valor = documento.getInteger("matricula");
                valor = valor + 1;
                Document atualizacao = new Document("$set", new Document("matricula", valor));
                collection.updateOne(filtro, atualizacao);
            } else{
                System.out.println("Documento não existe!");
            }
        }
    }

    public int buscaIdentificador() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collection = database.getCollection("identificador");

            Document filtro = new Document("identificador", 1);
            Document documento = collection.find(filtro).first();

            if (documento != null) {
                incrementaIdentificador();
                return documento.getInteger("matricula");
            } else {
                return -1;
            }

        }
    }

    public Usuario(){
        this.id = buscaIdentificador();
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
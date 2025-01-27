package mongodb;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import exceptions.OpcaoInvalida;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class EmprestaLivro {
    public static void emprestaLivro(int ISBN) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("");
            MongoCollection<Document> collection = database.getCollection("");
            List<String> habilidades = Arrays.asList("Java", "MongoDB");

            Document doc = new Document("nome", "Luiz Henrique")
                    .append("idade", 25)
                    .append("habilidades", habilidades);
            collection.insertOne(doc);

            System.out.println("Documento inserido!");
        }
    }

    public static boolean validaLivro(int isbn){
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("catalogo");
            MongoCollection<Document> collection = database.getCollection("livros");
            Document filtro = new Document("ISBN", isbn);

            return collection.find(filtro).iterator().hasNext();
        }
    }

    public static boolean verificarLivrosDisponiveis(){
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("catalogo");
            MongoCollection<Document> collection = database.getCollection("livros");
            MongoIterable<Document> documentos = collection.find();
            Document filtro = new Document("Estado", "Disponível");

            return collection.find(filtro).iterator().hasNext();
        }
    }

    public static void mudarEstadoLivroParaEmprestado(int isbn, int idUser){
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("catalogo");
            MongoCollection<Document> collection = database.getCollection("livros");
            MongoIterable<Document> documentos = collection.find();

            try(MongoCursor<Document> cursor = documentos.iterator()){
                while(cursor.hasNext()){
                    Document documento = cursor.next();
                    if(documento.get("ISBN").equals(isbn)){
                        if(documento.get("Estado").equals("Emprestado")){
                            throw new OpcaoInvalida("Este livro já está emprestado!");
                        }
                        collection.updateOne(
                                Filters.eq("ISBN", documento.get("ISBN")),
                                Updates.set("Estado", "Emprestado")
                        );
                        System.out.println("O livro " + documento.get("Título") + " foi emprestado com sucesso!");
                        Document livro = new Document("Título", documento.get("Título"))
                                .append("Autor", documento.get("Autor"))
                                .append("Estado", documento.get("Estado"))
                                .append("ISBN", documento.get("ISBN"));

                        InsereDadosUsuario.insereLivroUsuario(idUser, livro);

                    }
                }
            } catch (OpcaoInvalida e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void mudarEstadoLivroParaDisponivel(int isbn){
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("catalogo");
            MongoCollection<Document> collection = database.getCollection("livros");


        }
    }
}
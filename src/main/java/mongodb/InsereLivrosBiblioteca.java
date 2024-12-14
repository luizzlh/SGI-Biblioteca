package mongodb;

import aplicattion.Livro;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class InsereLivrosBiblioteca {
    public void insereLivro(Livro livro) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");) {
            MongoDatabase database = mongoClient.getDatabase("catalogo");
            MongoCollection<Document> collection = database.getCollection("livros");

            Document doc = new Document("Título", livro.getTitulo())
                    .append("Autor", livro.getAutor())
                    .append("Estado", livro.getEstado())
                    .append("ISBN", livro.getIsbn());
            collection.insertOne(doc);

            System.out.println("Livro inserido!");
        }
    }
}
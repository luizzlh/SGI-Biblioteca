package mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import exceptions.ItemInexistente;
import org.bson.Document;

public class RemoveLivro {
    public static void removeLivro(int isbn) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("catalogo");
            MongoCollection<Document> collection = database.getCollection("livros");

            if (collection.find(new Document("ISBN", isbn)).iterator().hasNext()) {
                collection.deleteOne(new Document("ISBN", isbn));
                System.out.println("Livro removido com sucesso!");
            } else {
                throw new ItemInexistente("Este livro não existe!");
            }

        } catch (ItemInexistente e) {
            System.out.println(e.getMessage());
        }
    }
}
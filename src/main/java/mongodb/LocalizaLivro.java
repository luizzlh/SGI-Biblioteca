package mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class LocalizaLivro {
    public static boolean localizaLivro(String nome) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("catalogo");
            MongoCollection<Document> collection = database.getCollection("livros");

            Document query = new Document("Título", nome);

            if (collection.find(query).iterator().hasNext()) {
                return true;
            }
            return false;
        }
    }
}
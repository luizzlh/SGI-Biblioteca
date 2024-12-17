package mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class InsertExample {
    public static void main() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("");
            MongoCollection<Document> collection = database.getCollection("");

            List<String> habilidades = Arrays.asList("Java", "MongoDB");

            // Criar um documento e inserir
            Document doc = new Document("nome", "Luiz Henrique")
                    .append("idade", 25)
                    .append("habilidades", habilidades);
            collection.insertOne(doc);

            System.out.println("Documento inserido!");
        }
    }
}

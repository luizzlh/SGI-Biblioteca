package mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.util.Arrays;
import java.util.List;

public class VerificaConteudoBD {
    public static boolean verificaConteudoDB() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("admin");

            MongoCollection<Document> collectionClientes = database.getCollection("clientes");
            MongoCollection<Document> collectionAdmins = database.getCollection("admins");

            return collectionAdmins.countDocuments() > 0 || collectionClientes.countDocuments() > 0;

        } catch (Exception e) {
            System.err.println("Erro ao verificar conteúdo do banco de dados: " + e.getMessage());
            return false;
        }
    }
}

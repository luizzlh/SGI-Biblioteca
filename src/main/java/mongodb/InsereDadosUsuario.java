package mongodb;
import aplicattion.Pessoa;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class InsereDadosUsuario {
    public static void insereDadosUsuario(Pessoa user) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collection = database.getCollection("clientes");

            Document doc = new Document("Nome", user.getNome())
                    .append("Idade", user.getIdade())
                    .append("Tipo", "Cliente")
                    .append("ID", user.getId())
                    .append("Senha", user.getSenha());
            collection.insertOne(doc);
        }
    }
}
package mongodb;
import aplicattion.Pessoa;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;

public class InsereDadosUsuario {
    public static void insereDadosUsuario(Pessoa user) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collection = database.getCollection("clientes");

            ArrayList<Object> livros = new ArrayList<>();
            Document doc = new Document("Nome", user.getNome())
                    .append("Idade", user.getIdade())
                    .append("Tipo", user.getTipo())
                    .append("ID", user.getId())
                    .append("Senha", user.getSenha())
                    .append("Livros", livros);
            collection.insertOne(doc);
        }
    }

    public static void insereLivroUsuario(Document livro){
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collection = database.getCollection("clientes");



        }
    }
}
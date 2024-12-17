package mongodb;
import com.mongodb.client.*;
import org.bson.Document;

public class Login {
    public static boolean login(String usuario, String senha) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collectionAdmin = database.getCollection("admins");
            MongoCollection<Document> collectionClientes = database.getCollection("clientes");

            Document query = new Document("Nome", usuario)
                    .append("Senha", senha);

            return collectionAdmin.find(query).iterator().hasNext() ||
                    collectionClientes.find(query).iterator().hasNext();
        } catch (Exception e) {
            System.err.println("Erro ao realizar login: " + e.getMessage());
            return false;
        }
    }

    public static int retornaIdLogin(String usuario, String senha) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collection = database.getCollection("clientes");
            MongoIterable<Document> documentos = collection.find();

            Document query = new Document("Nome", usuario)
                    .append("Senha", senha);

            for (Document documento : documentos) {
                String nome = (String) documento.get("Nome");
                if (nome.equals(usuario)) {
                    return documento.get("ID");
                }
            }
            return -1;
        }
    }
}
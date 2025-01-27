package mongodb;

import aplicattion.Biblioteca;
import aplicattion.Livro;
import aplicattion.Pessoa;
import com.mongodb.client.*;
import exceptions.ItemInexistente;
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

    public static void insereLivroUsuario(int id, Document livro) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collection = database.getCollection("clientes");
            MongoIterable<Document> documentos = collection.find(new Document("ID", id));
            ArrayList<Object> livrosUsuario = new ArrayList<>();

            try (MongoCursor<Document> cursor = documentos.iterator()) {
                while (cursor.hasNext()) {
                    ArrayList livros = (ArrayList) cursor.next().get("Livros");
                    System.out.println(livros);



        }
    }
}
package mongodb;

import aplicattion.PasswordHasher;
import com.mongodb.client.*;
import org.bson.Document;

public class Login {

    public static boolean login(String usuario, String senha) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collectionClientes = database.getCollection("clientes");

            Document userCliente = collectionClientes.find(new Document("Nome", usuario)).first();

            if (userCliente != null && validarSenha(senha, userCliente.getString("Senha"))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Erro ao realizar login: " + e.getMessage());
            return false;
        }
    }

    public static int retornaIdLogin(String usuario, String senha) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collectionClientes = database.getCollection("clientes");

            Document userCliente = collectionClientes.find(new Document("Nome", usuario)).first();

            if (userCliente != null && validarSenha(senha, userCliente.getString("Senha"))) {
                return userCliente.getInteger("ID");
            }
            return -1;
        } catch (Exception e) {
            System.err.println("Erro ao recuperar ID do login: " + e.getMessage());
            return -1;
        }
    }

    private static boolean validarSenha(String senha, String senhaHashArmazenada) {
        String senhaHash = PasswordHasher.hashPassword(senha);
        return senhaHash.equals(senhaHashArmazenada);
    }

    public static int verificaTipoUsuario(String usuario, String senha){
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collectionClientes = database.getCollection("clientes");

            Document userCliente = collectionClientes.find(new Document("Nome", usuario)).first();

            if (userCliente != null && validarSenha(senha, userCliente.getString("Senha"))) {
                if(userCliente.getString("Tipo").equalsIgnoreCase("Adm")){
                    return 1;
                }
            }
            return 0;
        } catch (Exception e) {
            System.err.println("Erro ao recuperar ID do login: " + e.getMessage());
            return -1;
        }
    }
}
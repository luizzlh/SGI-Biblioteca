package mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class IniciaConexao {
    public void iniciaConexao() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/")) {
            System.out.println("Conexão bem sucedida!");
        }
    }
}

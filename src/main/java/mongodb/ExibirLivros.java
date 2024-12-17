package mongodb;

import com.mongodb.client.*;
import org.bson.Document;

public class ExibirLivros {
    public static void exibirLivros() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("catalogo");
            MongoCollection<Document> collection = database.getCollection("livros");
            MongoIterable<Document> documentos = collection.find();

            try (MongoCursor<Document> cursor = documentos.iterator()){
                int contador = 1;
                while(cursor.hasNext()){
                    Document documento = cursor.next();
                    System.out.println("-------------------------------------------------");
                    System.out.println("Livro " + contador);
                    System.out.println("Título: " + documento.get("Título"));
                    System.out.println("Autor: " + documento.get("Autor"));
                    System.out.println("Estado: " + documento.get("Estado"));
                    System.out.println("ISBN: " + documento.get("ISBN"));
                    contador++;
                }
            } catch (Exception e){
                System.out.println("Erro ao iterar: " + e.getMessage());
            }
        }
    }
}

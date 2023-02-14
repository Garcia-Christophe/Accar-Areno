package dao.mongodb;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pojo.mongodb.Ressource;


public class TestMongoDb {
    public static void main(String[] args) {

        // connexion � la base Mongo et � la base "sports"
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        ConnectionString connectionString = new ConnectionString("mongodb://obiwan.univ-brest.fr:27017");
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("zsorinal0").withCodecRegistry(pojoCodecRegistry);
        System.out.println("Connexion etablie\n");

        MongoCollection<Ressource> ressources = database.getCollection("ressources", Ressource.class);
        
        for(Ressource r : ressources.find()) {
        	System.out.println(r.getAuteur());
        }

    }
}

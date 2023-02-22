package dao.mongodb;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pojo.mongodb.Ressource;
import pojo.mongodb.Sujets;


public class TestMongoDb {
    public static void main(String[] args) {
    	
    	DaoRessources dao = new DaoRessources();

        MongoCollection<Ressource> ressources = dao.database.getCollection("ressources", Ressource.class);
        
        for(Ressource r : ressources.find()) {
        	System.out.println(r.getAuteur());
        	//System.out.println(r.getSujets().getGroupe().get(0));
        }
        
        ArrayList<String> concert = new ArrayList<String>();
        concert.add("Concert1");
        concert.add("concert2");
        
        Sujets s = new Sujets();
        s.setConcert(concert);
        
        dao.create("url", "image", "02/02/2001", "Alexia Sorin", s);
        
        System.out.println(dao.find("url").getAuteur());
        
        dao.update("url", "article", "25/25/25", "christophe", s);
        
        System.out.println(dao.find("url").getAuteur());
        
        //dao.delete("url");
        
    }
}

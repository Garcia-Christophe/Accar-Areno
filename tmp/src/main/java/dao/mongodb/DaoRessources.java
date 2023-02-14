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
import com.mongodb.client.model.Filters;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Updates;



import pojo.mongodb.Ressource;
import pojo.mongodb.Sujets;

public class DaoRessources {
	 CodecProvider pojoCodecProvider;
	 CodecRegistry pojoCodecRegistry;
	 ConnectionString connectionString;
	 MongoClient mongoClient;
	 MongoDatabase database;
	 MongoCollection<Ressource> ressources;
	 
	public DaoRessources() {
		 pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
	     pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

	     connectionString = new ConnectionString("mongodb://obiwan.univ-brest.fr:27017");
	     mongoClient = MongoClients.create(connectionString);
	     database = mongoClient.getDatabase("zsorinal0").withCodecRegistry(pojoCodecRegistry);

	}
	
	public Ressource find(String url) {
		ressources = database.getCollection("ressources", Ressource.class);
    	FindIterable<Ressource> list = ressources.find(Filters.eq("url",url));
    	for(Ressource r : list) {
    		return r;
    	}
    	return null;
	}
	
    public void create(String url, String type, String date, String auteur, Sujets sujets){
    	ressources = database.getCollection("ressources", Ressource.class);
    	FindIterable<Ressource> list = ressources.find(Filters.eq("url",url));
    	if(list.first()==null) {
    		Ressource r = new Ressource();
	    	r.setUrl(url);
	    	r.setType(type);
	    	r.setAuteur(auteur);
	    	r.setDate(date);
	    	r.setSujets(sujets);
	    	ressources.insertOne(r);
    	}
	    	
	    	
    }

    public void update(String url,String type, String date, String auteur, Sujets sujets){
    	ressources = database.getCollection("ressources", Ressource.class);
    	FindIterable<Ressource> list = ressources.find(Filters.eq("url",url));
    	if(list.first()==null) {
    		ressources.updateOne(Filters.eq("url",url), Updates.set("auteur", auteur));
    	}
    }

    public void delete(String url){
	     ressources = database.getCollection("ressources", Ressource.class);
         ressources.deleteOne(Filters.eq("url",url));
    }
	 
	 
}

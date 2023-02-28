package dao.mongodb;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import pojo.mongodb.Ressource;

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

		connectionString = new ConnectionString("mongodb://localhost:27017");
		mongoClient = MongoClients.create(connectionString);
		database = mongoClient.getDatabase("local").withCodecRegistry(pojoCodecRegistry);
	}

	public Ressource findByUrl(String url) {
		ressources = database.getCollection("accarareno", Ressource.class);
		FindIterable<Ressource> list = ressources.find(Filters.eq("url", url));
		return list.first();
	}

	public List<Ressource> findByName(String nom) {
		ressources = database.getCollection("accarareno", Ressource.class);
		FindIterable<Ressource> list = ressources.find(Filters.where(
				"for(var field in this.sujets) { if(this.sujets[field].includes(\"" + nom + "\")) { return true; }}"));
		List<Ressource> results = new ArrayList<>();
		for (Ressource r : list) {
			results.add(r);
		}
		return results;
	}

	public void create(Ressource ressource) {
		ressources = database.getCollection("accarareno", Ressource.class);
		if (this.findByUrl(ressource.getUrl()) == null) {
			ressources.insertOne(ressource);
		}
	}

	public void update(Ressource ressource, String previousUrl) {
		ressources = database.getCollection("accarareno", Ressource.class);
		FindIterable<Ressource> list = ressources.find(Filters.eq("url", previousUrl));
		if (list.first() != null) {
			Bson filtre = Filters.eq("url", previousUrl);
			ressources.updateOne(filtre, Updates.set("auteur", ressource.getAuteur()));
			ressources.updateOne(filtre, Updates.set("type", ressource.getType()));
			ressources.updateOne(filtre, Updates.set("date", ressource.getDate()));
			ressources.updateOne(filtre, Updates.set("sujets.groupe", ressource.getSujets().getGroupe()));
			ressources.updateOne(filtre, Updates.set("sujets.concert", ressource.getSujets().getConcert()));
			ressources.updateOne(filtre, Updates.set("sujets.soiree", ressource.getSujets().getSoiree()));
			ressources.updateOne(filtre, Updates.set("sujets.salle", ressource.getSujets().getSalle()));
			ressources.updateOne(filtre, Updates.set("url", ressource.getUrl()));
		}
	}

	public void delete(String url) {
		ressources = database.getCollection("accarareno", Ressource.class);
		ressources.deleteOne(Filters.eq("url", url));
	}

}

package dao.mongodb;

import java.util.List;

import com.mongodb.client.MongoCollection;

import pojo.mongodb.Ressource;

public class TestMongoDb {
	public static void main(String[] args) {

		DaoRessources dao = new DaoRessources();

		MongoCollection<Ressource> ressources = dao.database.getCollection("accarareno", Ressource.class);

		List<Ressource> liste = dao.findByName("Groupe 2");

		System.out.println(liste);

		for (Ressource r : liste) {
			System.out.println(r.getAuteur());
		}

	}
}

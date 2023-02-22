package dao.mysql;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import pojo.mysql.Artiste;
import pojo.mysql.Billet;
import pojo.mysql.Concert;
import pojo.mysql.Groupe;
import pojo.mysql.Salle;
import pojo.mysql.Soiree;
import pojo.mysql.Utilisateur;

public class TestJPA {

	public static void main(String argv[]) throws Exception {
		EntityManager em = Persistence.createEntityManagerFactory("Accarareno").createEntityManager();
		System.out.println("ok");

//		Groupe g = new Groupe();
//		g.setNom("One Direction");
		GroupeDAO daoGroupe = new GroupeDAO();
//		daoGroupe.create(g);
		
//		Artiste a = new Artiste();
//		a.setNom("tof");
//		a.setVilleOrigine("London");
//		a.setIdGroupe(g);
		ArtisteDAO daoArtiste = new ArtisteDAO();
//		daoArtiste.create(a);
		
//		Artiste b = new Artiste();
//		b.setNom("Nial");
//		b.setVilleOrigine("London");
//		b.setIdGroupe(g);
//		daoArtiste.create(b);
		
//		Utilisateur u = new Utilisateur();
//		u.setNom("christophe");
//		u.setMdp("125874");
//		UtilisateurDAO daoUtilisateur = new UtilisateurDAO();
//		daoUtilisateur.create(u);
		
		Groupe g = daoGroupe.find(6);
//		g.setNbArtistes(5);
//		daoGroupe.update(g);

//		Artiste a = daoArtiste.find(10);
//		daoArtiste.delete(a);
		
//		System.out.println(g.getArtisteSet().size());
//		daoArtiste.delete(a);
//		System.out.println(g.getArtisteSet().size());
		daoGroupe.delete(g);
	}
}

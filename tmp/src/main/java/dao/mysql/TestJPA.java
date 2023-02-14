package dao.mysql;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import pojo.mysql.Salle;

public class TestJPA {

  public static void main(String argv[]) throws Exception {
	 EntityManager em = Persistence.createEntityManagerFactory("Acareno").createEntityManager();
	 System.out.println("ok");
	  
    SalleDAO daoUtilisateur = new SalleDAO();
    Salle salle = new Salle(5);
    salle.setNom("nommmm");
    salle.setAdresse("a");
    salle.setAssociation("assoc");
    salle.setCapacite(1);
    salle.setNomGest("nomgest");
    salle.setPrenomGest("prenomgest");
    daoUtilisateur.create(salle);
  }
}

package com.dao;

import donnees.Discipline;
import sportsDAO.DAO_JPA_Discipline;
import sportsDAO.DAO_JPA_Sport;
import sportsDAO.DAO_JPA_Sportif;

public class TestJPA {

  public static void main(String argv[]) throws Exception {

    // charge le gestionnaire d'entités lié à l'unité de persistance "SportsPU"
    // EntityManagerFactory emf = Persistence.createEntityManagerFactory("SportsPU");
    // EntityManager em = emf.createEntityManager();
    // System.out.println("PU chargée");

    // récupère le sport d'identifiant 1, affiche son intitulé et ses disciplines
    // int cle = 1;
    // Sport sport = em.find(Sport.class, cle);
    // System.out.println("Le sport " + cle + " est " + sport.getIntitule());
    // for (Discipline disc : sport.getDisciplines())
    // System.out.println(" -> " + disc.getIntitule());

    // Tests JPA
    // find codeDiscipline = 1
    DAO_JPA_Sport daoJpaSport = new DAO_JPA_Sport();
    DAO_JPA_Sportif daoJpaSportif = new DAO_JPA_Sportif();
    DAO_JPA_Discipline daoJpaDiscipline = new DAO_JPA_Discipline();
    System.out.println(daoJpaDiscipline.find(1));
    // create Discipline : 10, 500 mètres, 1
    Discipline d = new Discipline();
    d.setIntitule("500 mètres");
    d.setSport(daoJpaSport.find(1));
    daoJpaDiscipline.create(d);
    // update d.intitule
    d.setIntitule("1000 mètres");
    daoJpaDiscipline.update(d);
    daoJpaDiscipline.delete(daoJpaDiscipline.find(12));
  }
}

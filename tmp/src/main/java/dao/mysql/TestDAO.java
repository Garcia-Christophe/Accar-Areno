package com.dao;

import donnees.Discipline;
import donnees.Sport;
import sportsDAO.AbstractDAOFactory;
import sportsDAO.DAO;
import sportsDAO.PersistenceKind;
import sportsDAO.SportsDAOFactory;

public class TestDAO {

  public static void main(String argv[]) {
    try {

      // création des DAO via les fabriques
      SportsDAOFactory factory = AbstractDAOFactory.getDAOFactory(PersistenceKind.JDBC);
      DAO<Sport> daoSport = factory.getDAOSport();
      DAO<Discipline> daoDisc = factory.getDAODiscipline();

      // Affichage sport
      Sport s = daoSport.find(1);
      System.out.println(s);
      // Affichage discipline
      for (Discipline d : s.getDisciplines()) {
        System.out.println(d);
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }
}


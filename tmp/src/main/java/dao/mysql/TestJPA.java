package dao.mysql;

import dao.mysql.UtilisateurDAO;
import pojo.mysql.Utilisateur;

public class TestJPA {

  public static void main(String argv[]) throws Exception {
    UtilisateurDAO daoUtilisateur = new UtilisateurDAO();
    System.out.println(daoUtilisateur.find(1));
  }
}

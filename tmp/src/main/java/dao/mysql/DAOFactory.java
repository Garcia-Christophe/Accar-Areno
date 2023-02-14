package dao.mysql;

import pojo.mysql.Utilisateur;
import pojo.mysql.Soiree;
import pojo.mysql.Salle;
import pojo.mysql.Artiste;
import pojo.mysql.Concert;
import pojo.mysql.Billet;
import pojo.mysql.Groupe;

public abstract class DAOFactory {

  public abstract DAO<Utilisateur> getDAOUtilisateur() throws DAOException;

  public abstract DAO<Soiree> getDAOSoiree() throws DAOException;

  public abstract DAO<Salle> getDAOSalle() throws DAOException;

  public abstract DAO<Artiste> getDAOArtiste() throws DAOException;

  public abstract DAO<Concert> getDAOConcert() throws DAOException;

  public abstract DAO<Billet> getDAOBillet() throws DAOException;

  public abstract DAO<Groupe> getDAOGroupe() throws DAOException;

}

package dao.mysql;

import pojo.mysql.Utilisateur;
import pojo.mysql.Soiree;
import pojo.mysql.Salle;

public abstract class DAOFactory {

  public abstract DAO<Utilisateur> getDAOUtilisateur() throws DAOException;

  public abstract DAO<Soiree> getDAOSoiree() throws DAOException;

  public abstract DAO<Salle> getDAOSalle() throws DAOException;

}

package com.dao;

import com.pojo.mysql.Utilisateur;
import com.pojo.mysql.Soiree;
import com.pojo.mysql.Salle;

public abstract class DAOFactory {

  public abstract DAO<Utilisateur> getDAOUtilisateur() throws DAOException;

  public abstract DAO<Soiree> getDAOSoiree() throws DAOException;

  public abstract DAO<Salle> getDAOSalle() throws DAOException;

}

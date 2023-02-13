package com.dao;


import com.POJO.*;

/**
 * Fabrique abstraite de DAO pour le sch�ma sports
 *
 */
public abstract class ConcertsDAOFactory {

    public abstract DAO<Artiste> getDAOArtiste() throws DAOException;

    /**
     * @return le DAO pour la classe/table Billet.
     * @throws DAOException en cas de problème
     */

    public abstract DAO<Concert> getDAOConcert() throws DAOException;
    /**
     * @return le DAO pour la classe/table Groupe.
     * @throws DAOException en cas de problème
     */
    public abstract DAO<Billet> getDAOBillet() throws DAOException;
    /**
     * @return le DAO pour la classe/table Groupe.
     * @throws DAOException en cas de problème
     */
    public abstract DAO<Groupe> getDAOGroupe() throws DAOException;
    /**
     * @return le DAO pour la classe/table Salle.
     * @throws DAOException en cas de problème
     */
    public abstract DAO<Salle> getDAOSalle() throws DAOException;

    /**
     * @return le DAO pour la classe/table Soiree.
     * @throws DAOException en cas de problème
     */
    public abstract DAO<Soiree> getDAOSoiree() throws DAOException;

    /**
     * @return le DAO pour la classe/table Utilisateur.
     * @throws DAOException en cas de probl�me
     */
    public abstract DAO<Utilisateur> getDAOUtilisateur() throws DAOException;
}

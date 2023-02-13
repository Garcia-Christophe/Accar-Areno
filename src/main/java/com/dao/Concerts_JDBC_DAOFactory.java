package com.dao;

import com.POJO.*;

/**
 * Fabrique concr�te de DAO pour le sch�ma relationnel sports avec une impl�mentation en JDBC.
 *
 */
public class Concerts_JDBC_DAOFactory extends ConcertsDAOFactory {

    /**
     * Le DAO concret en JDBC pour la table Artiste
     */
    private ArtisteDAO daoArtiste = null;

    /**
     * Le DAO concret en JDBC pour la table Billet
     */
    private BilletDAO daoBillet = null;
    
    /**
     * Le DAO concret en JDBC pour la table Concert
     */
    private ConcertDAO daoConcert = null;

    /**
     * Le DAO concret en JDBC pour la table Salle
     */
    private SalleDAO daoSalle = null;

    /**
     * Le DAO concret en JDBC pour la table Soiree
     */
    private SoireeDAO daoSoiree = null;

    /**
     * Le DAO concret en JDBC pour la table Groupe
     */
    private GroupeDAO daoGroupe = null;

    /**
     * Le DAO concret en JDBC pour la table Utilisateur
     */
    private UtilisateurDAO daoUtilisateur = null;


    /**
     * @return
     * @throws DAOException
     */
    @Override
    public DAO<Artiste> getDAOArtiste() throws DAOException {
        if (daoArtiste == null) daoArtiste = new ArtisteDAO();
        return daoArtiste;
    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public DAO<Concert> getDAOConcert() throws DAOException {
        if (daoConcert == null) daoConcert = new ConcertDAO();
        return daoConcert;
    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public DAO<Billet> getDAOBillet() throws DAOException {
        if (daoBillet == null) daoBillet = new BilletDAO();
        return daoBillet;
    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public DAO<Groupe> getDAOGroupe() throws DAOException {
        if (daoGroupe == null) daoGroupe = new GroupeDAO();
        return daoGroupe;
    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public DAO<Salle> getDAOSalle() throws DAOException {
        if (daoSalle == null) daoSalle = new SalleDAO();
        return daoSalle;
    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public DAO<Soiree> getDAOSoiree() throws DAOException {
        if (daoSoiree == null) daoSoiree = new SoireeDAO();
        return daoSoiree;
    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public DAO<Utilisateur> getDAOUtilisateur() throws DAOException {
        if (daoUtilisateur == null) daoUtilisateur = new UtilisateurDAO();
        return daoUtilisateur;
    }
}

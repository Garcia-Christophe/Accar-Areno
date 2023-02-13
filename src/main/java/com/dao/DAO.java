package com.dao;

import java.sql.SQLException;


public abstract class DAO<T> {

    /**
     * Retourne à partir du support de persistance un objet en fonction de son identifiant.
     * @param id identifiant de l'objet
     * @return l'instance de l'objet
     * @throws SQLException en cas de problème
     */
    public abstract T find(int id) throws DAOException;

    /**
     * Rend persistant un objet qui n'avait pas encore de réprésentation sur
     * le support de persistance.
     * @param t l'objet à rendre persistant
     * @throws SQLException en cas de problème
     */
    public abstract void create ( T t) throws DAOException;

    /**
     * Met à jour le contenu correspondant à l'objet sur le support persistant (l'objet
     * avait déjà une représentation sur le support persistant).
     * @param t l'objet modifi� dont le contenu est � mettre � jour
     * @throws SQLException en cas de problème
     */
    public abstract void update ( T t) throws DAOException;

    /**
     * Efface du support persistant le contenu équivalent à l'objet.
     * @param t l'objet à supprimer
     * @throws SQLException en cas de problème
     */
    public abstract void delete ( T t) throws DAOException;

}

package com.dao;

import com.POJO.Groupe;

import java.sql.SQLException;

public class GroupeDAO extends DAO<Groupe> {
    /**
     * @param id identifiant de l'objet
     * @return
     * @throws SQLException
     */
    @Override
    public Groupe find(int id) throws DAOException {
        return null;
    }

    /**
     * @param groupe l'objet à rendre persistant
     * @throws SQLException
     */
    @Override
    public void create(Groupe groupe) throws DAOException {

    }

    /**
     * @param groupe l'objet modifi� dont le contenu est � mettre � jour
     * @throws SQLException
     */
    @Override
    public void update(Groupe groupe) throws DAOException {

    }

    /**
     * @param groupe l'objet à supprimer
     * @throws SQLException
     */
    @Override
    public void delete(Groupe groupe) throws DAOException {

    }
}

package com.dao;

import com.POJO.Billet;

import java.sql.SQLException;

public class BilletDAO extends DAO<Billet>{
    /**
     * @param id identifiant de l'objet
     * @return
     * @throws SQLException
     */
    @Override
    public Billet find(int id) throws DAOException {
        return null;
    }

    /**
     * @param billet l'objet à rendre persistant
     * @throws SQLException
     */
    @Override
    public void create(Billet billet) throws DAOException {

    }

    /**
     * @param billet l'objet modifi� dont le contenu est � mettre � jour
     * @throws SQLException
     */
    @Override
    public void update(Billet billet) throws DAOException {

    }

    /**
     * @param billet l'objet à supprimer
     * @throws SQLException
     */
    @Override
    public void delete(Billet billet) throws DAOException {

    }
}

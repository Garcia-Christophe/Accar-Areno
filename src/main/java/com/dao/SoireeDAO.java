package com.dao;

import com.POJO.Soiree;

import java.sql.SQLException;

public class SoireeDAO extends DAO<Soiree>{
    /**
     * @param id identifiant de l'objet
     * @return
     * @throws SQLException
     */
    @Override
    public Soiree find(int id) throws DAOException {
        return null;
    }

    /**
     * @param soiree l'objet à rendre persistant
     * @throws SQLException
     */
    @Override
    public void create(Soiree soiree) throws DAOException {

    }

    /**
     * @param soiree l'objet modifi� dont le contenu est � mettre � jour
     * @throws SQLException
     */
    @Override
    public void update(Soiree soiree) throws DAOException {

    }

    /**
     * @param soiree l'objet à supprimer
     * @throws SQLException
     */
    @Override
    public void delete(Soiree soiree) throws DAOException {

    }
}

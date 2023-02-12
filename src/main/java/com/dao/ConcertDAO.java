package com.dao;

import com.POJO.Concert;

import java.sql.SQLException;

public class ConcertDAO extends DAO<Concert>{
    /**
     * @param id identifiant de l'objet
     * @return
     * @throws SQLException
     */
    @Override
    public Concert find(int id) throws SQLException {
        return null;
    }

    /**
     * @param concert l'objet à rendre persistant
     * @throws SQLException
     */
    @Override
    public void create(Concert concert) throws SQLException {

    }

    /**
     * @param concert l'objet modifi� dont le contenu est � mettre � jour
     * @throws SQLException
     */
    @Override
    public void update(Concert concert) throws SQLException {

    }

    /**
     * @param concert l'objet à supprimer
     * @throws SQLException
     */
    @Override
    public void delete(Concert concert) throws SQLException {

    }
}

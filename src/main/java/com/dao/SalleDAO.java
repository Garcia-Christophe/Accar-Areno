package com.dao;

import com.POJO.Salle;

import java.sql.SQLException;

public class SalleDAO extends DAO<Salle>{
    /**
     * @param id identifiant de l'objet
     * @return
     * @throws SQLException
     */
    @Override
    public Salle find(int id) throws SQLException {
        return null;
    }

    /**
     * @param salle l'objet à rendre persistant
     * @throws SQLException
     */
    @Override
    public void create(Salle salle) throws SQLException {

    }

    /**
     * @param salle l'objet modifi� dont le contenu est � mettre � jour
     * @throws SQLException
     */
    @Override
    public void update(Salle salle) throws SQLException {

    }

    /**
     * @param salle l'objet à supprimer
     * @throws SQLException
     */
    @Override
    public void delete(Salle salle) throws SQLException {

    }
}

package com.dao;

import com.POJO.Artiste;
import com.dao.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArtisteDAO extends DAO<Artiste>{
    /**
     * @param id identifiant de l'objet
     * @return
     * @throws SQLException
     */
    @Override
    public Artiste find(int id) throws SQLException {
        return null;
    }

    /**
     * @param artiste l'objet à rendre persistant
     * @throws SQLException
     */
    @Override
    public void create(Artiste artiste) throws SQLException {

    }

    /**
     * @param artiste l'objet modifi� dont le contenu est � mettre � jour
     * @throws SQLException
     */
    @Override
    public void update(Artiste artiste) throws SQLException {

    }

    /**
     * @param artiste l'objet à supprimer
     * @throws SQLException
     */
    @Override
    public void delete(Artiste artiste) throws SQLException {

    }
}

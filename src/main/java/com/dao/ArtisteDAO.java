package com.dao;

import com.POJO.Artiste;
import com.dao.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class ArtisteDAO extends DAO<Artiste>{
    /**
     * @param id identifiant de l'objet
     * @return
     * @throws SQLException
     */

    private Connection connection = null;

    @Override
    public Artiste find(int id) throws DAOException {
        try {
            Statement req = connection.createStatement();
            ResultSet res = req.executeQuery("select * from artiste where idArtiste=" + id);
            if (res.next()) {
                Artiste artiste = new Artiste();
                artiste.setIdArtiste(res.getInt(1));
                artiste.setNom(res.getString(2));
                artiste.setVilleOrigine(res.getString(3));
                artiste.setDateNaissance(res.getDate(4));

                return artiste;
            } else {
                throw new DAOException("Le discipline d'identifiant " + id + " n'existe pas");
            }
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }

    /**
     * @param artiste l'objet à rendre persistant
     * @throws SQLException
     */
    @Override
    public void create(Artiste artiste) throws DAOException {

    }

    /**
     * @param artiste l'objet modifi� dont le contenu est � mettre � jour
     * @throws SQLException
     */
    @Override
    public void update(Artiste artiste) throws DAOException {

    }

    /**
     * @param artiste l'objet à supprimer
     * @throws SQLException
     */
    @Override
    public void delete(Artiste artiste) throws DAOException {

    }
}

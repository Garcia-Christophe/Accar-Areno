package com.dao;

import com.pojo.mysql.Artiste;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;


public class ArtisteDAO extends DAO<Artiste>{
    /**
     * @param id identifiant de l'objet
     * @return
     * @throws SQLException
     */

    private Connection connection = DriverManager.getConnection("jdbc:mariadb://obiwan.univ-brest.fr:3306/zfm1-zgarciach_1", "zgarciach", "y4bhcyub");
//
    //jdbc:mysql://localhost:3306/gestionconcerts
    @Override
    public Artiste find(int id) throws DAOException {
        try {
            Statement req = connection.createStatement();
            ResultSet res = req.executeQuery("select * from Artiste where idArtiste=" + id);
            if (res.next()) {
                Artiste artiste = new Artiste();
                artiste.setIdArtiste(res.getInt(1));
                artiste.setNom(res.getString(2));
                artiste.setVilleOrigine(res.getString(3));
                artiste.setDateNaissance(res.getDate(4));

                return artiste;
            } else {
                throw new DAOException("L'Artiste d'identifiant " + id + " n'existe pas");
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
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Artiste (nom, villeOrigine, dateNaissance, idGroupe) VALUES (?, ?, ?, ?)");
            statement.setString(1, artiste.getNom());
            statement.setString(2, artiste.getVilleOrigine());
            statement.setDate(3, artiste.getDateNaissance());
            statement.setInt(4,artiste.getIdGroupe());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur technique lors de la création de l'artiste : " + e.getMessage());
        }
    }

    /**
     * @param artiste l'objet modifi� dont le contenu est � mettre � jour
     * @throws SQLException
     */
    @Override
    public void update(Artiste artiste) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Artiste SET nom=?, villeOrigine=?, dateNaissance=? WHERE idArtiste=?");
            statement.setString(1, artiste.getNom());
            statement.setString(2, artiste.getVilleOrigine());
            statement.setDate(3, artiste.getDateNaissance());
            statement.setInt(4, artiste.getIdArtiste());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur technique lors de la mise à jour de l'artiste : " + e.getMessage());
        }
    }

    /**
     * @param artiste l'objet à supprimer
     * @throws SQLException
     */
    @Override
    public void delete(Artiste artiste) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Artiste WHERE idArtiste=?");
            statement.setInt(1, artiste.getIdArtiste());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur technique lors de la mise à jour de l'artiste : " + e.getMessage());
        }
    }

    public ArtisteDAO() throws SQLException {
    }
}

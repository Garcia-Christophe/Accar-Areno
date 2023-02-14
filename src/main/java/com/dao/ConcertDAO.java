package com.dao;

import com.pojo.mysql.Artiste;
import com.pojo.mysql.Concert;

import java.sql.*;

public class ConcertDAO extends DAO<Concert>{
    /**
     * @param id identifiant de l'objet
     * @return
     * @throws SQLException
     */

    private Connection connection = DriverManager.getConnection("jdbc:mysql://obiwan.univ-brest.fr:3306/zfm1-zgarciach_2", "zgarciach", "y4bhcyub");

    public ConcertDAO() throws SQLException {
    }

    @Override
    public Concert find(int id) throws DAOException {
        try {
            Statement req = connection.createStatement();
            ResultSet res = req.executeQuery("select * from concert where idConcert=" + id);
            if (res.next()) {
                Concert concert = new Concert();
                concert.setIdConcert(res.getInt(1));
                concert.setDate(res.getDate(2));
                concert.setHeure(res.getTime(3));
                concert.setDuree(res.getTime(4));
                concert.setIdGroupe(res.getInt(5));
                concert.setIdSoiree(res.getInt(6));
                return concert;
            } else {
                throw new DAOException("Le concert d'identifiant " + id + " n'existe pas");
            }
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }

    /**
     * @param concert l'objet à rendre persistant
     * @throws SQLException
     */
    @Override
    public void create(Concert concert) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Concert (idConcert, date, heure,duree,idGroupe,idSoiree) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, concert.getIdConcert());
            statement.setDate(2, concert.getDate());
            statement.setTime(3,concert.getHeure());
            statement.setTime(4,concert.getDuree());
            statement.setInt(5, concert.getIdGroupe());
            statement.setInt(6, concert.getIdSoiree());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur technique lors de la création de concert : " + e.getMessage());
        }
    }

    /**
     * @param concert l'objet modifi� dont le contenu est � mettre � jour
     * @throws SQLException
     */
    @Override
    public void update(Concert concert) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Concert SET date=?, heure=?, duree=?, idGroupe=?, idSoiree=? WHERE idConcert=?");
            statement.setDate(1, concert.getDate());
            statement.setTime(2,concert.getHeure());
            statement.setTime(3,concert.getDuree());
            statement.setInt(4,concert.getIdGroupe());
            statement.setInt(5,concert.getIdSoiree());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur technique lors de la mise à jour de concert : " + e.getMessage());
        }
    }

    /**
     * @param concert l'objet à supprimer
     * @throws SQLException
     */
    @Override
    public void delete(Concert concert) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Artiste WHERE idConcert=?");
            statement.setInt(1, concert.getIdConcert());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur technique lors de la suppression de concert : " + e.getMessage());
        }
    }
}

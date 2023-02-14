package com.dao;

import com.pojo.mysql.Artiste;
import com.pojo.mysql.Billet;

import java.sql.*;

public class BilletDAO extends DAO<Billet>{
    /**
     * @param id identifiant de l'objet
     * @return
     * @throws SQLException
     */

    private Connection connection = DriverManager.getConnection("jdbc:mysql://obiwan.univ-brest.fr:3306/zfm1-zgarciach_2", "zgarciach", "y4bhcyub");



    @Override
    public Billet find(int id) throws DAOException {
        try {
            Statement req = connection.createStatement();
            ResultSet res = req.executeQuery("select * from billet where idBillet=" + id);
            if (res.next()) {
                Billet billet = new Billet();
                billet.setIdBillet(res.getInt(1));
                billet.setPrix(res.getInt(2));
                billet.setIdSoiree(res.getInt(3));
                billet.setIdUtilisateur(res.getInt(4));
                return billet;
            } else {
                throw new DAOException("Le billet n " + id + " n'existe pas");
            }
        } catch (Exception e) {
            throw new DAOException("Problème technique (" + e.getMessage() + ")");
        }
    }

    /**
     * @param billet l'objet à rendre persistant
     * @throws SQLException
     */
    @Override
    public void create(Billet billet) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Billet (idBillet, prix, idSoiree,idUtilisateur) VALUES (?, ?, ? , ?)");
            statement.setInt(1, billet.getIdBillet());
            statement.setInt(2, billet.getPrix());
            statement.setInt(3, billet.getIdSoiree());
            statement.setInt(4, billet.getIdUtilisateur());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur technique lors de la création de l'artiste : " + e.getMessage());
        }
    }

    /**
     * @param billet l'objet modifi� dont le contenu est � mettre � jour
     * @throws SQLException
     */
    @Override
    public void update(Billet billet) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Billet SET prix=?, idSoiree=?, idUtilisateur=? WHERE idBillet=?");
            statement.setInt(1, billet.getPrix());
            statement.setInt(2, billet.getIdSoiree());
            statement.setInt(3, billet.getIdUtilisateur());
            statement.setInt(4, billet.getIdBillet());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur technique lors de la mise à jour de l'artiste : " + e.getMessage());
        }
    }

    /**
     * @param billet l'objet à supprimer
     * @throws SQLException
     */
    @Override
    public void delete(Billet billet) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Billet WHERE idBillet=?");
            statement.setInt(1, billet.getIdBillet());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur technique lors de la mise à jour de l'artiste : " + e.getMessage());
        }
    }
    public BilletDAO() throws SQLException {
    }
}

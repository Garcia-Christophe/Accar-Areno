package com.dao;

import com.POJO.Utilisateur;

import java.sql.SQLException;

public class UtilisateurDAO extends DAO<Utilisateur> {
    /**
     * @param id identifiant de l'objet
     * @return
     * @throws SQLException
     */
    @Override
    public Utilisateur find(int id) throws DAOException {
        return null;
    }

    /**
     * @param utilisateur l'objet à rendre persistant
     * @throws SQLException
     */
    @Override
    public void create(Utilisateur utilisateur) throws DAOException {

    }

    /**
     * @param utilisateur l'objet modifié dont le contenu est à mettre à jour
     * @throws SQLException
     */
    @Override
    public void update(Utilisateur utilisateur) throws DAOException {

    }

    /**
     * @param utilisateur l'objet à supprimer
     * @throws SQLException
     */
    @Override
    public void delete(Utilisateur utilisateur) throws DAOException {

    }
}

package com.dao;

/**
 * Fabrique renvoyant une fabrique de DAO en fonction du support de persistance choisi.
 * @author Christophe
 */
public class AbstractDAOFactory {

	/**
	 * Renvoie la fabrique concrète de DAO en fonction du support de persistance.
	 * @param type le support de persistance à utiliser
	 * @return la fabrique de DAO pour ce type de persistance
	 */
    public static SportsDAOFactory getDAOFactory(PersistenceKind type) {
        if (type.equals(PersistenceKind.JDBC)) {
            return new Sports_JDBC_DAOFactory();
        } else {
            return null;
        }
    }
}

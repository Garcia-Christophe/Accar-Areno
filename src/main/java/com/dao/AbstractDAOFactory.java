package com.dao;

/**
 * Fabrique renvoyant une fabrique de DAO en fonction du support de persistance choisi.
 *
 */
public class AbstractDAOFactory {

	/**
	 * Renvoie la fabrique concr�te de DAO en fonction du support de persistance.
	 * @param type le support de persistance � utiliser
	 * @return la fabrique de DAO pour ce type de persistance
	 */
    public static ConcertsDAOFactory getDAOFactory(PersistenceKind type) {
        if (type.equals(PersistenceKind.JDBC)) {
            return new Concerts_JDBC_DAOFactory();
        } else {
            return null;
        }
    }
}

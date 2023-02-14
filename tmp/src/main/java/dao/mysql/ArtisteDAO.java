package dao.mysql;

import pojo.mysql.Artiste;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ArtisteDAO extends DAO<Artiste> {
   
	private EntityManager em = null;

	public ArtisteDAO() throws DAOException {
	  super();
	}
	
	public EntityManager getEntityManager() {
	  if (em == null) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Acareno");
	    em = emf.createEntityManager();
	  }
	  return em;
	}
	
	public void closeEntityManager() {
	  if (em != null) {
	    em.close();
	    em = null;
	  }
	}
	  
    @Override
    public Artiste find(int id) throws DAOException {
    	Query query = this.getEntityManager()
	        .createQuery("select u from Artiste u where u.idArtiste = " + id);
	    return (Artiste) query.getSingleResult();
    }

    @Override
    public void create(Artiste utilisateur) throws DAOException {
    	EntityTransaction trans = null;
        try {
          trans = em.getTransaction();
          trans.begin();
          em.persist(utilisateur);
          trans.commit();
        } catch (Exception e) {
          if (trans != null)
            trans.rollback();
        }
    }

    @Override
    public void update(Artiste utilisateur) throws DAOException {
    	EntityTransaction trans = null;
        try {
          trans = em.getTransaction();
          trans.begin();
          em.refresh(utilisateur);
          trans.commit();
        } catch (Exception e) {
          if (trans != null)
            trans.rollback();
        }
    }

    @Override
    public void delete(Artiste utilisateur) throws DAOException {
    	EntityTransaction trans = null;
        try {
          trans = em.getTransaction();
          trans.begin();
          em.remove(utilisateur);
          trans.commit();
        } catch (Exception e) {
          if (trans != null)
            trans.rollback();
        }
    }
}

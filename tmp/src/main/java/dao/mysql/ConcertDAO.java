package dao.mysql;

import pojo.mysql.Concert;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ConcertDAO extends DAO<Concert> {
   
	private EntityManager em = null;

	public ConcertDAO() throws DAOException {
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
    public Concert find(int id) throws DAOException {
    	Query query = this.getEntityManager()
	        .createQuery("select c from Concert c where c.idConcert = " + id);
	    return (Concert) query.getSingleResult();
    }

    @Override
    public void create(Concert concert) throws DAOException {
    	EntityTransaction trans = null;
        try {
          trans = em.getTransaction();
          trans.begin();
          em.persist(concert);
          trans.commit();
        } catch (Exception e) {
          if (trans != null)
            trans.rollback();
        }
    }

    @Override
    public void update(Concert concert) throws DAOException {
    	EntityTransaction trans = null;
        try {
          trans = em.getTransaction();
          trans.begin();
          em.refresh(concert);
          trans.commit();
        } catch (Exception e) {
          if (trans != null)
            trans.rollback();
        }
    }

    @Override
    public void delete(Concert concert) throws DAOException {
    	EntityTransaction trans = null;
        try {
          trans = em.getTransaction();
          trans.begin();
          em.remove(concert);
          trans.commit();
        } catch (Exception e) {
          if (trans != null)
            trans.rollback();
        }
    }
}

package dao.mysql;

import pojo.mysql.Salle;
import pojo.mysql.Soiree;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SalleDAO extends DAO<Salle>{

	private EntityManager em = null;

	public SalleDAO() throws DAOException {
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
    public Salle find(int id) throws DAOException {
    	Query query = this.getEntityManager()
	        .createQuery("select s from Salle s where s.idSalle = " + id);
	    return (Salle) query.getSingleResult();
    }

    @Override
    public void create(Salle salle) throws DAOException {
    	EntityTransaction trans = null;
        try {
          trans = em.getTransaction();
          trans.begin();
          em.persist(salle);
          trans.commit();
        } catch (Exception e) {
          if (trans != null)
            trans.rollback();
        }
    }

    @Override
    public void update(Salle salle) throws DAOException {
    	EntityTransaction trans = null;
        try {
	        trans = em.getTransaction();
	        trans.begin();
	        em.refresh(salle);
	        trans.commit();
        } catch (Exception e) {
          if (trans != null)
            trans.rollback();
        }
    }

    @Override
    public void delete(Salle salle) throws DAOException {
    	EntityTransaction trans = null;
        try {
        	// Suppression des soirees de la salle
	        DAO<Soiree> daoSoiree = new SoireeDAO();
	        for (Soiree soiree : salle.getSoireeSet()) {
	        	daoSoiree.delete(soiree);
	        }
	            
	        // Suppression de la salle
	        trans = em.getTransaction();
	        trans.begin();
	        em.remove(salle);
	        trans.commit();
        } catch (Exception e) {
          if (trans != null)
            trans.rollback();
        }
    }
}

package dao.mysql;

import pojo.mysql.Soiree;

import java.sql.SQLException;

public class SoireeDAO extends DAO<Soiree>{

	private EntityManager em = null;

	public SoireeDAO() throws DAOException {
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
    public Soiree find(int id) throws DAOException {
    	Query query = this.getEntityManager()
	        .createQuery("select s from Soiree s where s.idSoiree = " + id);
	    return (Soiree) query.getSingleResult();
    }

    @Override
    public void create(Soiree soiree) throws DAOException {
    	EntityTransaction trans = null;
        try {
          trans = em.getTransaction();
          trans.begin();
          em.persist(data);
          trans.commit();
        } catch (Exception e) {
          if (trans != null)
            trans.rollback();
        }
    }

    @Override
    public void update(Soiree soiree) throws DAOException {
    	EntityTransaction trans = null;
        try {
          trans = em.getTransaction();
          trans.begin();
          em.refresh(data);
          trans.commit();
        } catch (Exception e) {
          if (trans != null)
            trans.rollback();
        }
    }

    @Override
    public void delete(Soiree soiree) throws DAOException {
    	EntityTransaction trans = null;
        try {
	        // Suppression des billets de la soiree
	        DAO<Billet> daoBillet = new DAO<Billet>();
	        for (Billet billet : soiree.getBilletSet()) {
	      	  daoBillet.delete(billet);
	        }
	        
	        // Suppression des concerts de la soiree
	        DAO<Concert> daoConcert = new DAO<Concert>();
	        for (Concert concert : soiree.getConcertSet()) {
	        	daoConcert.delete(billet);
	        }
	            
	        // Suppression de la soiree
	        trans = em.getTransaction();
	        trans.begin();
	        em.remove(data);
	        trans.commit();
        } catch (Exception e) {
	          if (trans != null)
	        	  trans.rollback();
        }
    }
}
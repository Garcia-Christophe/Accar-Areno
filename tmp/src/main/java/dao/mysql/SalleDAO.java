package dao.mysql;

import pojo.mysql.Salle;

import java.sql.SQLException;

public class SalleDAO extends DAO<Salle>{

	private EntityManager em = null;

	public UtilisateurDAO() throws DAOException {
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
          em.persist(data);
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
	        em.refresh(data);
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
	        DAO<Soiree> daoSoiree = new DAO<Soiree>();
	        for (Soiree soiree : salle.getSoireeSet()) {
	        	daoSoiree.delete(soiree);
	        }
	            
	        // Suppression de la salle
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

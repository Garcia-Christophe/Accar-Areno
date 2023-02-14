package dao.mysql;

import pojo.mysql.Utilisateur;

import java.sql.SQLException;

public class UtilisateurDAO extends DAO<Utilisateur> {
   
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
    public Utilisateur find(int id) throws DAOException {
    	Query query = this.getEntityManager()
	        .createQuery("select u from Utilisateur u where u.idUtilisateur = " + id);
	    return (Utilisateur) query.getSingleResult();
    }

    @Override
    public void create(Utilisateur utilisateur) throws DAOException {
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
    public void update(Utilisateur utilisateur) throws DAOException {
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
    public void delete(Utilisateur utilisateur) throws DAOException {
    	EntityTransaction trans = null;
        try {
          // Suppression des billets de l'utilisateur
          DAO<Billet> daoBillet = new DAO<Billet>();
          for (Billet billet : utilisateur.getBilletSet()) {
        	  daoBillet.delete(billet);
          }
          
          // Suppression de l'utilisateur
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

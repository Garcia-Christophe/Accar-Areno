package dao.mysql;

import pojo.mysql.Billet;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BilletDAO extends DAO<Billet> {

	private EntityManager em = null;

	public BilletDAO() throws DAOException {
		super();
	}

	public EntityManager getEntityManager() {
		if (em == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Accarareno");
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
	public Billet find(int id) throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select u from Billet u where u.idBillet = " + id);
		return (Billet) query.getSingleResult();
	}

	@Override
	public void create(Billet billet) throws DAOException {
		em = this.getEntityManager();
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			em.persist(billet);
			trans.commit();
		} catch (Exception e) {
			if (trans != null)
				trans.rollback();
		}
	}

	@Override
	public void update(Billet billet) throws DAOException {
		em = this.getEntityManager();
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			trans.commit();
		} catch (Exception e) {
			if (trans != null)
				trans.rollback();
		}
	}

	@Override
	public void delete(Billet billet) throws DAOException {
		em = this.getEntityManager();
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			em.remove(billet);
			trans.commit();
		} catch (Exception e) {
			if (trans != null)
				trans.rollback();
		}
	}
}

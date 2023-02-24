package dao.mysql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojo.mysql.Concert;

public class ConcertDAO extends DAO<Concert> {

	private EntityManager em = null;

	public ConcertDAO() throws DAOException {
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
	public Concert find(int id) throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select c from Concert c where c.idConcert = " + id);
		try {
			return (Concert) query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException();
		}
	}

	@Override
	public List<?> findAll() throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select u from Concert u");
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new DAOException();
		}
	}

	@Override
	public void create(Concert concert) throws DAOException {
		em = this.getEntityManager();
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
	public void delete(Concert concert) throws DAOException {
		em = this.getEntityManager();
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

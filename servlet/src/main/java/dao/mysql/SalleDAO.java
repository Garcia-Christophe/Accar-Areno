package dao.mysql;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojo.mysql.Salle;
import pojo.mysql.Soiree;

public class SalleDAO extends DAO<Salle> {

	private EntityManager em = null;

	public SalleDAO() throws DAOException {
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
	public Salle find(int id) throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select s from Salle s where s.idSalle = " + id);
		try {
			return (Salle) query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException();
		}
	}

	@Override
	public List<?> findAll() throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select u from Salle u");
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new DAOException();
		}
	}

	@Override
	public void create(Salle salle) throws DAOException {
		em = this.getEntityManager();
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
	public void delete(Salle salle) throws DAOException {
		em = this.getEntityManager();
		EntityTransaction trans = null;
		try {
			// Suppression des soirees de la salle
			DAO<Soiree> daoSoiree = new SoireeDAO();
			Iterator<Soiree> it1 = salle.getSoireeSet().iterator();
			while (it1.hasNext()) {
				Soiree tmp = daoSoiree.find(it1.next().getIdSoiree());
				daoSoiree.delete(tmp);
			}

			// Suppression de la salle
			trans = em.getTransaction();
			trans.begin();
			em.remove(salle);
			trans.commit();

		} catch (Exception e) {
			System.out.println("err : " + e.getMessage());
			if (trans != null)
				trans.rollback();
		}
	}
}

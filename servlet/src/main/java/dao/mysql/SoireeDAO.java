package dao.mysql;

import pojo.mysql.Billet;
import pojo.mysql.Concert;
import pojo.mysql.Soiree;

import java.sql.SQLException;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SoireeDAO extends DAO<Soiree> {

	private EntityManager em = null;

	public SoireeDAO() throws DAOException {
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
	public Soiree find(int id) throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select s from Soiree s where s.idSoiree = " + id);
		return (Soiree) query.getSingleResult();
	}

	@Override
	public void create(Soiree soiree) throws DAOException {
		em = this.getEntityManager();
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			em.persist(soiree);
			trans.commit();
		} catch (Exception e) {
			if (trans != null)
				trans.rollback();
		}
	}

	@Override
	public void update(Soiree soiree) throws DAOException {
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
	public void delete(Soiree soiree) throws DAOException {
		em = this.getEntityManager();
		EntityTransaction trans = null;
		try {
			// Suppression des billets de la soiree
			DAO<Billet> daoBillet = new BilletDAO();
			Iterator<Billet> it = soiree.getBilletSet().iterator();
			while (it.hasNext()) {
				Billet tmp = daoBillet.find(it.next().getIdBillet());
				daoBillet.delete(tmp);
			}

			// Suppression des concerts de la soiree
			DAO<Concert> daoConcert = new ConcertDAO();
			Iterator<Concert> it2 = soiree.getConcertSet().iterator();
			while (it2.hasNext()) {
				Concert tmp = daoConcert.find(it2.next().getIdConcert());
				daoConcert.delete(tmp);
			}

			// Suppression de la soiree
			trans = em.getTransaction();
			trans.begin();
			em.remove(soiree);
			trans.commit();
		} catch (Exception e) {
			if (trans != null)
				trans.rollback();
		}
	}
}

package dao.mysql;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojo.mysql.Billet;
import pojo.mysql.Utilisateur;

public class UtilisateurDAO extends DAO<Utilisateur> {

	private EntityManager em = null;

	public UtilisateurDAO() throws DAOException {
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
	public Utilisateur find(int id) throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select u from Utilisateur u where u.idUtilisateur = " + id);
		try {
			return (Utilisateur) query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException();
		}
	}

	@Override
	public List<?> findAll() throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select u from Utilisateur u");
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new DAOException();
		}
	}

	@Override
	public void create(Utilisateur utilisateur) throws DAOException {
		em = this.getEntityManager();
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
	public void update(Utilisateur utilisateur) throws DAOException {
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
	public void delete(Utilisateur utilisateur) throws DAOException {
		em = this.getEntityManager();
		EntityTransaction trans = null;
		try {
			// Suppression des billets de l'utilisateur
			DAO<Billet> daoBillet = new BilletDAO();
			Iterator<Billet> it = utilisateur.getBilletSet().iterator();
			while (it.hasNext()) {
				Billet tmp = daoBillet.find(it.next().getIdBillet());
				daoBillet.delete(tmp);
			}

			// Suppression de l'utilisateur
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

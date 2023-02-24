package dao.mysql;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojo.mysql.Artiste;
import pojo.mysql.Groupe;

public class GroupeDAO extends DAO<Groupe> {

	private EntityManager em = null;

	public GroupeDAO() throws DAOException {
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
	public Groupe find(int id) throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select u from Groupe u where u.idGroupe = " + id);
		try {
			return (Groupe) query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException();
		}
	}

	@Override
	public List<?> findAll() throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select u from Groupe u");
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new DAOException();
		}
	}

	@Override
	public void create(Groupe groupe) throws DAOException {
		em = this.getEntityManager();
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			em.persist(groupe);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (trans != null)
				trans.rollback();
		}
	}

	@Override
	public void update(Groupe groupe) throws DAOException {
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
	public void delete(Groupe groupe) throws DAOException {
		em = this.getEntityManager();
		EntityTransaction trans = null;
		try {
			// Suppression des soirees de la salle
			DAO<Artiste> daoArtiste = new ArtisteDAO();
			Iterator<Artiste> it1 = groupe.getArtisteSet().iterator();
			while (it1.hasNext()) {
				Artiste tmp = daoArtiste.find(it1.next().getIdArtiste());
				daoArtiste.delete(tmp);
			}

			trans = em.getTransaction();
			trans.begin();
			em.remove(groupe);
			trans.commit();
		} catch (Exception e) {
			if (trans != null)
				trans.rollback();
		}
	}
}

package dao.mysql;

import pojo.mysql.Artiste;
import pojo.mysql.Groupe;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ArtisteDAO extends DAO<Artiste> {

	private EntityManager em = null;

	public ArtisteDAO() throws DAOException {
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
	public Artiste find(int id) throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select u from Artiste u where u.idArtiste = " + id);
		return (Artiste) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findAll() throws DAOException {
		em = this.getEntityManager();
		Query query = em.createQuery("select u from Artiste u");
		return (List<Artiste>) query.getResultList();
	}
	
	@Override
	public void create(Artiste artiste) throws DAOException {
		em = this.getEntityManager();
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			em.persist(artiste);
			trans.commit();
			DAO<Groupe> daoGroupe = new GroupeDAO();
			Groupe groupe = daoGroupe.find(artiste.getIdGroupe().getIdGroupe());
			groupe.setNbArtistes(groupe.getNbArtistes() + 1);
			daoGroupe.update(groupe);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (trans != null)
				trans.rollback();
		}
	}

	@Override
	public void update(Artiste artiste) throws DAOException {
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
	public void delete(Artiste artiste) throws DAOException {
		em = this.getEntityManager();
		EntityTransaction trans = null;
		try {
			trans = em.getTransaction();
			trans.begin();
			em.remove(artiste);
			trans.commit();
			DAO<Groupe> daoGroupe = new GroupeDAO();
			Groupe groupe = daoGroupe.find(artiste.getIdGroupe().getIdGroupe());
			groupe.setNbArtistes(groupe.getNbArtistes() - 1);
			daoGroupe.update(groupe);
		} catch (Exception e) {
			if (trans != null)
				trans.rollback();
		}
	}
}

package billetapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import dao.mysql.BilletDAO;
import dao.mysql.DAO;
import dao.mysql.DAOException;
import dao.mysql.GroupeDAO;
import dao.mysql.SoireeDAO;
import dao.mysql.UtilisateurDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.mysql.Artiste;
import pojo.mysql.Billet;
import pojo.mysql.Groupe;
import pojo.mysql.Soiree;
import pojo.mysql.Utilisateur;

/**
 * Servlet implementation class BilletServlet
 */
@WebServlet("/billets")
public class BilletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BilletServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Prédéfinition de la réponse HTTP à retourner
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");
		response.setContentType("application/json; charset=UTF-8");

		// Création de l'outil permettant de renvoyer un string
		PrintWriter out = response.getWriter();

		// Récupération des paramètres
		String idUtilisateur = request.getParameter("idUtilisateur");

		// Préparation du json à renvoyer
		String json = "";

		try {
			DAO<Utilisateur> daoUtilisateur = new UtilisateurDAO();
			Utilisateur u = daoUtilisateur.find(Integer.valueOf(idUtilisateur));
			json = "{\"status\": \"OK\",\"message\":\"" + u.getBilletSet().size()
					+ " groupe(s) correspondant(s).\",\"data\":[";
			Iterator<Billet> it1 = u.getBilletSet().iterator();
			while (it1.hasNext()) {
				Billet b = it1.next();
				json += this.getJsonBillet(b);
				if (it1.hasNext()) {
					json += ',';
				}
			}
			json += "]}";

		} catch (DAOException e) {
			e.printStackTrace();
			json = "{\"status\": \"KO\",\"message\":\"Aucun billet correspondant.\",\"data\":{}";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			json = "{\"status\": \"KO\",\"message\":\"L'identifiant doit être un entier.\",\"data\":{}";
		}

		// Écriture du json
		out.write(json);

		// Fermeture de writer
		out.close();

	}

	private String getJsonBillet(Billet b) {
		String json = "";
		json += "{";
		json += "\"idBillet\":" + b.getIdBillet() + ",";
		json += "\"prix\":\"" + b.getPrix() + "\",";
		json += "\"nomSoiree\":\"" + b.getIdSoiree().getNom() + "\",";
		json += "\"nomUtilisateur\":\"" + b.getIdUtilisateur().getNom() + "\"";
		json += "}";
		return json;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Prédéfinition de la réponse HTTP à retourner
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");
		response.setContentType("application/json; charset=UTF-8");

		// Création de l'outil permettant de renvoyer un string
		PrintWriter out = response.getWriter();

		// Préparation du json à renvoyer
		String json = "{";

		// Récupération des paramètres
		String prix = request.getParameter("prix");
		String idSoiree = request.getParameter("idSoiree");
		String idUtilisateur = request.getParameter("idUtilisateur");

		// Création du DAO billet
		boolean erreur = false;
		DAO<Billet> daoBillet = null;
		DAO<Soiree> daoSoiree = null;
		DAO<Utilisateur> daoUtilisateur = null;
		Utilisateur u = null;
		Soiree s = null;
		try {
			daoBillet = new BilletDAO();
		} catch (DAOException e) {
			e.printStackTrace();
			erreur = true;
			json += "\"message\":\"Impossible de créer le DAO Billet.\",";
		}

		// Création du DAO Soiree
		if (!erreur && idSoiree != null) {
			try {
				daoSoiree = new SoireeDAO();
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de créer le DAO Soiree.\",";
			}
		} else {
			erreur = true;
			json += "\"message\":\"L'identifiant de la soir�e doit �tre renseign�.\",";
		}

		// R�cup�rationde la soir�e
		if (!erreur) {
			try {
				s = daoSoiree.find(Integer.valueOf(idSoiree));
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Aucune soir�e correspondante.\",";
			}
		}

		// Création du DAO Utilisateur
		if (!erreur && idUtilisateur != null) {
			try {
				daoUtilisateur = new UtilisateurDAO();
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de créer le DAO Utilisateur.\",";
			}
		} else {
			erreur = true;
			json += "\"message\":\"L'identifiant de l'utilisateur doit �tre renseign�.\",";
		}

		// R�cup�ration de l'utilisateur
		if (!erreur) {
			try {
				u = daoUtilisateur.find(Integer.valueOf(idUtilisateur));
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Aucune soir�e correspondante.\",";
			}
		}

		// Création du Billet
		if (!erreur && prix != null) {
			Billet b = new Billet();
			b.setPrix(Integer.valueOf(prix));
			b.setIdSoiree(s);
			b.setIdUtilisateur(u);

			// Ajout du billet dans la base de données
			try {
				daoBillet.create(b);
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de créer le billet.\",";
			}
		} else {
			erreur = true;
			json += "\"message\":\"Le prix doit �tre renseign�.\",";
		}

		// Définition de l'état du status
		if (!erreur) {
			json += "\"status\":\"OK\", \"message\":\"Billet ajouté.\", \"data\":{}}";
		} else {
			json += "\"status\":\"KO\", \"data\":{}}";
		}

		// Écriture du json
		out.write(json);

		// Fermeture de writer
		out.close();
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Prédéfinition de la réponse HTTP à retourner
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");
		response.setContentType("application/json; charset=UTF-8");

		// Création de l'outil permettant de renvoyer un string
		PrintWriter out = response.getWriter();

		// Préparation du json à renvoyer
		String json = "{";

		// Récupération des paramètres
		String id = request.getParameter("id");
		String prix = request.getParameter("prix");

		// Création du DAO Billet
		boolean erreur = false;
		DAO<Billet> daoBillet = null;
		try {
			daoBillet = new BilletDAO();
		} catch (DAOException e) {
			e.printStackTrace();
			erreur = true;
			json += "\"message\":\"Impossible de créer le DAO Billet.\",";
		}

		// Si un id a été fourni en paramètre, alors on tente de récupérer le
		// billet
		// correspondant
		Billet b = null;
		if (!erreur && id != null) {
			try {
				b = daoBillet.find(Integer.valueOf(id));
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Aucun billet correspondant.\",";
			}
		}

		// Si le billet est retrouvé dans la base de données, alors on procède à sa
		// éventuelle modification
		if (!erreur && b != null && prix != null) {
			b.setPrix(Integer.valueOf(prix));

			// Modification de l'artiste dans la base de données
			try {
				daoBillet.update(b);
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de modifier le billet.\",";
			}
		} else {
			erreur = true;
			json += "\"message\":\"Le prix doit �tre renseign�.\",";
		}

		// Définition de l'état du status
		if (!erreur) {
			json += "\"status\":\"OK\", \"message\":\"Bilet modifié.\", \"data\":{}}";
		} else {
			json += "\"status\":\"KO\", \"data\":{}}";
		}

		// Écriture du json
		out.write(json);

		// Fermeture de writer
		out.close();
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Prédéfinition de la réponse HTTP à retourner
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");
		response.setContentType("application/json; charset=UTF-8");

		// Création de l'outil permettant de renvoyer un string
		PrintWriter out = response.getWriter();

		// Préparation du json à renvoyer
		String json = "{";

		// Récupération des paramètres
		String id = request.getParameter("id");

		// Création du DAO Billet
		boolean erreur = false;
		DAO<Billet> daoBillet = null;
		try {
			daoBillet = new BilletDAO();
		} catch (DAOException e) {
			e.printStackTrace();
			erreur = true;
			json += "\"message\":\"Impossible de créer le DAO Groupe.\",";
		}

		// Si un id a été fourni en paramètre, alors on tente de récupérer le groupe
		// correspondant
		Billet b = null;
		if (!erreur && id != null) {
			try {
				b = daoBillet.find(Integer.valueOf(id));
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Aucun billet correspondant.\",";
			}
		}

		// Si le groupe est retrouvé dans la base de données, alors on le supprime
		if (!erreur && b != null) {
			try {
				daoBillet.delete(b);
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de supprimer le billet.\",";
			}
		}

		// Définition de l'état du status
		if (!erreur) {
			json += "\"status\":\"OK\", \"message\":\"Billet supprimé.\", \"data\":{}}";
		} else {
			json += "\"status\":\"KO\", \"data\":{}}";
		}

		// Écriture du json
		out.write(json);

		// Fermeture de writer
		out.close();
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Methods", "GET, PUT, HEAD, POST, DELETE, TRACE, OPTIONS");
		resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
	}
}

package groupeapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.mysql.DAO;
import dao.mysql.DAOException;
import dao.mysql.GroupeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.mysql.Groupe;

/**
 * Servlet implementation class GroupeServlet
 */
@WebServlet("/groupes")
public class GroupeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
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
		String id = request.getParameter("id");

		// Préparation du json à renvoyer
		String json = "";

		// Création du DAO Groupe
		DAO<Groupe> daoGroupe = null;
		try {
			daoGroupe = new GroupeDAO();
			if (id != null) {
				// Le client cherche un unique groupe
				Groupe a = daoGroupe.find(Integer.valueOf(id));
				json = "{\"status\": \"OK\",\"message\":\"1 groupe(s) correspondant(s).\",\"data\":";
				json += this.getJsonGroupe(a);
				json += "}";
			} else {
				// Le client cherche tous les groupes
				@SuppressWarnings("unchecked")
				List<Groupe> listGroupes = (List<Groupe>) daoGroupe.findAll();
				json = "{\"status\": \"OK\",\"message\":\"" + listGroupes.size()
						+ " groupe(s) correspondant(s).\",\"data\":[";
				for (Groupe g : listGroupes) {
					json += this.getJsonGroupe(g);
					if (!g.equals(listGroupes.get(listGroupes.size() - 1))) {
						json += ',';
					}
				}
				json += "]}";
			}
		} catch (DAOException e) {
			e.printStackTrace();
			json = "{\"status\": \"KO\",\"message\":\"Aucun groupe correspondant.\",\"data\":{}";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			json = "{\"status\": \"KO\",\"message\":\"L'identifiant doit être un entier.\",\"data\":{}";
		}

		// Écriture du json
		out.write(json);

		// Fermeture de writer
		out.close();
	}

	/**
	 * Crée un objet Json représentant un groupe.
	 * 
	 * @param g le groupe à représenter
	 * @return l'équivalent du groupe en objet Json
	 */
	private String getJsonGroupe(Groupe g) {
		String json = "";
		json += "{";
		json += "\"idGroupe\":" + g.getIdGroupe() + ",";
		json += "\"nom\":\"" + g.getNom() + "\",";
		json += "\"nbArtistes\":\"" + g.getNbArtistes() + "\"";
		json += "}";
		return json;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
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
		String nom = request.getParameter("nom");

		// Création du DAO Groupe
		boolean erreur = false;
		DAO<Groupe> daoGroupe = null;
		try {
			daoGroupe = new GroupeDAO();
		} catch (DAOException e) {
			e.printStackTrace();
			erreur = true;
			json += "\"message\":\"Impossible de créer le DAO Groupe.\",";
		}

		// Création du groupe
		if (!erreur) {
			Groupe g = new Groupe();
			g.setNom(nom);

			// Ajout du groupe dans la base de données
			try {
				daoGroupe.create(g);
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de créer le groupe.\",";
			}
		}

		// Définition de l'état du status
		if (!erreur) {
			json += "\"status\":\"OK\", \"message\":\"Groupe ajouté.\", \"data\":{}}";
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
		String nom = request.getParameter("nom");

		// Création du DAO Groupe
		boolean erreur = false;
		DAO<Groupe> daoGroupe = null;
		try {
			daoGroupe = new GroupeDAO();
		} catch (DAOException e) {
			e.printStackTrace();
			erreur = true;
			json += "\"message\":\"Impossible de créer le DAO Groupe.\",";
		}

		// Si un id a été fourni en paramètre, alors on tente de récupérer le groupe
		// correspondant
		Groupe g = null;
		if (!erreur && id != null) {
			try {
				g = daoGroupe.find(Integer.valueOf(id));
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Aucun groupe correspondant.\",";
			}
		}

		// Si le groupe est retrouvé dans la base de données, alors on procède à sa
		// éventuelle modification
		if (!erreur && g != null) {
			g.setNom(nom);

			// Modification de l'artiste dans la base de données
			try {
				daoGroupe.update(g);
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de modifier le groupe.\",";
			}
		}

		// Définition de l'état du status
		if (!erreur) {
			json += "\"status\":\"OK\", \"message\":\"Groupe modifié.\", \"data\":{}}";
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

		// Création du DAO Groupe
		boolean erreur = false;
		DAO<Groupe> daoGroupe = null;
		try {
			daoGroupe = new GroupeDAO();
		} catch (DAOException e) {
			e.printStackTrace();
			erreur = true;
			json += "\"message\":\"Impossible de créer le DAO Groupe.\",";
		}

		// Si un id a été fourni en paramètre, alors on tente de récupérer le groupe
		// correspondant
		Groupe g = null;
		if (!erreur && id != null) {
			try {
				g = daoGroupe.find(Integer.valueOf(id));
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Aucun groupe correspondant.\",";
			}
		}

		// Si le groupe est retrouvé dans la base de données, alors on le supprime
		if (!erreur && g != null) {
			try {
				daoGroupe.delete(g);
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de supprimer le groupe.\",";
			}
		}

		// Définition de l'état du status
		if (!erreur) {
			json += "\"status\":\"OK\", \"message\":\"Groupe supprimé.\", \"data\":{}}";
		} else {
			json += "\"status\":\"KO\", \"data\":{}}";
		}

		// Écriture du json
		out.write(json);

		// Fermeture de writer
		out.close();
	}

}

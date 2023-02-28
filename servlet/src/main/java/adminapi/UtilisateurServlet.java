package adminapi;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.mysql.Utilisateur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import dao.mysql.DAO;
import dao.mysql.DAOException;
import dao.mysql.UtilisateurDAO;

/**
 * Servlet implementation class UtilisateurServlet
 */
@WebServlet("/utilisateurs")
public class UtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UtilisateurServlet() {
		super();
		// TODO Auto-generated constructor stub
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

		// Création du DAO Utilisateur
		DAO<Utilisateur> daoUtilisateur = null;
		try {
			daoUtilisateur = new UtilisateurDAO();
			if (id != null) {
				// Le client cherche un unique utilisateur
				Utilisateur u = daoUtilisateur.find(Integer.valueOf(id));
				json = "{\"status\": \"OK\",\"message\":\"1 utilisateur(s) correspondant(s).\",\"data\":";
				json += this.getJsonUtilisateur(u);
				json += "}";
			} else {
				// Le client cherche tous les utilisateurs
				@SuppressWarnings("unchecked")
				List<Utilisateur> listUtilisateurs = (List<Utilisateur>) daoUtilisateur.findAll();
				json = "{\"status\": \"OK\",\"message\":\"" + listUtilisateurs.size()
						+ " utilisateurs(s) correspondant(s).\",\"data\":[";
				for (Utilisateur u : listUtilisateurs) {
					json += this.getJsonUtilisateur(u);
					if (!u.equals(listUtilisateurs.get(listUtilisateurs.size() - 1))) {
						json += ',';
					}
				}
				json += "]}";
			}
		} catch (DAOException e) {
			e.printStackTrace();
			json = "{\"status\": \"KO\",\"message\":\"Aucun utilisateur correspondant.\",\"data\":{}";
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
	 * Crée un objet Json représentant un utilisateur.
	 * 
	 * @param a l'utilisateur à représenter
	 * @return l'équivalent de l'utilisateur en objet Json
	 */
	private String getJsonUtilisateur(Utilisateur u) {
		String json = "";
		json += "{";
		json += "\"idUtilisateur\":" + u.getIdUtilisateur() + ",";
		json += "\"mdp\":\"" + u.getMdp() + "\",";
		json += "\"nom\":\"" + u.getNom()+"\"";
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
		String mdp = request.getParameter("mdp");

		boolean erreur = false;
		if (nom != null) {
			if (nom.equals("admin")) {
				erreur = true;
				json += "\"message\":\"Impossible d'ajouter l'utilisateur admin.\",";
			}
		} else {
			erreur = true;
			json += "\"message\":\"Le nom doit être renseigné.\",";
		}

		if (!erreur && mdp == null) {
			erreur = true;
			json += "\"message\":\"Le mot de passe doit être renseigné.\",";
		}

		// Création du DAO Utilisateur
		DAO<Utilisateur> daoUtilisateur = null;
		if (!erreur) {
			try {
				daoUtilisateur = new UtilisateurDAO();
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de créer le DAO Utilisateur.\",";
			}
		}

		// Création de l'utilisateur
		if (!erreur) {
			Utilisateur u = new Utilisateur();
			u.setNom(nom);
			u.setMdp(mdp);

			// Ajout de l'utilisateur dans la base de données
			try {
				daoUtilisateur.create(u);
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de créer l'utilisateur.\",";
			}
		}

		// Définition de l'état du status
		if (!erreur) {
			json += "\"status\":\"OK\", \"message\":\"Utilisateur ajouté.\", \"data\":{}}";
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
		String mdp = request.getParameter("mdp");

		boolean erreur = false;
		if (nom != null) {
			if (nom.equals("admin")) {
				erreur = true;
				json += "\"message\":\"Impossible de modifier le nom l'utilisateur admin.\",";
			}
		}
		// Création du DAO Utilisteur
		DAO<Utilisateur> daoUtilisateur = null;
		if (!erreur) {
			try {
				daoUtilisateur = new UtilisateurDAO();
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de créer le DAO Utilisateur.\",";
			}
		}

		// Si un id a été fourni en paramètre, alors on tente de récupérer l'utilisateur
		// correspondant
		Utilisateur u = null;
		if (!erreur && id != null) {
			try {
				u = daoUtilisateur.find(Integer.valueOf(id));
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Aucun utilisateur correspondant.\",";
			}
		}

		// Si l'utilisateur est retrouvé dans la base de données, alors on procède à sa
		// éventuelle modification
		if (!erreur && u != null) {

			if (nom != null) {
				u.setNom(nom);

			}
			if (!erreur && mdp != null) {
				u.setMdp(mdp);
			}

			// Modification de l'utilisateur dans la base de données
			if (!erreur) {
				try {
					daoUtilisateur.update(u);
				} catch (DAOException e) {
					e.printStackTrace();
					erreur = true;
					json += "\"message\":\"Impossible de modifier l'utilisateur.\",";
				}
			}
		}

		// Définition de l'état du status
		if (!erreur)

		{
			json += "\"status\":\"OK\", \"message\":\"Utilisateur modifié.\", \"data\":{}}";
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

		// Création du DAO Utilisateur
		boolean erreur = false;
		DAO<Utilisateur> daoUtilisateur = null;
		try {
			daoUtilisateur = new UtilisateurDAO();
		} catch (DAOException e) {
			e.printStackTrace();
			erreur = true;
			json += "\"message\":\"Impossible de créer le DAO Utilisateur.\",";
		}

		// Si un id a été fourni en paramètre, alors on tente de récupérer l'utilisateur
		// correspondant
		Utilisateur u = null;
		if (!erreur && id != null) {
			try {
				u = daoUtilisateur.find(Integer.valueOf(id));
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Aucun utilisateur correspondant.\",";
			}
		}

		// Si l'utilisateur est retrouvé dans la base de données, alors on le supprime
		if (!erreur && u != null) {
			try {
				if (!u.getNom().equals("admin")) {
					daoUtilisateur.delete(u);
				} else {
					erreur = true;
					json += "\"message\":\"Impossible de supprimer l'utilisateur admin.\",";
				}
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de supprimer l'utilisateur.\",";
			}
		}

		// Définition de l'état du status
		if (!erreur) {
			json += "\"status\":\"OK\", \"message\":\"Utilisateur supprimé.\", \"data\":{}}";
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

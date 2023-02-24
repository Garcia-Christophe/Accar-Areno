package groupeapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.mysql.ArtisteDAO;
import dao.mysql.DAO;
import dao.mysql.DAOException;
import dao.mysql.GroupeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.mysql.Artiste;
import pojo.mysql.Groupe;

/**
 * Servlet implementation class ArtisteServlet
 */
@WebServlet("/artistes")
public class ArtisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArtisteServlet() {
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

		// Création du DAO Artiste
		DAO<Artiste> daoArtiste = null;
		try {
			daoArtiste = new ArtisteDAO();
			if (id != null) {
				// Le client cherche un unique artiste
				Artiste a = daoArtiste.find(Integer.valueOf(id));
				json = "{\"status\": \"OK\",\"message\":\"1 artiste(s) correspondant(s).\",\"data\":";
				json += this.getJsonArtiste(a);
				json += "}";
			} else {
				// Le client cherche tous les artistes
				@SuppressWarnings("unchecked")
				List<Artiste> listArtistes = (List<Artiste>) daoArtiste.findAll();
				json = "{\"status\": \"OK\",\"message\":\"" + listArtistes.size()
						+ " artiste(s) correspondant(s).\",\"data\":[";
				for (Artiste a : listArtistes) {
					json += this.getJsonArtiste(a);
					if (!a.equals(listArtistes.get(listArtistes.size() - 1))) {
						json += ',';
					}
				}
				json += "]}";
			}
		} catch (DAOException e) {
			e.printStackTrace();
			json = "{\"status\": \"KO\",\"message\":\"Aucun artiste correspondant.\",\"data\":{}";
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
	 * Crée un objet Json représentant un artiste.
	 * 
	 * @param a l'artiste à représenter
	 * @return l'équivalent de l'artiste en objet Json
	 */
	private String getJsonArtiste(Artiste a) {
		String json = "";
		json += "{";
		json += "\"idArtiste\":" + a.getIdArtiste() + ",";
		json += "\"nom\":\"" + a.getNom() + "\",";
		json += "\"villeOrigine\":\"" + a.getVilleOrigine() + "\",";
		json += "\"dateNaissance\":\"" + a.getDateNaissance() + "\",";
		json += "\"idGroupe\":" + a.getIdGroupe().getIdGroupe();
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
		String villeOrigine = request.getParameter("villeOrigine");
		String dateNaissanceString = request.getParameter("dateNaissance");
		Date dateNaissance = null;
		if (dateNaissanceString != null) {
			// Transformation de la date : String -> Date
			try {
				dateNaissance = new SimpleDateFormat("yyyy-MM-dd").parse(dateNaissanceString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String idGroupe = request.getParameter("idGroupe");

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

		// Si un idGroupe a été fourni en paramètre, alors on tente de récupérer le
		// groupe correspondant
		Groupe g = null;
		if (!erreur && idGroupe != null) {
			try {
				g = daoGroupe.find(Integer.valueOf(idGroupe));
			} catch (DAOException e) {
				// Ne fait rien
			}
		}

		// Si aucun idGroupe n'a été fourni OU que l'idGroupe fourni ne correspond à
		// aucun Groupe, alors création d'un nouveau groupe pour l'artiste
		if (!erreur && g == null) {
			g = new Groupe();
			g.setNom(nom);
			try {
				daoGroupe.create(g);
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de créer le groupe.\",";
			}
		}

		// Création du DAO Artiste
		DAO<Artiste> daoArtiste = null;
		try {
			daoArtiste = new ArtisteDAO();
		} catch (DAOException e) {
			e.printStackTrace();
			erreur = true;
			json += "\"message\":\"Impossible de créer le DAO Artiste.\",";
		}

		// Création de l'artiste
		if (!erreur) {
			Artiste a = new Artiste();
			a.setNom(nom);
			a.setVilleOrigine(villeOrigine);
			a.setDateNaissance(dateNaissance);
			a.setIdGroupe(g);

			// Ajout de l'artiste dans la base de données
			try {
				daoArtiste.create(a);
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de créer l'artiste.\",";
			}
		}

		// Définition de l'état du status
		if (!erreur) {
			json += "\"status\":\"OK\", \"message\":\"Artiste ajouté.\", \"data\":{}}";
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
		String villeOrigine = request.getParameter("villeOrigine");
		String dateNaissanceString = request.getParameter("dateNaissance");
		Date dateNaissance = null;
		if (dateNaissanceString != null) {
			// Transformation de la date : String -> Date
			try {
				dateNaissance = new SimpleDateFormat("yyyy-MM-dd").parse(dateNaissanceString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String idGroupe = request.getParameter("idGroupe");

		// Création du DAO Artiste
		boolean erreur = false;
		DAO<Artiste> daoArtiste = null;
		try {
			daoArtiste = new ArtisteDAO();
		} catch (DAOException e) {
			e.printStackTrace();
			erreur = true;
			json += "\"message\":\"Impossible de créer le DAO Artiste.\",";
		}

		// Si un id a été fourni en paramètre, alors on tente de récupérer l'artiste
		// correspondant
		Artiste a = null;
		if (!erreur && id != null) {
			try {
				a = daoArtiste.find(Integer.valueOf(id));
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Aucun artiste correspondant.\",";
			}
		}

		// Si l'artiste est retrouvé dans la base de données, alors on procède à sa
		// éventuelle modification
		if (!erreur && a != null) {
			// Création du DAO Groupe
			DAO<Groupe> daoGroupe = null;
			try {
				daoGroupe = new GroupeDAO();
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de créer le DAO Groupe.\",";
			}
			// Décrémentation de l'ancien groupe de l'artiste
			Groupe oldG = null;
			if (!erreur && idGroupe != null) {
				try {
					oldG = daoGroupe.find(a.getIdGroupe().getIdGroupe());
				} catch (DAOException e) {
					e.printStackTrace();
					erreur = true;
					json += "\"message\":\"L'artiste a un groupe non existant.\",";
				}
				if (!erreur) {
					oldG.setNbArtistes(oldG.getNbArtistes() - 1);
					try {
						daoGroupe.update(oldG);
					} catch (DAOException e) {
						e.printStackTrace();
						erreur = true;
						json += "\"message\":\"Impossible de modifier le nombre d'artistes de l'ancien groupe de l'artiste.\",";
					}
				}
			}
			// Récupération du nouveau groupe
			Groupe g = null;
			if (!erreur && idGroupe != null) {
				try {
					g = daoGroupe.find(Integer.valueOf(idGroupe));
				} catch (DAOException e) {
					// Ne fait rien
				}
			}
			// Si l'idGroupe fourni ne correspond à aucun Groupe, alors création d'un
			// nouveau groupe pour l'artiste
			if (!erreur && idGroupe != null && g == null) {
				g = new Groupe();
				g.setNom(nom);
				try {
					daoGroupe.create(g);
				} catch (DAOException e) {
					e.printStackTrace();
					erreur = true;
					json += "\"message\":\"Impossible de créer le nouveau groupe de l'artiste.\",";
				}
			}

			// Modification de l'artiste
			if (!erreur) {
				if (nom != null) {
					a.setNom(nom);
				}
				if (villeOrigine != null) {
					a.setVilleOrigine(villeOrigine);
				}
				if (dateNaissance != null) {
					a.setDateNaissance(dateNaissance);
				}
				if (idGroupe != null) {
					g.setNbArtistes(g.getNbArtistes() + 1);
					try {
						daoGroupe.update(g);
						a.setIdGroupe(g);
					} catch (DAOException e) {
						e.printStackTrace();
						erreur = true;
						json += "\"message\":\"Impossible de modifier le nombre d'artistes du nouveau groupe de l'artiste.\",";
					}
				}
			}

			// Modification de l'artiste dans la base de données
			if (!erreur) {
				try {
					daoArtiste.update(a);
				} catch (DAOException e) {
					e.printStackTrace();
					erreur = true;
					json += "\"message\":\"Impossible de modifier l'artiste.\",";
				}
			}
		}

		// Définition de l'état du status
		if (!erreur) {
			json += "\"status\":\"OK\", \"message\":\"Artiste modifié.\", \"data\":{}}";
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

		// Création du DAO Artiste
		boolean erreur = false;
		DAO<Artiste> daoArtiste = null;
		try {
			daoArtiste = new ArtisteDAO();
		} catch (DAOException e) {
			e.printStackTrace();
			erreur = true;
			json += "\"message\":\"Impossible de créer le DAO Artiste.\",";
		}

		// Si un id a été fourni en paramètre, alors on tente de récupérer l'artiste
		// correspondant
		Artiste a = null;
		if (!erreur && id != null) {
			try {
				a = daoArtiste.find(Integer.valueOf(id));
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Aucun artiste correspondant.\",";
			}
		}

		// Si l'artiste est retrouvé dans la base de données, alors on le supprime
		if (!erreur && a != null) {
			try {
				daoArtiste.delete(a);
			} catch (DAOException e) {
				e.printStackTrace();
				erreur = true;
				json += "\"message\":\"Impossible de supprimer l'artiste.\",";
			}
		}

		// Définition de l'état du status
		if (!erreur) {
			json += "\"status\":\"OK\", \"message\":\"Artiste supprimé.\", \"data\":{}}";
		} else {
			json += "\"status\":\"KO\", \"data\":{}}";
		}

		// Écriture du json
		out.write(json);

		// Fermeture de writer
		out.close();
	}

}

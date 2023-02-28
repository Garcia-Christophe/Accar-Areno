package ressourceapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dao.mongodb.DaoRessources;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.mongodb.Ressource;
import pojo.mongodb.Sujets;

/**
 * Servlet implementation class RessourceServlet
 */
@WebServlet("/ressources")
public class RessourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RessourceServlet() {
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
		String nom = request.getParameter("nom");

		// Préparation du json à renvoyer
		String json = "";

		// Création du DAO Ressources
		DaoRessources daoRessources = new DaoRessources();

		// Récupération des ressources correspondant au sujet fourni
		List<Ressource> liste = daoRessources.findByName(nom);
		json = "{\"status\": \"OK\",\"message\":\"" + liste.size() + " ressources(s) correspondante(s).\",\"data\":[";
		for (Ressource r : liste) {
			json += this.getJsonRessource(r);
			if (!r.equals(liste.get(liste.size() - 1))) {
				json += ',';
			}
		}
		// Récupération de la ressource trouvée par URL
		Ressource resUrl = daoRessources.findByUrl(nom);
		if (resUrl != null) {
			if (!liste.isEmpty()) {
				json += ",";
			}
			json += this.getJsonRessource(resUrl);
		}
		json += "]}";

		// Écriture du json
		out.write(json);

		// Fermeture de writer
		out.close();
	}

	/**
	 * Crée un objet Json représentant une ressource.
	 * 
	 * @param r la ressource à représenter
	 * @return l'équivalent de la ressource en objet Json
	 */
	private String getJsonRessource(Ressource r) {
		String json = "";
		json += "{";
		json += "\"url\":\"" + r.getUrl() + "\",";
		json += "\"type\":\"" + r.getType() + "\",";
		json += "\"date\":\"" + r.getDate() + "\",";
		json += "\"auteur\":\"" + r.getAuteur() + "\"";
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
		String json = "";

		// Récupération des paramètres
		String url = request.getParameter("url");
		String type = request.getParameter("type");
		String date = request.getParameter("date");
		String auteur = request.getParameter("auteur");

		// Création du DAO Ressources
		DaoRessources daoRessources = new DaoRessources();

		// Création de la ressource
		Sujets sujets = new Sujets();
		Ressource ressource = new Ressource(url, type, date, auteur, sujets);
		daoRessources.create(ressource);
		json = "{\"status\": \"OK\",\"message\":\"Ressource ajoutée.\",\"data\":{}}";

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
		String json = "";

		// Récupération des paramètres
		String previousUrl = request.getParameter("previousUrl");
		String url = request.getParameter("url");
		String type = request.getParameter("type");
		String auteur = request.getParameter("auteur");
		String date = request.getParameter("date");
		String sujetType = request.getParameter("sujetType");
		String sujet = request.getParameter("sujet");

		// Création du DAO Ressources
		DaoRessources daoRessources = new DaoRessources();

		// Si l'url a été fourni en paramètre
		if (previousUrl != null && previousUrl.length() > 0) {
			// Modification de la ressource
			Ressource res = daoRessources.findByUrl(previousUrl);
			res.setUrl(url);
			res.setAuteur(auteur);
			res.setDate(date);
			res.setType(type);
			Sujets sujets = res.getSujets();
			ArrayList<String> liste = null;
			switch (sujetType) {
			case "groupe":
				liste = sujets.getGroupe();
				liste.add(sujet);
				sujets.setGroupe(liste);
				break;
			case "concert":
				liste = sujets.getConcert();
				liste.add(sujet);
				sujets.setConcert(liste);
				break;
			case "soiree":
				liste = sujets.getSoiree();
				liste.add(sujet);
				sujets.setSoiree(liste);
				break;
			case "salle":
				liste = sujets.getSalle();
				liste.add(sujet);
				sujets.setSalle(liste);
				break;
			default:
				break;
			}
			res.setSujets(sujets);

			// Modification de la ressource
			daoRessources.update(res, previousUrl);
			json = "{\"status\": \"OK\",\"message\":\"Ressource modifiée.\",\"data\":{}}";
		} else {
			json = "{\"status\": \"KO\",\"message\":\"URL manquante.\",\"data\":{}}";
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
		String json = "";

		// Récupération des paramètres
		String url = request.getParameter("url");
		String nom = request.getParameter("nom");

		// Création du DAO Ressources
		DaoRessources daoRessources = new DaoRessources();

		// Suppression de la ressource ou enlèvement de la ressource liée au nom
		if (nom == null || nom.length() == 0) {
			// Suppression de la ressource
			daoRessources.delete(url);
			json = "{\"status\": \"OK\",\"message\":\"Ressource supprimée.\",\"data\":{}}";
		} else {
			// Retirage de la ressource correspondant au nom
			Ressource res = daoRessources.findByUrl(url);
			Sujets sujets = res.getSujets();
			ArrayList<String> groupes = sujets.getGroupe();
			groupes.remove(nom);
			sujets.setGroupe(groupes);
			ArrayList<String> concerts = sujets.getConcert();
			concerts.remove(nom);
			sujets.setConcert(concerts);
			ArrayList<String> soirees = sujets.getSoiree();
			soirees.remove(nom);
			sujets.setSoiree(soirees);
			ArrayList<String> salles = sujets.getSalle();
			salles.remove(nom);
			sujets.setSalle(salles);
			daoRessources.update(res, url);
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

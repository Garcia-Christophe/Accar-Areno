package ressourceapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.mongodb.DaoRessources;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.mongodb.Ressource;

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
		DaoRessources daoRessources = null;
		daoRessources = new DaoRessources();

		// Récupération des ressources correspondants aux filtres
		List<Ressource> liste = daoRessources.find(nom);
		json = "{\"status\": \"OK\",\"message\":\"" + liste.size() + " ressources(s) correspondante(s).\",\"data\":[";
		for (Ressource r : liste) {
			json += this.getJsonRessource(r);
			if (!r.equals(liste.get(liste.size() - 1))) {
				json += ',';
			}
		}
		json += "]}";

		System.out.println(json);

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
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Methods", "GET, PUT, HEAD, POST, DELETE, TRACE, OPTIONS");
		resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
	}

}

package groupeapi;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.mysql.Artiste;
import pojo.mysql.Groupe;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import dao.mysql.ArtisteDAO;
import dao.mysql.DAO;
import dao.mysql.DAOException;
import dao.mysql.GroupeDAO;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String json = "";
		DAO<Artiste> daoArtiste = null;
		try {
			daoArtiste = new ArtisteDAO();
			System.out.println(id);
			if (id != null) {
				// Le client cherche un artiste
				Artiste a = daoArtiste.find(Integer.valueOf(id));
				json+=this.getJsonArtiste(a);
			} else {
				// Le client cherche tous les artistes
				json+="[";
				List<Artiste> listArtistes = (List<Artiste>)daoArtiste.findAll();
				for(Artiste a : listArtistes) {
					json+=this.getJsonArtiste(a);
					if(!a.equals(listArtistes.get(listArtistes.size()-1))) {
						json+=',';
					}
				}
				json+="]";
			}
			out.write(json);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();
	}

	private String getJsonArtiste(Artiste a) {
		String json="";
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String json = "{";
		String nom = request.getParameter("nom");
		String villeOrigine = request.getParameter("villeOrigine");
		String dateNaissanceString = request.getParameter("dateNaissance");
		Date dateNaissance = null;
		if(dateNaissanceString != null) {
			try {
				dateNaissance = new SimpleDateFormat("yyyy-MM-dd").parse(dateNaissanceString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String idGroupe = request.getParameter("idGroupe");
		
		DAO<Groupe> daoGroupe = null;
		try {
			daoGroupe = new GroupeDAO();
			Groupe g = null;
			try {
				g = daoGroupe.find(Integer.valueOf(idGroupe));
			}catch(DAOException e) {
				g=new Groupe();
				g.setNom(nom);
				try {
					daoGroupe.create(g);
				} catch (DAOException e1) {
					e1.printStackTrace();
					throw new DAOException("Impossible de creer le groupe de l'artiste.");
				}
			}
			Artiste a = new Artiste();
			a.setNom(nom);
			a.setVilleOrigine(villeOrigine);
			a.setDateNaissance(dateNaissance);
			a.setIdGroupe(g);
			DAO<Artiste> daoArtiste =  new ArtisteDAO();
			daoArtiste.create(a);
			json+= "\"status\":\"OK\"}";
		} catch (DAOException e1) {
			e1.printStackTrace();
			json+= "\"status\":\"KO\"}";
		}
		out.write(json);
	}

}

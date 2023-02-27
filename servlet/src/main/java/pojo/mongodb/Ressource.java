package pojo.mongodb;

import java.util.Objects;

public class Ressource {
	private String url;
	private String type;
	private String date;
	private String auteur;
	private Sujets sujets;

	public Ressource(String url, String type, String date, String auteur, Sujets sujets) {
		this.url = url;
		this.type = type;
		this.date = date;
		this.auteur = auteur;
		this.sujets = sujets;
	}

	public Ressource() {

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Sujets getSujets() {
		return sujets;
	}

	public void setSujets(Sujets sujets) {
		this.sujets = sujets;
	}

	@Override
	public int hashCode() {
		return Objects.hash(url, type, date, auteur, sujets);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ressource other = (Ressource) obj;
		return Objects.equals(url, other.url) && Objects.equals(type, other.type) && Objects.equals(date, other.date)
				&& Objects.equals(auteur, other.auteur) && Objects.equals(sujets, other.sujets);
	}
}

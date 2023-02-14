package pojo.mongodb;

import java.util.ArrayList;
import java.util.Objects;

public class Sujets {
    private ArrayList<String> groupe;
    private ArrayList<String> concert;
    private ArrayList<String> soiree;
    private ArrayList<String> salle;

    public Sujets(ArrayList<String> groupe, ArrayList<String> concert, ArrayList<String> soiree, ArrayList<String> salle) {
        this.groupe = groupe;
        this.concert = concert;
        this.soiree = soiree;
        this.salle = salle;
    }

    public Sujets() {
        this.groupe = new ArrayList<String>();
        this.concert = new ArrayList<String>();
        this.soiree = new ArrayList<String>();
        this.salle = new ArrayList<String>();
    }

    public ArrayList<String> getGroupe() {
        return groupe;
    }

    public void setGroupe(ArrayList<String> groupe) {
        this.groupe = groupe;
    }

    public ArrayList<String> getConcert() {
        return concert;
    }

    public void setConcert(ArrayList<String> concert) {
        this.concert = concert;
    }

    public ArrayList<String> getSoiree() {
        return soiree;
    }

    public void setSoiree(ArrayList<String> soiree) {
        this.soiree = soiree;
    }

    public ArrayList<String> getSalle() {
        return salle;
    }

    public void setSalle(ArrayList<String> salle) {
        this.salle = salle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupe, concert, soiree, salle);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sujets other = (Sujets) obj;
        return Objects.equals(groupe, other.groupe) && Objects.equals(concert, other.concert)
                && Objects.equals(soiree, other.soiree) && Objects.equals(salle, other.salle);
    }
}

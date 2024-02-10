package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Exposition {
    private int id;
    private String nom;
    private Date dateDebut;
    private Date dateFin;
    private  int nbrOeuvre;

    public Exposition() {}

    public Exposition(int id, String nom, Date dateDebut, Date dateFin, int nbrOeuvre) {
        this.id = id;
        this.nom = nom;
        this.dateDebut =dateDebut;
        this.dateFin =dateFin;
        this.nbrOeuvre = nbrOeuvre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbrOeuvre() {
        return nbrOeuvre;
    }

    public void setNbrOeuvre(int nbrOeuvre) {
        this.nbrOeuvre = nbrOeuvre;
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", nbrOeuvre=" + nbrOeuvre +
                "}\n";
    }
}

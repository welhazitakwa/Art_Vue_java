package models;

import java.util.Date;

public class VenteEncheres {
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private float prixDepart;
    private String statue;
    private int idExposition;

    public VenteEncheres() {
    }

    public VenteEncheres(int id, Date dateDebut, Date dateFin, float prixDepart, String statue, int idExposition) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixDepart = prixDepart;
        this.statue = statue;
        this.idExposition = idExposition;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getPrixDepart() {
        return prixDepart;
    }

    public void setPrixDepart(float prixDepart) {
        this.prixDepart = prixDepart;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }
    public int getIdExposition() {
        return idExposition;
    }

    public void setIdExposition(int idExposition) {
        this.idExposition = idExposition;
    }

    @Override
    public String toString() {
        return "VenteEncheres{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prixDepart=" + prixDepart +
                ", statue='" + statue + '\'' +
                "}\n";
    }
}

package models;

import java.util.Date;

public class OffreEnchere {
    private int id;
    private float montant;
    private Date date;
    private int id_VenteEnchere;

    public OffreEnchere() {
    }

    public OffreEnchere(int id, float montant, Date date, int id_VenteEnchere) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.id_VenteEnchere = id_VenteEnchere;
    }

    public OffreEnchere(int id, float montant, Date date) {
        this.id = id;
        this.montant = montant;
        this.date = date;
    }

    public int getId_VenteEnchere() {
        return id_VenteEnchere;
    }

    public void setId_VenteEnchere(int id_VenteEnchere) {
        this.id_VenteEnchere = id_VenteEnchere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OffreEnchere{" +
                "id=" + id +
                ", montant=" + montant +
                ", date=" + date +
                "}\n";
    }
}

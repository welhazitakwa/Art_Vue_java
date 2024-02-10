package models;
import java.util.Date;

public class Commande {
    public int id;
    public float montant;
    public Date date;
    public String etat;

    public Commande(int id, float montant, Date date, String etat) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.etat = etat;
    }

    public Commande(float montant, Date date, String etat) {
        this.montant = montant;
        this.date = date;
        this.etat = etat;
    }

    public Commande() {

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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}

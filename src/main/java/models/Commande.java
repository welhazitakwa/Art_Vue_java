package models;
import java.time.LocalDate;

public class Commande {
    public int id;
    public float montant;
    public LocalDate date;
    public String etat;

    public Commande(int id, float montant, LocalDate date, String etat) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.etat = etat;
    }

    public Commande(float montant, LocalDate date, String etat) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", montant=" + montant +
                ", date=" + date +
                ", etat='" + etat + '\'' +
                '}';
    }
}

package models;
import java.util.Date;
import models.panieroeuvre;
public class Commande {
    private int id;
    private float montant;
    private Date date;
    private String etat;
  private Panier panier;
public Commande(){}

    public Commande(int id, float montant, Date date,  Panier panier) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.etat = "En attente";
        this.panier = panier;
    }

    public Commande(float montant, Date date, Panier panier) {
        this.montant = montant;
        this.date = date;
        this.etat = "En attente";
        this.panier = panier;
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

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", montant=" + montant +
                ", date=" + date +
                ", etat='" + etat + '\'' +
                ", panier=" + panier +
                '}';
    }
}

package models;
import java.time.LocalDate;

public class Panier {
public int id;
public int quantite;
public LocalDate dateAjout;
    public Panier() {

    }

    public Panier(int quantite, LocalDate dateAjout) {
        this.quantite = quantite;
        this.dateAjout = dateAjout;
    }

    public Panier(int id, int quantite, LocalDate dateAjout) {
        this.id = id;
        this.quantite = quantite;
        this.dateAjout = dateAjout;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public LocalDate getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDate date) {
        this.dateAjout = date;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", quantite=" + quantite +
                ", dateAjout=" + dateAjout +
                '}';
    }
}

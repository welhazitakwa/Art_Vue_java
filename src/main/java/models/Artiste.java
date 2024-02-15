package models;

import java.sql.Date;

public class Artiste extends Utilisateur {
    private int id ;
    private String description ;

    public Artiste(String nom, String prenom, String email, int numTel, String login, String mdp,
                   String image, String genre, Date dateNaissance, String adresse,  String description) {
        super(nom, prenom, email, numTel, login, mdp, image, genre, dateNaissance, adresse);
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Artiste{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}

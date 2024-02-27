package models;

import java.time.LocalDate;

public class Concours {

    private  int id;
    private String titre;
    private  LocalDate date_debut;
    private LocalDate date_fin;
    private  String description;

    public Concours() {

    }
    public Concours(int id, String titre, LocalDate date_debut, LocalDate date_fin, String description ) {
        this.id = id;
        this.titre = titre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description ;
    }

    public Concours(String titre, LocalDate date_debut, LocalDate date_fin, String description) {
        this.titre = titre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description ;
    }

    public int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public  String getTitre() {
        return titre;
    }

    public  void setTitre(String titre) {
        this.titre = titre;
    }

    public  LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public  String getDescription() {
        return description;
    }

    public  void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(LocalDate of) {this.date_debut=date_debut;
    }

    public  void setDateFin(LocalDate of) {this.date_fin=date_fin;
    }

    @Override
    public String toString() {
        return "Concours{" +
                "id=" + id +
                ", titre='" + titre+ '\'' +
                ", date debut='" + date_debut + '\'' +
                ", date fin=" + date_fin +
                ", description=" + description +
                '}';
    }
}

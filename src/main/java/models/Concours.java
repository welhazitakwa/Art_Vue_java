package models;

import java.time.LocalDate;
import java.util.Date;

public class Concours {

    private static int id;
    private static String titre;
    private static Date date_debut;
    private static Date date_fin;
    private static String description;

    public Concours() {

    }
    public Concours(int id, String titre, Date date_debut, Date date_fin, String description ) {
        this.id = id;
        this.titre = titre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description ;
    }

    public Concours(String titre, Date date_debut, Date date_fin, String description) {
        this.titre = titre;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description ;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Concours.id = id;
    }

    public static String getTitre() {
        return titre;
    }

    public static void setTitre(String titre) {
        Concours.titre = titre;
    }

    public static Date getDate_debut() {
        return date_debut;
    }

    public static void setDate_debut(Date date_debut) {
        Concours.date_debut = date_debut;
    }

    public static Date getDate_fin() {
        return date_fin;
    }

    public static void setDate_fin(Date date_fin) {
        Concours.date_fin = date_fin;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Concours.description = description;
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

package models;

import java.time.LocalDate;

public class Evènement {

    private static int id;
    private static String nom;
    private static String lieu;
    private static LocalDate date;

    public   Evènement() {
    }

    public Evènement(int id , String nom, String lieu, LocalDate date) {
        this.id = id;
        this.nom = nom;
        this.date=  date;
        this.lieu = lieu;

    }
    public Evènement(String nom, String lieu, LocalDate date) {
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;

    }

    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public static LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Evènement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", lieu='" + lieu + '\'' +
                ", date=" + date +
                '}';
    }



}


package models;

import java.util.Date;
import java.util.List;

public class  evenement {
    private  int id;
    private  String nom;
    private  String lieu;

    public void setId(int id) {
        this.id = id;
    }

    public evenement(int id, String nom, String lieu, Date date, int calender, List<Integer> utulisateurs, float price, int capacite) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.calender = calender;
        this.utulisateurs = utulisateurs;
        this.price = price;
        this.capacite = capacite;
    }
    public evenement( String nom, String lieu, Date date, int calender, float price, int capacite) {
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.calender = calender;
        this.price = price;
        this.capacite = capacite;
    }
    public evenement( String nom, String lieu, Date date, float price, int capacite) {
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.price = price;
        this.capacite = capacite;
    }

    public int getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCalender(int calender) {
        this.calender = calender;
    }

    public void setUtulisateurs(List<Integer> utulisateurs) {
        this.utulisateurs = utulisateurs;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() {
        return lieu;
    }

    public Date getDate() {
        return date;
    }

    public int getCalender() {
        return calender;
    }

    public List<Integer> getUtulisateurs() {
        return utulisateurs;
    }

    public float getPrice() {
        return price;
    }

    public int getCapacite() {
        return capacite;
    }

    private Date date;
    private int calender;
    private List<Integer> utulisateurs;
    private float price;
    private int capacite;
    private List<String> utulisteursnames;

    public void setUtulisteursnames(List<String> utulisteursnames) {
        this.utulisteursnames = utulisteursnames;
    }

    public evenement() {
    }

    public void setCalendername(String calendername) {
        this.calendername = calendername;
    }

    public String getCalendername() {
        return calendername;
    }

    public List<String> getUtulisteursnames() {
        return utulisteursnames;
    }
    public String calendername;
}

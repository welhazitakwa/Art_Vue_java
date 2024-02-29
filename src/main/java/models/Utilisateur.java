package models;

import java.sql.Date;

public class Utilisateur {
    private int id, numTel, profil, etat_compte, cin;
    private Date dateNaissance, date_inscription ;
    private String nom, prenom, login, mdp, email, adresse, image, genre ;

    public Utilisateur (){}
    public Utilisateur(int id,  String nom,String prenom, String email, int numTel, String login, String mdp,String image, String genre,
                       Date dateNaissance, String adresse) {
        this.id = id;
        this.numTel = numTel;
        this.dateNaissance = dateNaissance;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.email = email;
        this.adresse = adresse;
        this.image = image;
        this.genre = genre;
    }
    public Utilisateur(String nom, String prenom,String email, String login, String mdp){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email ;
        this.login = login ;
        this.mdp= mdp;
    }
    public Utilisateur(int id,String nom, String prenom,String email, int numTel, String login, int cin,String mdp, int profil,String image,
                       String genre,Date dateNaissance,String adresse,  int etat_compte) {
        this.id = id;
        this.numTel = numTel;
        this.profil = profil;
        this.etat_compte = etat_compte;
       // this.date_inscription = date_inscription;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.email = email;
        this.adresse = adresse;
        this.image = image;
        this.genre = genre;
    }

    public Utilisateur(
            String nom,String prenom, String email, int numTel, String login, String mdp,String image, String genre,
            Date dateNaissance, String adresse
                         ) {
        this.numTel = numTel;
        this.dateNaissance = dateNaissance;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.email = email;
        this.adresse = adresse;
        this.image = image;
        this.genre = genre;
    }
    public Utilisateur( String nom, String prenom,String email, int numTel, String login, int cin,String mdp, int profil,String image,
                        String genre,Date dateNaissance,String adresse,  int etat_compte ) {
        this.numTel = numTel;
        this.profil = profil;
        this.etat_compte = etat_compte;
       // this.date_inscription = date_inscription;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.email = email;
        this.adresse = adresse;
        this.image = image;
        this.genre = genre;
    }
    public Utilisateur(int id, String nom, String prenom , String email , int numTel ,String login , int cin,
                       String image ,String genre ,Date dateNaissance ,String adresse ){

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email ;
        this.numTel = numTel;
        this.login = login;
        this.cin = cin;
        this.image = image;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getProfil() {
        return profil;
    }

    public void setProfil(int profil) {
        this.profil = profil;
    }

    public int getEtat_compte() {
        return etat_compte;
    }

    public void setEtat_compte(int etat_compte) {
        this.etat_compte = etat_compte;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", numTel=" + numTel +
                ", profil=" + profil +
                ", etat_compte=" + etat_compte +
                ", date_inscription=" + date_inscription +
                ", cin=" + cin +
                ", dateNaissance=" + dateNaissance +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", login='" + login + '\'' +
                ", mdp='" + mdp + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", image='" + image + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}

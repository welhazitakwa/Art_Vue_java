package models;

import java.util.Date;

public class utilisateur {
    private int id, numTel;
    private Date dateNaissance ;
    private String nom, prenom, login, mdp, email, adresse, image, genre ;

    public utilisateur(int id, int numTel, Date dateNaissance, String nom, String prenom,
                       String login, String mdp, String email, String adresse, String image, String genre) {
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

    public utilisateur(int numTel, Date dateNaissance, String nom, String prenom, String login, String mdp,
                       String email, String adresse, String image, String genre) {
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

    @Override
    public String toString() {
        return "utilisateur avec id =" + id +
                ", numTel=" + numTel +
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

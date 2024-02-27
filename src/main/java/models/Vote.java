package models;

import java.util.Date;

public class Vote {

    private int id;
    private int note;
    private int idUser;
    private int idConcours;
    private int idOeuvre;

    private String titreOeuvre;

    public Vote() {

    }

    public String getTitreOeuvre() {
        return titreOeuvre;
    }

    public void setTitreOeuvre(String titreOeuvre) {
        this.titreOeuvre = titreOeuvre;
    }

    public String getImageOeuvre() {
        return imageOeuvre;
    }

    public void setImageOeuvre(String imageOeuvre) {
        this.imageOeuvre = imageOeuvre;
    }

    public String getTitreConcours() {
        return titreConcours;
    }

    public void setTitreConcours(String titreConcours) {
        this.titreConcours = titreConcours;
    }

    private String imageOeuvre;
    private String titreConcours;

    // Ajoutez d'autres attributs et méthodes selon vos besoins

    public Vote(int id, int note, String titreOeuvre, String imageOeuvre, String titreConcours) {
        this.id = id;
        this.note = note;
        this.titreOeuvre = titreOeuvre;
        this.imageOeuvre = imageOeuvre;
        this.titreConcours = titreConcours;
    }


    public Vote(int id, int note, int idUser, int idConcours, int idOeuvre) {
        this.id = id;
        this.note = note;
        this.idUser = idUser;
        this.idConcours = idConcours;
        this.idOeuvre = idOeuvre;
    }

    public Vote(int note, int idUser, int idConcours, int idOeuvre) {
        this.note = note;
        this.idUser = idUser;
        this.idConcours = idConcours;
        this.idOeuvre = idOeuvre;
    }
    public Vote(int id,int note) {
        this.id = id;
        this.note = note;
    }


    // Les getters et setters pour les nouveaux attributs

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdConcours() {
        return idConcours;
    }

    public void setIdConcours(int idConcours) {
        this.idConcours = idConcours;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    // Les getters et setters pour les anciens attributs

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", note=" + note +
                ", idUser=" + idUser +
                ", idConcours=" + idConcours +
                ", idOeuvre=" + idOeuvre +
                '}';
    }
}
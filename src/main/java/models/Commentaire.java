package models;

import java.sql.Date;

public class Commentaire {
    private int id, client_id, oeuvre_id;
    private String commentaire ;
    private Date date_commentaire ;

    public Commentaire(int id, int client_id, int oeuvre_id, String commentaire, Date date_commentaire) {
        this.id = id;
        this.client_id = client_id;
        this.oeuvre_id = oeuvre_id;
        this.commentaire = commentaire;
        this.date_commentaire = date_commentaire;
    }

    public Commentaire() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getOeuvre_id() {
        return oeuvre_id;
    }

    public void setOeuvre_id(int oeuvre_id) {
        this.oeuvre_id = oeuvre_id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDate_commentaire() {
        return date_commentaire;
    }

    public void setDate_commentaire(Date date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", oeuvre_id=" + oeuvre_id +
                ", commentaire='" + commentaire + '\'' +
                ", date_commentaire=" + date_commentaire +
                '}';
    }
}

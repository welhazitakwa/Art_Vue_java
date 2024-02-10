package models;

import java.util.Date;

public class OeuvreArt {
    public int id;
    public String image;
    public String titre;
    public String description;
    public Date dateAjout;
    public float prixVente;
    public String categorie;
    public String status;
    public String artiste;

    public OeuvreArt(int id, String image, String titre, String description, Date dateAjout, float prixVente, String categorie, String status, String artiste) {
        this.id = id;
        this.image = image;
        this.titre = titre;
        this.description = description;
        this.dateAjout = dateAjout;
        this.prixVente = prixVente;
        this.categorie = categorie;
        this.status = status;

        this.artiste = artiste;
    }

    public OeuvreArt(String image, String titre, String description, Date dateAjout, float prixVente, String categorie, String status, String artiste) {
        this.image = image;
        this.titre = titre;
        this.description = description;
        this.dateAjout = dateAjout;
        this.prixVente = prixVente;
        this.categorie = categorie;
        this.status = status;
        this.artiste = artiste;
    }

    public OeuvreArt(int id, String image, String titre, String description, float prixVente, String categorie, String status, String artiste) {
        this.id = id ;
        this.image = image;
        this.titre = titre;
        this.description = description;
        this.prixVente = prixVente;
        this.categorie = categorie;
        this.status = status;
        this.artiste = artiste;
    }

    public OeuvreArt() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public float getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(float prixVente) {
        this.prixVente = prixVente;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    @Override
    public String toString() {
        return "OeuvreArt{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", dateAjout=" + dateAjout +
                ", prixVente=" + prixVente +
                ", categorie='" + categorie + '\'' +
                ", status='" + status + '\'' +
                ", artiste='" + artiste + '\'' +
                '}';
    }
}

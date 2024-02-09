package models;

import java.util.Date;

public class OeuvreArt {
    public int id;
    public String image;
    public String titre;
    public String description;
    public Date dateAjout;
    public float prixVente;
    public Categorie categorie;
    public StatusOeuvre status;
    public int QuantitéStock;
    public String artiste;

    public OeuvreArt(int id, String image, String titre, String description, Date dateAjout, float prixVente, Categorie categorie, StatusOeuvre status, int quantitéStock, String artiste) {
        this.id = id;
        this.image = image;
        this.titre = titre;
        this.description = description;
        this.dateAjout = dateAjout;
        this.prixVente = prixVente;
        this.categorie = categorie;
        this.status = status;
        QuantitéStock = quantitéStock;
        this.artiste = artiste;
    }

    public OeuvreArt(String image, String titre, String description, Date dateAjout, float prixVente, Categorie categorie, StatusOeuvre status, int quantitéStock, String artiste) {
        this.image = image;
        this.titre = titre;
        this.description = description;
        this.dateAjout = dateAjout;
        this.prixVente = prixVente;
        this.categorie = categorie;
        this.status = status;
        QuantitéStock = quantitéStock;
        this.artiste = artiste;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public StatusOeuvre getStatus() {
        return status;
    }

    public void setStatus(StatusOeuvre status) {
        this.status = status;
    }

    public int getQuantitéStock() {
        return QuantitéStock;
    }

    public void setQuantitéStock(int quantitéStock) {
        QuantitéStock = quantitéStock;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }
}

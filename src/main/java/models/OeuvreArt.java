package models;

import java.util.Date;

public class OeuvreArt {
    public int id;
    public String image;
    public String nom;
    public String description;
    public Date dateAjout;
    public float prix;
    public int QuantitéStock;

    public OeuvreArt(int id, String image, String nom, String description, Date dateAjout, float prix, int quantitéStock) {
        this.id = id;
        this.image = image;
        this.nom = nom;
        this.description = description;
        this.dateAjout = dateAjout;
        this.prix = prix;
        QuantitéStock = quantitéStock;
    }

    public OeuvreArt(String image, String nom, String description, Date dateAjout, float prix, int quantitéStock) {
        this.image = image;
        this.nom = nom;
        this.description = description;
        this.dateAjout = dateAjout;
        this.prix = prix;
        QuantitéStock = quantitéStock;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantitéStock() {
        return QuantitéStock;
    }

    public void setQuantitéStock(int quantitéStock) {
        QuantitéStock = quantitéStock;
    }
}

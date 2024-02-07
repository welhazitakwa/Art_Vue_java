package models;

public class Categorie {
    public int id;
    public String titre;

    public Categorie(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public Categorie(String titre) {
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}

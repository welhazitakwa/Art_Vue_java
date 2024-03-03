package models;

public class Likes {
    private int id;
    private int idUtilisateur;
    private int idOeuvreArt;
    private boolean estLike;

    // Constructeur
    public Likes(int id, int idUtilisateur, int idOeuvreArt, boolean estLike) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idOeuvreArt = idOeuvreArt;
        this.estLike = estLike;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdOeuvreArt() {
        return idOeuvreArt;
    }

    public void setIdOeuvreArt(int idOeuvreArt) {
        this.idOeuvreArt = idOeuvreArt;
    }

    public boolean isEstLike() {
        return estLike;
    }

    public void setEstLike(boolean estLike) {
        this.estLike = estLike;
    }

    // Méthode toString() pour l'affichage
    @Override
    public String toString() {
        return "Likes{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", idOeuvreArt=" + idOeuvreArt +
                ", estLike=" + estLike +
                '}';
    }
}


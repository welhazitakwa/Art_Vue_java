package models;
public class panieroeuvre {
    private int id;
    private Panier id_panier;
    private OeuvreArt id_oeuvre;
    private int quantite;

    public panieroeuvre() {
    }

    public panieroeuvre(int id, Panier id_panier, OeuvreArt id_oeuvre, int quantite) {
        this.id = id;
        this.id_panier = id_panier;
        this.id_oeuvre = id_oeuvre;
        this.quantite = quantite;
    }

    public panieroeuvre(Panier id_panier, OeuvreArt id_oeuvre, int quantite) {
        this.id_panier = id_panier;
        this.id_oeuvre = id_oeuvre;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Panier getId_panier() {
        return id_panier;
    }

    public void setId_panier(Panier id_panier) {
        this.id_panier = id_panier;
    }

    public OeuvreArt getId_oeuvre() {
        return id_oeuvre;
    }

    public void setId_oeuvre(OeuvreArt id_oeuvre) {
        this.id_oeuvre = id_oeuvre;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "panieroeuvre{" +
                "id=" + id +
                ", id_panier=" + id_panier +
                ", id_oeuvre=" + id_oeuvre +
                ", quantite=" + quantite +
                '}';
    }
}

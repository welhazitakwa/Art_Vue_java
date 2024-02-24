package services.panieroeuvre;
import models.OeuvreArt;

import java.sql.SQLException;
import java.util.List;
public interface Ipanieroeuvre {

    void ajouterOeuvreAuPanier(int id_panier, int id_oeuvre, int quantite) throws SQLException;
    public void modifierQuantiteOeuvreDansPanier(int id ,int id_panier, int id_oeuvre, int nouvelleQuantite) throws SQLException;
    public void supprimerOeuvreDuPanier(int id_panier, int id_oeuvre) throws SQLException ;
    public List<OeuvreArt> getOeuvresDuPanier(int id_panier) throws SQLException;
    }

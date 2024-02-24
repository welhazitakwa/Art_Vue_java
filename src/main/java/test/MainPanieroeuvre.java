package test;
import utils.MyDataBase;
import java.sql.SQLException;
import java.util.List;
import models.panieroeuvre;
import services.panieroeuvre.PanieroeuvreService;
import models.OeuvreArt;
public class MainPanieroeuvre {
    public static void main(String[] args) {
        MyDataBase d = MyDataBase.getInstance();
        PanieroeuvreService panieroeuvreService = new PanieroeuvreService();
        int id = 1;
        int idPanier = 18;
        int idOeuvre = 15;
        int quantite = 12;
        int nouvelleQuantite=11;
        try {
            panieroeuvreService.ajouterOeuvreAuPanier(idPanier, idOeuvre, quantite);
            System.out.println("Oeuvre ajoutée au panier avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'oeuvre au panier : " + e.getMessage());
        }
       /* try{
            panieroeuvreService.modifierQuantiteOeuvreDansPanier(id, idPanier, idOeuvre, nouvelleQuantite);
            System.out.println("modifié avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'oeuvre au panier : " + e.getMessage());
        }*/
      /*  try {
            panieroeuvreService.supprimerOeuvreDuPanier(idPanier, idOeuvre);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'oeuvre du panier : " + e.getMessage());
        }*/
        /*try {
            List<OeuvreArt> oeuvresDuPanier = panieroeuvreService.getOeuvresDuPanier(idPanier);
            // Afficher les détails de chaque oeuvre
            for (OeuvreArt oeuvre : oeuvresDuPanier) {
                System.out.println(oeuvre); // Assurez-vous que la classe OeuvreArt a une méthode toString appropriée
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des oeuvres du panier : " + e.getMessage());
        }*/
    }

}

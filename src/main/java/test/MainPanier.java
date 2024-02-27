package test;

import models.Panier;
import models.Utilisateur;
import services.Panier.PanierService;
import services.utilisateur.UtilisateurService;
import utils.MyDataBase;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDate.*;
import java.util.Date;
import java.util.List;
public class MainPanier {
    public static void main(String[] args) {
        MyDataBase d = MyDataBase.getInstance();
        PanierService pan1=new PanierService();
        PanierService pan2=new PanierService();
      UtilisateurService utilisateurService = new UtilisateurService();
      /*try{

          Panier panier = pan1.getPanierById(15);

          if (panier != null) {
              // Affichage des détails du panier
              System.out.println("Détails du panier avec l'ID " + 15 + ":");
              System.out.println("ID: " + panier.getId());
              System.out.println("Date d'ajout: " + panier.getDateAjout());
              System.out.println("Client: " + panier.getClient());
          } else {
              System.out.println("Le panier avec l'ID " + 15 + " n'existe pas.");
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
*/

/*try
{
    Utilisateur client1 = utilisateurService.getUtilisateurById(10);
    Panier panier1 = new Panier();

    panier1.setDateAjout(new Date());
    panier1.setClient(client1);
    pan1.AjouterPanier(panier1);
    System.out.println("Ajouté avec succès!");
}
catch (SQLException e) {
    System.out.println("Erreur lors de l'ajout du panier : " + e.getMessage());
}*/
   /* try {
        Utilisateur client1 = utilisateurService.getUtilisateurById(1);
        Panier panier1 = new Panier();
        panier1.setId(15);

        panier1.setDateAjout(new Date());
        panier1.setClient(client1);
        pan1.ModifierPanier(panier1);
    }
    catch (SQLException e) {
        System.out.println("Erreur lors de la modification du panier : " + e.getMessage());
    }*/

       /* try {

            pan1.SupprimerPanier(13);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'oeuvre d'art : " + e.getMessage());
        }*/

       /* try {

            List<Panier> paniers = pan1.AfficherPanier();


            if (paniers.isEmpty()) {
                System.out.println("Aucun panier trouvée.");
            } else {
                System.out.println("Liste des paniers :");

                for (Panier panier : paniers) {
                    System.out.println("ID : " + panier.getId());


                    System.out.println("Date Ajout : " + panier.getDateAjout());

                    System.out.println("Client : " + panier.getClient().getNom() + " " + panier.getClient().getPrenom());
                    System.out.println("------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des paniers : " + e.getMessage());
        }
*/
    }
}


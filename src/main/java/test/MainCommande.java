package test;

import models.Commande;
import models.OeuvreArt;
import models.Panier;
import models.panieroeuvre;
import services.Commande.CommandeService;
import  services.Panier.PanierService;
import utils.MyDataBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;


public class MainCommande {
    public static void main(String[] args) {

      /*  try {
            // Création d'un objet PanierService pour accéder aux fonctionnalités de gestion des paniers
            PanierService panierService = new PanierService();

            // Création d'un objet CommandeService pour accéder aux fonctionnalités de gestion des commandes
            CommandeService commandeService = new CommandeService();

            // Récupération du panier
            int PanierId = 19;
            Panier panier = panierService.getPanierById(PanierId);

            // Création d'un objet Commande
            Commande commande = new Commande();
            commande.setDate(new Date()); // Utilisation de la date actuelle
            commande.setPanier(panier);

            // Création de la commande pour le panier spécifié
            commandeService.creerCommande(commande, panier);
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
       /* try {
            // Créez un objet CommandeService pour accéder aux fonctionnalités de gestion des commandes
            CommandeService commandeService = new CommandeService();

            // ID de la commande à modifier
            int commandeId = 11; // Remplacez 1 par l'ID de la commande que vous souhaitez modifier

            // Récupérez la commande à l'aide de son ID
            Commande commande = commandeService.getCommandeById(commandeId);

            // Vérifiez si la commande existe
            if (commande != null) {
                // Affichez les détails de la commande avant la modification
                System.out.println("Détails de la commande avant modification : " + commande);

                // Modifiez l'état de la commande en tant qu'administrateur (par exemple, en l'expédiant)
                boolean estExpediee = true; // Indiquez si la commande est expédiée
                commandeService.modifierCommande(commande, estExpediee);

                // Affichez les détails de la commande après la modification
                commande = commandeService.getCommandeById(commandeId);
                System.out.println("Détails de la commande après modification : " + commande);
            } else {
                // La commande n'existe pas
                System.out.println("La commande avec l'ID " + commandeId + " n'existe pas.");
            }
        } catch (SQLException e) {
            // Gérez les exceptions
            e.printStackTrace();
        }*/


       /* CommandeService com = new CommandeService();

        // Supprimez une commande en utilisant son ID (remplacez idCommande par l'ID de la commande à supprimer)
        int idCommandeASupprimer = 9; // Remplacez 1 par l'ID de la commande que vous souhaitez supprimer
        try {
            com.supprimerCommande(idCommandeASupprimer);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la commande : " + e.getMessage());
        }*/
       /* CommandeService commandeService = new CommandeService();

        try {
            // Récupérez la liste des commandes
            List<Commande> commandes = commandeService.AfficherCommande();

            // Affichez les détails de chaque commande
            for (Commande commande : commandes) {
                System.out.println("ID de la commande : " + commande.getId());
                System.out.println("Montant de la commande : " + commande.getMontant());
                System.out.println("Date de la commande : " + commande.getDate());
                System.out.println("ID du panier associé à la commande : " + commande.getPanier().getId());
                System.out.println("----------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des commandes : " + e.getMessage());
        }*/


    }
}


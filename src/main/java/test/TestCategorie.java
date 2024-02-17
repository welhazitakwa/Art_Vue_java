package test;

import models.Categorie;
import services.categorie.CategorieService;
import utils.MyDataBase;

import java.sql.SQLException;

public class TestCategorie {
    public static void main(String[] args) {

        // MyDataBase d = MyDataBase.getInstance();
        CategorieService Cs1 = new CategorieService();
        CategorieService Cs2 = new CategorieService();
        CategorieService Cs3 = new CategorieService();
        CategorieService Cs4 = new CategorieService();
        CategorieService Cs5 = new CategorieService();
        CategorieService Cs6 = new CategorieService();

/*
        try {
            Cs1.AjouterCategorie(new Categorie("Peinture"));
            Cs2.AjouterCategorie(new Categorie("Sculpture"));
            Cs3.AjouterCategorie(new Categorie("Photographie"));
            Cs4.AjouterCategorie(new Categorie("Dessin"));
            Cs5.AjouterCategorie(new Categorie("Artisanat"));
            Cs6.AjouterCategorie(new Categorie("musique"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            Cs6.ModifierCategorie(new Categorie(10, "NouveauMusique"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            Cs6.SupprimerCategorie(10);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(Cs1.AfficherCategorie());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
*/
        try {
            // Nom de la catégorie à récupérer
            String nomCategorie = "Peinture";
            // Récupérer une catégorie par son nom
            Categorie categorie = Cs1.getCategorieByNom(nomCategorie);

            if (categorie != null) {
                System.out.println("Catégorie trouvée :");
                System.out.println("ID : " + categorie.getIdCategorie());
                System.out.println("Nom : " + categorie.getNomCategorie());
            } else {
                System.out.println("Aucune catégorie trouvée pour le nom : " + nomCategorie);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la catégorie : " + e.getMessage());
        }

        try {
            // Récupérer une catégorie par son ID
            int idCategorie = 8; // ID de la catégorie à récupérer
            Categorie categorie = Cs1.getCategorieById(idCategorie);

            if (categorie != null) {
                System.out.println("Catégorie trouvée :");
                System.out.println("ID : " + categorie.getIdCategorie());
                System.out.println("Nom : " + categorie.getNomCategorie());
            } else {
                System.out.println("Aucune catégorie trouvée pour l'ID : " + idCategorie);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la catégorie : " + e.getMessage());
        }
    }

}

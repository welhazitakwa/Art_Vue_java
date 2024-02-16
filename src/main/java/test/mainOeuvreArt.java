package test;
import models.Categorie;
import models.OeuvreArt;
import services.oeuvreArt.OeuvreArtService;

import java.sql.SQLException;
import java.util.List;


public class mainOeuvreArt {

    //MyDataBase d = MyDataBase.getInstance();
    public static void main(String[] args) {

        OeuvreArtService Oa1 = new OeuvreArtService();
        Categorie Cs1 = new Categorie(8,"Dessin");
        Categorie Cs2 = new Categorie(5,"Peinture");

        /*try {
            Oa1.AjouterOeuvreArt(new OeuvreArt("chemin/image.jpg","Peinture murale",
                    "Avec noir et blanc",java.sql.Date.valueOf(LocalDate.now()), 
                    100.0f,Cs1, "Disponible","Nourhen Ferjeni"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            Oa1.ModifierOeuvreArt(new OeuvreArt(3,"chemin/image33.jpg","Peinture murale","Avec noir et blanc",100.0f,Cs2,"Vendu","Nourhen Ferjeni"));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }*/

        /*try {
            Oa1.SupprimerOeuvreArt(2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        System.out.println("-------------------------Simple Affichage ----------------");
        System.out.println();
        try {
            System.out.println(Oa1.AfficherOeuvreArt());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       /* int idOeuvreArt = 6;
        try {
            OeuvreArt oeuvre = Oa1.AfficherOeuvreArtById(idOeuvreArt);
            if (oeuvre != null) {
                System.out.println("Œuvre trouvée :");
                System.out.println("ID : " + oeuvre.getId());
                System.out.println("Catégorie : " + oeuvre.getCategorie());
                System.out.println("Titre : " + oeuvre.getTitre());
                System.out.println("Artiste : " + oeuvre.getArtiste());
                System.out.println("Description : " + oeuvre.getDescription());
                System.out.println("Image : " + oeuvre.getImage());
                System.out.println("Prix de vente : " + oeuvre.getPrixVente());
            } else {
                System.out.println("Œuvre non trouvée pour l'ID : " + idOeuvreArt);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'œuvre d'art : " + e.getMessage());
        }*/
        System.out.println();
        System.out.println("-------------------------Affichage By chaque Oeuvre-------------");
        System.out.println();
        try {
            List<OeuvreArt> oeuvres = Oa1.getAllOeuvreArt();
            for (OeuvreArt oeuvre : oeuvres) {
                System.out.println("ID : " + oeuvre.getId());
                System.out.println("Image : " + oeuvre.getImage());
                System.out.println("Titre : " + oeuvre.getTitre());
                System.out.println("Description : " + oeuvre.getDescription());
                System.out.println("Date Ajout : " + oeuvre.getDateAjout());
                System.out.println("Prix de vente : " + oeuvre.getPrixVente());
                System.out.println("Catégorie : " + oeuvre.getCategorie().getNomCategorie());
                System.out.println("Statut : " + oeuvre.getStatus());
                System.out.println("Artiste : " + oeuvre.getArtiste());
                System.out.println("------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des œuvres d'art avec catégories : " + e.getMessage());
        }


        System.out.println();
        System.out.println("-------------------------Affichage By Categorie-------------");
        System.out.println();
        String categorie = "Peinture"; // Spécifiez le nom de la catégorie que vous souhaitez récupérer

        try {
            List<OeuvreArt> oeuvres = Oa1.getAllOeuvreArtByCategorie(categorie);
            for (OeuvreArt oeuvre : oeuvres) {
                System.out.println("ID : " + oeuvre.getId());
                System.out.println("Image : " + oeuvre.getImage());
                System.out.println("Titre : " + oeuvre.getTitre());
                System.out.println("Description : " + oeuvre.getDescription());
                System.out.println("Date Ajout : " + oeuvre.getDateAjout());
                System.out.println("Prix de vente : " + oeuvre.getPrixVente());
                System.out.println("Catégorie : " + oeuvre.getCategorie().getNomCategorie());
                System.out.println("Statut : " + oeuvre.getStatus());
                System.out.println("Artiste : " + oeuvre.getArtiste());
                System.out.println("------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des œuvres d'art de la catégorie " + categorie + " : " + e.getMessage());
        }

    }

}

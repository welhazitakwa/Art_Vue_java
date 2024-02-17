package test;
import models.Categorie;
import models.OeuvreArt;
import models.Utilisateur;
import services.categorie.CategorieService;
import services.oeuvreArt.OeuvreArtService;
import services.utilisateur.UtilisateurService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class mainOeuvreArt {

    //MyDataBase d = MyDataBase.getInstance();
    public static void main(String[] args) {

        OeuvreArtService Oa1 = new OeuvreArtService();
        OeuvreArtService oeuvreArtService = new OeuvreArtService();
        Categorie Cs1 = new Categorie(8, "Dessin");
        Categorie Cs2 = new Categorie(5, "Peinture");

        /*
        //--------------------------------------------------AjouterOeuvre----------------------------------------------------------

        // Création d'une instance de service OeuvreArt
        OeuvreArtService oeuvreArtService = new OeuvreArtService();

        // Création d'une instance de service Categorie pour récupérer la catégorie
        CategorieService categorieService = new CategorieService();
        UtilisateurService utilisateurService = new UtilisateurService();

        try {
            // Récupération d'une catégorie existante
            Categorie categorie = categorieService.getCategorieById(5);
            Utilisateur artiste1 = utilisateurService.getUtilisateurById(1);

            // Création d'une instance d'oeuvre d'art avec les détails
            OeuvreArt oeuvreArt = new OeuvreArt();
            oeuvreArt.setImage("chemin/image.jpg");
            oeuvreArt.setTitre("Titre de l'oeuvre d'art");
            oeuvreArt.setDescription("Description de l'oeuvre d'art");
            oeuvreArt.setDateAjout(new Date()); // Date d'ajout actuelle
            oeuvreArt.setPrixVente(100.0f);
            oeuvreArt.setCategorie(categorie); // Utilisation de la catégorie récupérée
            oeuvreArt.setStatus("Disponible");
           oeuvreArt.setArtiste(artiste1);

            // Appel de la méthode pour ajouter l'oeuvre d'art
            oeuvreArtService.AjouterOeuvreArt(oeuvreArt);
            System.out.println("L'oeuvre d'art a été ajoutée avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'oeuvre d'art : " + e.getMessage());
        }

        //-----------------------------------------Modifier oeuvre art---------------------------------------------------------

        try {
            // Récupération d'une catégorie existante
            Categorie categorie = categorieService.getCategorieById(5);
            Utilisateur artiste1 = utilisateurService.getUtilisateurById(1);

            // Création d'une instance d'oeuvre d'art avec les détails
            OeuvreArt oeuvreArt = new OeuvreArt();
            oeuvreArt.setId(8); // Spécifier l'ID de l'oeuvre d'art à modifier
            oeuvreArt.setImage("chemin/nouvelle_image.jpg"); // Nouvelle image
            oeuvreArt.setTitre("Nouveau titre"); // Nouveau titre
            oeuvreArt.setDescription("Nouvelle description"); // Nouvelle description
            oeuvreArt.setDateAjout(new Date()); // Date d'ajout actuelle
            oeuvreArt.setPrixVente(150.0f); // Nouveau prix de vente
            oeuvreArt.setCategorie(categorie); // Utilisation de la catégorie récupérée
            oeuvreArt.setStatus("Disponible"); // Nouveau statut
            oeuvreArt.setArtiste(artiste1);

            // Appel de la méthode pour modifier l'oeuvre d'art
            oeuvreArtService.ModifierOeuvreArt(oeuvreArt);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de l'oeuvre d'art : " + e.getMessage());
        }



        //----------------------------Supprimer oeuvre art----------------------------------------------------------------------


        try {
            // Appel de la méthode pour supprimer une oeuvre d'art avec l'ID 1
            oeuvreArtService.SupprimerOeuvreArt(13);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'oeuvre d'art : " + e.getMessage());
        }


        // ---------------------------------------------------Afficher Oeuvre By Categorie--------------------------------------
        String categorie = "Peinture";

        try {
            List<OeuvreArt> oeuvres = Oa1.getAllOeuvreArtByCategorie(categorie);
            if (oeuvres.isEmpty()) {
                System.out.println("Aucune oeuvre d'art trouvée pour la catégorie : " + categorie);
            } else {
                System.out.println("Oeuvres d'art pour la catégorie : " + categorie);
                for (OeuvreArt oeuvre : oeuvres) {
                    afficherOeuvre(oeuvre);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des oeuvres d'art pour la catégorie " + categorie + " : " + e.getMessage());
        }

    }

    private static void afficherOeuvre(OeuvreArt oeuvre) {
        System.out.println("ID : " + oeuvre.getId());
        System.out.println("Image : " + oeuvre.getImage());
        System.out.println("Titre : " + oeuvre.getTitre());
        System.out.println("Description : " + oeuvre.getDescription());
        System.out.println("Date Ajout : " + oeuvre.getDateAjout());
        System.out.println("Prix de vente : " + oeuvre.getPrixVente());
        System.out.println("Catégorie : " + oeuvre.getCategorie().getNomCategorie());
        System.out.println("Statut : " + oeuvre.getStatus());
        System.out.println("Artiste : " + oeuvre.getArtiste().getNom() + " " + oeuvre.getArtiste().getPrenom());
        System.out.println("------------------------------------");
    }

*/
//-----------------------------------------------Afficher oeuvre By Artiste------------------------------------


     /*   OeuvreArtService oeuvreArtService = new OeuvreArtService();
        int idArtiste = 13; // Remplacez 1 par l'ID de l'artiste existant dans la base de données

        try {
            List<OeuvreArt> oeuvres = oeuvreArtService.getAllOeuvreArtByArtistes(idArtiste);
            if (oeuvres.isEmpty()) {
                System.out.println("Aucune oeuvre d'art trouvée pour l'artiste avec l'ID : " + idArtiste);
            } else {
                System.out.println("Oeuvres d'art pour l'artiste avec l'ID : " + idArtiste);
                for (OeuvreArt oeuvre : oeuvres) {
                    System.out.println("ID : " + oeuvre.getId());
                    System.out.println("Image : " + oeuvre.getImage());
                    System.out.println("Titre : " + oeuvre.getTitre());
                    System.out.println("Description : " + oeuvre.getDescription());
                    System.out.println("Date Ajout : " + oeuvre.getDateAjout());
                    System.out.println("Prix de vente : " + oeuvre.getPrixVente());
                    System.out.println("Catégorie : " + oeuvre.getCategorie().getNomCategorie());
                    System.out.println("Statut : " + oeuvre.getStatus());
                    System.out.println("Artiste : " + oeuvre.getArtiste().getNom() + " " + oeuvre.getArtiste().getPrenom());
                    System.out.println("------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des oeuvres d'art pour l'artiste avec l'ID : " + idArtiste + " : " + e.getMessage());
        }
        */

        //----------------------------Afficher tous les oeuvre d'art-------------------------------------------------------

       /* try {
            // Récupération de toutes les œuvres d'art
            List<OeuvreArt> oeuvres = oeuvreArtService.AfficherOeuvreArt();

            // Vérification si des œuvres d'art ont été trouvées
            if (oeuvres.isEmpty()) {
                System.out.println("Aucune œuvre d'art trouvée.");
            } else {
                System.out.println("Liste des œuvres d'art :");
                // Affichage de chaque œuvre d'art
                for (OeuvreArt oeuvre : oeuvres) {
                    System.out.println("ID : " + oeuvre.getId());
                    System.out.println("Titre : " + oeuvre.getTitre());
                    System.out.println("Description : " + oeuvre.getDescription());
                    System.out.println("Image : " + oeuvre.getImage());
                    System.out.println("Date Ajout : " + oeuvre.getDateAjout());
                    System.out.println("Prix de vente : " + oeuvre.getPrixVente());
                    System.out.println("Statut : " + oeuvre.getStatus());
                    System.out.println("Catégorie : " + oeuvre.getCategorie().getNomCategorie());
                    System.out.println("Artiste : " + oeuvre.getArtiste().getNom() + " " + oeuvre.getArtiste().getPrenom());
                    System.out.println("------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des œuvres d'art : " + e.getMessage());
        } */

        //------------------------------------Afficher Oeuvre d'art By ID---------------------------------------------------

        int idOeuvreArt = 11;

        try {
            OeuvreArt oeuvre = oeuvreArtService.AfficherOeuvreArtById(idOeuvreArt);
            if (oeuvre != null) {
                System.out.println("Informations sur l'oeuvre d'art avec l'ID " + idOeuvreArt + ":");
                System.out.println("ID: " + oeuvre.getId());
                System.out.println("Titre: " + oeuvre.getTitre());
                System.out.println("Description: " + oeuvre.getDescription());
                System.out.println("Image: " + oeuvre.getImage());
                System.out.println("Date Ajout: " + oeuvre.getDateAjout());
                System.out.println("Prix de Vente: " + oeuvre.getPrixVente());
                System.out.println("Catégorie: " + oeuvre.getCategorie().getNomCategorie());
                System.out.println("Artiste: " + oeuvre.getArtiste().getNom() + " " + oeuvre.getArtiste().getPrenom());
                System.out.println("Statut: " + oeuvre.getStatus());
            } else {
                System.out.println("Aucune oeuvre d'art trouvée avec l'ID " + idOeuvreArt);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'oeuvre d'art avec l'ID " + idOeuvreArt + ": " + e.getMessage());
        }

        //-------------------------------------------calculer le nb des oeuvre d'art--------------------------------------
        try {
            int nombreOeuvres = oeuvreArtService.nombreOeuvresArt();
            System.out.println("Nombre total d'œuvres d'art : " + nombreOeuvres);
        } catch (SQLException e) {
            System.out.println("Erreur lors du calcul du nombre d'œuvres d'art : " + e.getMessage());
        }
        // ----------------------------------------calculer nombre Oeuvre d'art selon categorie----------------------------
        CategorieService categorieService = new CategorieService();
        try {
            Categorie categorie = categorieService.getCategorieById(5);
            String nomCategorie = categorie.getNomCategorie(); // Obtenez le nom de la catégorie
            int nombreOeuvres = oeuvreArtService.nombreOeuvresArtParCategorie(nomCategorie);
            System.out.println("Nombre total d'œuvres d'art dans la catégorie " + nomCategorie + " : " + nombreOeuvres);
        } catch (SQLException e) {
            System.out.println("Erreur lors du calcul du nombre d'œuvres d'art par catégorie : " + e.getMessage());
        }

        //------------------------------------Calculer Nombre Oeuvre d'art selon  artiste-------------------------------------
        try {
            int idUtilisateur = 15; // Remplacez 13 par l'ID de l'utilisateur existant dans votre base de données
            int nombreOeuvres = oeuvreArtService.nombreOeuvresArtParArtiste(idUtilisateur);
            System.out.println("Nombre total d'œuvres d'art de l'artiste avec l'ID " + idUtilisateur + " : " + nombreOeuvres);
        } catch (SQLException e) {
            System.out.println("Erreur lors du calcul du nombre d'œuvres d'art par artiste : " + e.getMessage());
        }


    }
}




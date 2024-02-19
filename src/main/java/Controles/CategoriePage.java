package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.categorie.CategorieService;
import models.Categorie;

import java.sql.SQLException;
import java.util.List;

public class CategoriePage {

    @FXML
    private TextField idCategorieField;

    @FXML
    private TextField nomCategorieField;

    @FXML
    private TableView<Categorie> tableCategories;

    private CategorieService categorieService;

    public CategoriePage() {
        categorieService = new CategorieService();
    }

    @FXML
    public void ajouterCategorie(ActionEvent actionEvent) {
        String nomCategorie = nomCategorieField.getText();

        if (!nomCategorie.isEmpty()) {
            try {
                Categorie nouvelleCategorie = new Categorie();
                nouvelleCategorie.setNomCategorie(nomCategorie);
                categorieService.AjouterCategorie(nouvelleCategorie);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("La catégorie " +nomCategorieField.getText()+" a été ajouté avec succés" );
                alert.show();
                // Rafraîchir le tableau des catégories après l'ajout
                afficherCategories();
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer les erreurs d'ajout de catégorie ici
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer le nom de la catégorie.");
            alert.showAndWait();
        }
    }

    // Méthode pour afficher les catégories existantes dans le tableau
    private void afficherCategories() {
        try {
            List<Categorie> categories = categorieService.AfficherCategorie();
            tableCategories.getItems().setAll(categories);
            System.out.println("Catégories affichées avec succès : " + categories.size());
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de récupération des catégories depuis la base de données ici
        }
    }

}

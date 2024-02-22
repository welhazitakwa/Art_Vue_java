package Controles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Categorie;
import services.categorie.CategorieService;

import java.sql.SQLException;
import java.util.List;

public class CategoriePage {

    @FXML
    private TextField nomCategorieField;

    @FXML
    private TableView<Categorie> tableCategories;

    @FXML
    private TableColumn<Categorie, Integer> idColumn;

    @FXML
    private TableColumn<Categorie, String> titreColumn;

    @FXML
    private TableColumn<Categorie, Void> actionColumn; // Nouvelle colonne pour les boutons

    private CategorieService categorieService;

    @FXML
    public void initialize() {
        // Initialisation de categorieService
        categorieService = new CategorieService();

        // Configurer les colonnes du TableView
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));

        // Configurer la colonne "Action"
        actionColumn.setCellFactory(param -> new ButtonCellFactory(this)); // Passer cette instance de CategoriePage

        // Récupérer les catégories depuis la base de données et les afficher dans le tableau
        afficherCategories();
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
                alert.setContentText("La catégorie " + nomCategorieField.getText() + " a été ajoutée avec succès");
                alert.show();
                // Effacer le champ de texte après l'ajout
                nomCategorieField.clear();
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
    public void afficherCategories() {
        try {
            if (categorieService != null) { // Vérifier si categorieService est initialisé
                List<Categorie> categories = categorieService.AfficherCategorie();
                ObservableList<Categorie> categorieObservableList = FXCollections.observableArrayList(categories);
                tableCategories.setItems(categorieObservableList);
                System.out.println("Catégories affichées avec succès : " + categories.size());
            } else {
                System.err.println("categorieService n'est pas initialisé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour ouvrir la boîte de dialogue de modification de catégorie
    public void ouvrirModifierCategorieDialog(Categorie categorie) {
        ModifierCategorieDialog modifierDialog = new ModifierCategorieDialog(categorie, this);
        modifierDialog.showAndWait();
    }
}

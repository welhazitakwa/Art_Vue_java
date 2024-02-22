package Controles;

import Controles.CategoriePage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Categorie;
import services.categorie.CategorieService;

import java.sql.SQLException;

public class ModifierCategorieDialog {

    private final Categorie categorie;
    private final CategoriePage categoriePage;
    private final Stage stage;

    public ModifierCategorieDialog(Categorie categorie, CategoriePage categoriePage) {
        this.categorie = categorie;
        this.categoriePage = categoriePage;

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Modifier la catégorie");

        Label label = new Label("Nom Catégorie:");
        label.getStyleClass().add("dialog-label");

        TextField nomCategorieField = new TextField();
        nomCategorieField.getStyleClass().add("dialog-text-field");
        nomCategorieField.setPromptText("Entrez le nouveau nom de la catégorie");
        nomCategorieField.setText(categorie.getNomCategorie());
        nomCategorieField.setPrefWidth(300); // Définir la largeur souhaitée
        nomCategorieField.setPrefHeight(30); // Définir la hauteur souhaitée

        Button modifierButton = new Button("Modifier");
        modifierButton.getStyleClass().add("dialog-button");
        modifierButton.setOnAction(event -> {
            String nouveauNom = nomCategorieField.getText();
            categorie.setNomCategorie(nouveauNom);
            CategorieService categorieService = new CategorieService();
            try {
                categorieService.ModifierCategorie(categorie);
                categoriePage.afficherCategories();
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de la modification de la catégorie.");
                alert.showAndWait();
            }
            stage.close();
        });

        HBox buttonBox = new HBox(modifierButton);
        buttonBox.setAlignment(Pos.CENTER); // Alignement du bouton au centre horizontalement

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(label, nomCategorieField, buttonBox);
        vbox.getStyleClass().add("dialog-vbox");
        VBox.setMargin(nomCategorieField, new Insets(0, 50, 30, 10));

        Scene scene = new Scene(vbox);
        scene.getStylesheets().add(getClass().getResource("/css/dialog_styles.css").toExternalForm());

        stage.setScene(scene);
    }

    public void showAndWait() {
        stage.showAndWait();
    }

    public void show() {
        stage.show();
    }
}

package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Categorie;
import models.OeuvreArt;
import services.categorie.CategorieService;
import services.oeuvreArt.OeuvreArtService;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModifierOeuvre {
    @FXML
    private TextField imageField;
    @FXML
    private Button BtnModifier;
    @FXML
    private Button btnChoisirFile;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField prixField;
    @FXML
    private TextField titreField;

    @FXML
    private ComboBox<String> categorieComboBox;


    private CategorieService categorieService;
    private int id;

    @FXML
    void initialize() {
        categorieService = new CategorieService();

        try {
            List<Categorie> categories = categorieService.AfficherCategorie();
            for (Categorie categorie : categories) {
                categorieComboBox.getItems().add(categorie.getNomCategorie());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void choose_file(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            imageField.setText(selectedFile.toURI().toString());
        } else {
            System.out.println("Aucun fichier sélectionné.");
        }
    }

    public void updateFields(OeuvreArt oeuvreArt) {
        id = oeuvreArt.getId();
        titreField.setText(oeuvreArt.getTitre());
        categorieComboBox.setValue(oeuvreArt.getCategorie().getNomCategorie());
        descriptionField.setText(oeuvreArt.getDescription());
        prixField.setText(String.valueOf(oeuvreArt.getPrixVente()));
        imageField.setText(oeuvreArt.getImage());

    }



    public void EnvoyerDataOeuvre(OeuvreArt oeuvreArt) {

        updateFields(oeuvreArt);
    }

    public void ModifierOeuvreArt(ActionEvent actionEvent) {
        if (!validateFields()) {
            return;
        }
        String titre = titreField.getText();
        String categorieNom = categorieComboBox.getValue();
        String description = descriptionField.getText();
        String prixText = prixField.getText();
        String image = imageField.getText();

        CategorieService categorieService = new CategorieService();
        Categorie categorieObj;
        try {
            categorieObj = categorieService.getCategorieByNom(categorieNom);
        } catch (SQLException e) {
            e.printStackTrace();
            afficherAlerte("Erreur lors de la récupération de la catégorie.");
            return;
        }

        OeuvreArtService oeuvre1 = new OeuvreArtService();
        try {
            oeuvre1.ModifierOeuvreArt(new OeuvreArt(
                    id, imageField.getText(), titreField.getText(), descriptionField.getText(), Float.parseFloat(prixField.getText()), categorieObj));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification réussie");
            alert.setHeaderText(null);
            alert.setContentText("L'œuvre d'art a été modifiée avec succès.");
            alert.showAndWait();

            // Charger la page OeuvreArtArtiste.fxml après la fermeture de l'alerte
            loadNewPage("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml", actionEvent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void afficherAlerte(String message) {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("Erreur");
        dialog.setHeaderText(null);
        dialog.setContentText(message);
        dialog.showAndWait();
    }

    private boolean validateFields() {
        if (titreField.getText().isEmpty() || descriptionField.getText().isEmpty() || categorieComboBox.getValue() == null ||
                prixField.getText().isEmpty() || imageField.getText().isEmpty()) {
            showAlert("Erreur de saisie", "Veuillez remplir tous les champs.");
            return false;
        }

        try {
            Float.parseFloat(prixField.getText());
        } catch (NumberFormatException e) {
            showAlert("Erreur de saisie", "Le champ Prix doit contenir un nombre valide.");
            return false;
        }

        if (!isValidImagePath(imageField.getText())) {
            showAlert("Erreur de saisie", "Le chemin d'image est invalide.");
            return false;
        }

        return true;
    }
    private boolean isValidImagePath(String imagePath) {
        return imagePath.startsWith("file:/") && (imagePath.endsWith(".jpg") || imagePath.endsWith(".png") || imagePath.endsWith(".gif"));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        loadNewPage("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml", event);
    }

    @FXML
    public void To_Accueil(ActionEvent event) {
        loadNewPage("/fxml/fxmlArtiste/AcceuilArtiste.fxml", event);
    }

    private void loadNewPage(String fxmlFilePath, ActionEvent event) {
        try {
            // Charger la nouvelle page depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent newPage = loader.load();
            // Accéder au stage actuel à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Remplacer la scène actuelle par la nouvelle scène
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import models.Categorie;
import models.OeuvreArt;
import models.Utilisateur;
import services.categorie.CategorieService;
import services.oeuvreArt.OeuvreArtService;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterOeuvre {

    @FXML
    private TextField titreField;

    @FXML
    private TextField descriptionField;

    @FXML
    private ComboBox<String> categorieComboBox;

    @FXML
    private TextField prixField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField imageField;

    @FXML
    private Button BtnAjouter;

    private CategorieService categorieService;

    @FXML
    void initialize() {
        datePicker.setValue(LocalDate.now());
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

    public void AjouterOeuvre(ActionEvent actionEvent) {
        if (!validateFields()) {
            return;
        }

        String titre = titreField.getText();
        String description = descriptionField.getText();
        String categorieNom = categorieComboBox.getValue();
        float prixVente = Float.parseFloat(prixField.getText());
        String image = imageField.getText();
        LocalDate localDate = datePicker.getValue();
        Date dateAjout = java.sql.Date.valueOf(localDate);

        try {
            CategorieService categorieService = new CategorieService();
            Categorie categorieObj = categorieService.getCategorieByNom(categorieNom);

            Utilisateur artiste = new Utilisateur();
            artiste.setId(14);

            OeuvreArt oeuvreArt = new OeuvreArt(image, titre, description, dateAjout, prixVente, categorieObj, "disponible", artiste);

            OeuvreArtService oeuvreArtService = new OeuvreArtService();
            oeuvreArtService.AjouterOeuvreArt(oeuvreArt);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText(null);
            alert.setContentText("L'oeuvre d'art a été ajoutée avec succès.");
            alert.showAndWait();
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

    private boolean validateFields() {
        if (titreField.getText().isEmpty() || descriptionField.getText().isEmpty() || categorieComboBox.getValue() == null ||
                prixField.getText().isEmpty() || imageField.getText().isEmpty() || datePicker.getValue() == null) {
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isValidImagePath(String imagePath) {
        return imagePath.startsWith("file:/") && (imagePath.endsWith(".jpg") || imagePath.endsWith(".png") || imagePath.endsWith(".gif"));
    }
}

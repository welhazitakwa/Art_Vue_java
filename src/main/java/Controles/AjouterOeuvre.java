package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Categorie;
import models.OeuvreArt;
import models.Utilisateur;
import org.controlsfx.control.Notifications;
import services.categorie.CategorieService;
import services.oeuvreArt.OeuvreArtService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int idArtiste;

    public void setParametre(int idArtiste) {
        this.idArtiste = idArtiste;
        System.out.println("ID de l'artiste dans page AjouterOeuvre : " + idArtiste);

    }

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
        // Déclaration de event à un niveau plus élevé
        ActionEvent event = actionEvent;
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
            artiste.setId(idArtiste);

            OeuvreArt oeuvreArt = new OeuvreArt(image, titre, description, dateAjout, prixVente, categorieObj, "disponible", artiste);

            OeuvreArtService oeuvreArtService = new OeuvreArtService();
            oeuvreArtService.AjouterOeuvreArt(oeuvreArt);

            Image image1 = new Image("/image/succes.png");
            ImageView imageView = new ImageView(image1);
            imageView.setFitWidth(40); // Réglez la largeur souhaitée
            imageView.setFitHeight(40);
            Notifications notifications = Notifications.create();
            notifications.graphic(imageView); // Utilisez l'ImageView avec la taille ajustée
            notifications.text("L'oeuvre d'art a été ajoutée avec succès.");
            notifications.title("Success Message");
            notifications.hideAfter(Duration.seconds(4.0));
            notifications.show();

            // Charger la page OeuvreArtArtiste.fxml après la fermeture de l'alerte
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml"));
                Parent newPage = loader.load();
                OeuvresPageArtiste controller = loader.getController();
                controller.setParametre(idArtiste); // Passer l'ID de l'artiste à la nouvelle page
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(newPage);
                stage.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        loadNewPage("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml", event);
    }

    @FXML
    public void To_Accueil(ActionEvent event) {

        loadNewPage("/fxml/fxmlArtiste/AcceuilArtiste.fxml", event);
    }

    public void To_Apropos(ActionEvent event) {
        loadNewPage("/fxml/fxmlArtiste/AproposArtiste.fxml", event);

    }


}

package Controles;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Categorie;
import models.OeuvreArt;
import org.controlsfx.control.Notifications;
import services.categorie.CategorieService;
import services.oeuvreArt.OeuvreArtService;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
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
    private int idArtiste;

    public void setParametre(int idArtiste) {
        this.idArtiste = idArtiste;
        System.out.println("ID de l'artiste dans page Modifier Oeuvre : " + idArtiste);
    }

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
            // Générer un nom de fichier unique
            String uniqueFileName = UUID.randomUUID().toString() + "_" + selectedFile.getName();

            // Chemin de destination
            String destinationPath = "C:/Users/LENOVO/Desktop/Esprit-2024/PIDEV/Partie_Symfony/Art_Vue_Symfony/public/oeuvre/";

            try {
                // Copier le fichier sélectionné dans le répertoire de destination
                Files.copy(selectedFile.toPath(), Path.of(destinationPath + uniqueFileName), StandardCopyOption.REPLACE_EXISTING);

                // Enregistrer uniquement le nom de l'image dans la base de données
                String imageName = uniqueFileName;

                // Tu peux faire ce que tu veux avec le nom de l'image ici

                // Afficher le nom de l'image dans le champ texte
                imageField.setText(imageName);
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'erreur
            }
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
        // Déclaration de event à un niveau plus élevé
        ActionEvent event = actionEvent;

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

            Image image1 = new Image("/image/succes.png");
            ImageView imageView = new ImageView(image1);
            imageView.setFitWidth(40); // Réglez la largeur souhaitée
            imageView.setFitHeight(40);
            Notifications notifications = Notifications.create();
            notifications.graphic(imageView); // Utilisez l'ImageView avec la taille ajustée
            notifications.text("L'œuvre d'art a été modifiée avec succès.");
            notifications.title("Modification réussie");
            notifications.hideAfter(Duration.seconds(4.0));
            notifications.show();

            sendSMSNotification("Nouvelle mise à jour l'œuvre d'art : " + titre);

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



        return true;
    }

    private void sendSMSNotification(String message) {
        String ACCOUNT_SID = "ACdd5e61831be43b8a8a0f7a636fd661e9";
        String AUTH_TOKEN = "3dbfc4ca8da735662ba37b0a5eb66ee7";
        String TWILIO_PHONE_NUMBER = "+1 862 267 0495";
        String USER_PHONE_NUMBER = "+21658044443";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(
                        new PhoneNumber(USER_PHONE_NUMBER),
                        new PhoneNumber(TWILIO_PHONE_NUMBER),
                        message)
                .create();
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml"));
            Parent newPage = loader.load();
            OeuvresPageArtiste oeuvrePageArtiste = loader.getController();
            oeuvrePageArtiste.setParametre(idArtiste); // Passage de l'ID de l'artiste à la page OeuvrePageArtiste
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void To_Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/AcceuilArtiste.fxml"));
            Parent newPage = loader.load();
            AcceuilArtiste acceuilArtiste = loader.getController();
            acceuilArtiste.setParametre(idArtiste);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void To_Aprops(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/AproposArtiste.fxml"));
            Parent newPage = loader.load();
            AproposArtiste aproposArtiste = loader.getController();
            aproposArtiste.setParametre(idArtiste);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

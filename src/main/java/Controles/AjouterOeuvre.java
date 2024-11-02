package Controles;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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
import okhttp3.*;
import org.controlsfx.control.Notifications;
import services.categorie.CategorieService;
import services.oeuvreArt.OeuvreArtService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
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
            // Vérifier les mots inappropriés dans la description
            if (containsBadWords(description)) {
                showAlert("Erreur", "La description contient des mots inappropriés.");
                return;
            }
            CategorieService categorieService = new CategorieService();
            Categorie categorieObj = categorieService.getCategorieByNom(categorieNom);

            Utilisateur artiste = new Utilisateur();
            artiste.setId(idArtiste);

            OeuvreArt oeuvreArt = new OeuvreArt(image, titre, description, dateAjout, prixVente, categorieObj, "disponible", artiste);

            OeuvreArtService oeuvreArtService = new OeuvreArtService();
            oeuvreArtService.AjouterOeuvreArt(oeuvreArt);

            Image image1 = new Image("/image/succes.png");
            ImageView imageView = new ImageView(image1);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            Notifications notifications = Notifications.create();
            notifications.graphic(imageView);
            notifications.text("L'oeuvre d'art a été ajoutée avec succès.");
            notifications.title("Success Message");
            notifications.hideAfter(Duration.seconds(4.0));
            notifications.show();

            // Envoi d'un SMS de notification
            sendSMSNotification("Nouvelle œuvre d'art ajoutée : " + titre);

            // Charger la page OeuvreArtArtiste.fxml après la fermeture de l'alerte
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml"));
                Parent newPage = loader.load();
                OeuvresPageArtiste controller = loader.getController();
                controller.setParametre(idArtiste);
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



    private boolean containsBadWords(String text) {
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .callTimeout(10, TimeUnit.SECONDS) // Augmenter le délai d'attente à 30 secondes
                    .build();

            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, text);

            Request request = new Request.Builder()
                    .url("https://api.apilayer.com/bad_words?censor_character=*")
                    .addHeader("apikey", "G6KGVSYGI0uLnaDjGOlw6uzJEwbGgE6a")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            // Vérifier si la réponse contient des mots inappropriés
            return responseBody.contains("badwords");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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


        // Vérifier les mots inappropriés dans la description
        if (containsBadWords(descriptionField.getText())) {
            showAlert("Erreur", "La description contient des mots inappropriés.");
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



    private void sendSMSNotification(String message) {
       /* String ACCOUNT_SID = "ACdd5e61831be43b8a8a0f7a636fd661e9";
        String AUTH_TOKEN = "3dbfc4ca8da735662ba37b0a5eb66ee7";
        String TWILIO_PHONE_NUMBER = "+1 862 267 0495";
        String USER_PHONE_NUMBER = "+21658044443";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(
                        new PhoneNumber(USER_PHONE_NUMBER),
                        new PhoneNumber(TWILIO_PHONE_NUMBER),
                        message)
                .create();*/
    }

    private void loadNewPage(String fxmlFilePath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent newPage = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

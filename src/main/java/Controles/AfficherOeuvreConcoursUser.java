package Controles;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.OeuvreArt;
import services.concours.OeuvreConcoursService;
import services.vote.voteServices;

import java.util.List;

public class AfficherOeuvreConcoursUser {

    @FXML
    private VBox vboxDetails;
    @FXML
    private Button consultermesvotes;


    @FXML
    private ScrollPane scrollPane;

  

 
    @FXML
    public void initialize() {
        assert vboxDetails != null : "fx:id=\"vboxDetails\" was not injected: check your FXML file 'AfficherOeuvreConcoursUser.fxml'.";
    }
    int idUser =1;

    @FXML
    public void initialiserDetailsOeuvres(List<OeuvreArt> oeuvres) {
        vboxDetails.getChildren().clear();

        // Vous pouvez ajuster ces dimensions selon vos besoins
        double imageViewWidth = 300;
        double imageViewHeight = 300;

        oeuvres.forEach(oeuvre -> {
            Label labelTitre = new Label("Titre: " + oeuvre.getTitre());
            Label labelArtiste = new Label("Artiste: " + oeuvre.getArtiste());

            String imagePath = oeuvre.getImage();
            File file = new File(imagePath);
            URL imageUrl;
            try {
                imageUrl = file.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            ImageView imageView = new ImageView(new Image(imageUrl.toString()));
            imageView.setFitWidth(imageViewWidth);
            imageView.setFitHeight(imageViewHeight);

            // Utilisez la ComboBox au lieu de la HBox
            ComboBox<Integer> ratingComboBox = createRatingComboBox(oeuvre);

            // Add the confirmation button
            Button confirmerButton = new Button("Confirmer");
            confirmerButton.setOnAction(event -> confirmerVote(oeuvre, idUser));

            // Utilisez la ComboBox au lieu de la HBox
            VBox detailsOeuvre = new VBox(
                    labelTitre,
                    labelArtiste,
                    imageView,
                    ratingComboBox,
                    confirmerButton
            );

            // Vous pouvez ajouter un espacement entre les éléments si nécessaire
            detailsOeuvre.setSpacing(10);

            vboxDetails.getChildren().add(detailsOeuvre);
        });

        // Ajoutez la VBox à un ScrollPane pour gérer le défilement si nécessaire
        scrollPane.setContent(vboxDetails);
    }


    private ComboBox<Integer> createRatingComboBox(OeuvreArt oeuvre) {
        ComboBox<Integer> ratingComboBox = new ComboBox<>();
        ratingComboBox.setId(getComboBoxId(oeuvre));

        // Ajoutez les valeurs possibles à la ComboBox
        for (int i = 0; i <= 5; i++) {
            ratingComboBox.getItems().add(i);
        }

        // Sélectionnez la première valeur par défaut
        ratingComboBox.getSelectionModel().selectFirst();

        return ratingComboBox;
    }

    private String getComboBoxId(OeuvreArt oeuvre) {
        return "comboBox_" + oeuvre.getId();
    }

    private int getSelectedRating(OeuvreArt oeuvre) {
        // Retrieve the ComboBox using lookup
        ComboBox<Integer> ratingComboBox = (ComboBox<Integer>) vboxDetails.lookup("#" + getComboBoxId(oeuvre));

        // Retournez la valeur sélectionnée
        return ratingComboBox.getValue();
    }

    private void confirmerVote(OeuvreArt oeuvre, int idUser) {
        int selectedRating = getSelectedRating(oeuvre);

        // Ajoutez le vote à la base de données
        int idOeuvre = oeuvre.getId();
        OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();
        int idConcours = oeuvreConcoursService.getConcoursIdByOeuvreId(idOeuvre);

        voteServices vS = new voteServices();
        vS.enregistrerVote(idConcours, idOeuvre, idUser, selectedRating);

        // Reste de votre logique pour confirmer le vote
        System.out.println("Vote confirmé avec succès pour l'oeuvre: " + oeuvre.getTitre() + ", Note: " + selectedRating);
    }

    public void consultermesvotes(javafx.event.ActionEvent actionEvent) {
        try {
            // Chargez le fichier FXML pour la page VoteDetails
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VoteDetails.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur associé à la vue chargée
            VoteDetails voteDetailsController = loader.getController();

            // Configurez le contrôleur au besoin (vous pouvez avoir des méthodes pour initialiser des données, etc.)
            // voteDetailsController.initializeData(...);

            // Créez une nouvelle scène avec la page VoteDetails
            Scene scene = new Scene(root);

            // Obtenez la fenêtre (stage) actuelle à partir de n'importe quel nœud dans votre scène
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Définissez la nouvelle scène sur le stage
            stage.setScene(scene);
            stage.setTitle("Vote Details"); // Titre de la nouvelle fenêtre

            // Montrez la nouvelle fenêtre
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }
    }


}

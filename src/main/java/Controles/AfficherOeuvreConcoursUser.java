package Controles;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
    private int idClient ;

    public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de l'client connecté page Oeuvreuser : " + idClient);
    }

   // int idUser = 1;
    private int idConcours;

    @FXML
    public void initialize() {
        assert vboxDetails != null : "fx:id=\"vboxDetails\" was not injected: check your FXML file 'AfficherOeuvreUser.fxml'.";
    }

    private RatingBar createRatingBar(OeuvreArt oeuvre) {
        RatingBar ratingBar = new RatingBar();
        ratingBar.setId("ratingBar_" + oeuvre.getId());
        return ratingBar;
    }

    @FXML
    public void initialiserDetailsOeuvres(List<OeuvreArt> oeuvres, int concoursId) {
        vboxDetails.getChildren().clear();

        double imageViewWidth = 300;
        double imageViewHeight = 300;

        oeuvres.forEach(oeuvre -> {
            Label labelTitre = new Label("Titre: " + oeuvre.getTitre());
            Label labelArtiste = new Label("Artiste: " + oeuvre.getArtiste());

            File file = new File(oeuvre.getImage());
            URL imageUrl;
            try {
                imageUrl = file.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            ImageView imageView = new ImageView(new Image(imageUrl.toString()));
            imageView.setFitWidth(imageViewWidth);
            imageView.setFitHeight(imageViewHeight);

            RatingBar ratingBar = createRatingBar(oeuvre);
            Button confirmerButton = new Button("Confirmer");
            confirmerButton.setOnAction(event -> confirmerVote(oeuvre, idClient, concoursId));

            VBox detailsOeuvre = new VBox(
                    labelTitre,
                    labelArtiste,
                    imageView,
                    ratingBar,
                    confirmerButton
            );

            detailsOeuvre.setSpacing(10);

            vboxDetails.getChildren().add(detailsOeuvre);
        });

        scrollPane.setContent(vboxDetails);
    }

    private int getSelectedRating(OeuvreArt oeuvre) {
        // Utilisez la méthode getRating de RatingBar
        RatingBar ratingBar = (RatingBar) vboxDetails.lookup("#ratingBar_" + oeuvre.getId());
        return ratingBar.getRating();
    }

    private void confirmerVote(OeuvreArt oeuvre, int idUser, int idConcours) {
        int selectedRating = getSelectedRating(oeuvre);

        // Ajoutez le vote à la base de données
        int idOeuvre = oeuvre.getId();

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
            voteDetailsController.setParametre(idClient);


            // Configurez le contrôleur au besoin (vous pouvez avoir des méthodes pour initialiser des données, etc.)
            voteDetailsController.initialize();

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

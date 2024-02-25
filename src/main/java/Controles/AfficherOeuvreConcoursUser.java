package Controles;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.OeuvreArt;
import services.concours.OeuvreConcoursService;
import services.vote.voteServices;

public class AfficherOeuvreConcoursUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox vboxDetails;



    @FXML
    void initialize() {
        assert vboxDetails != null : "fx:id=\"vboxDetails\" was not injected: check your FXML file 'AfficherOeuvreConcoursUser.fxml'.";

    }
    @FXML
    public void initialiserDetailsOeuvres(List<OeuvreArt> oeuvres) {
        // Supprimez tous les éléments existants de la VBox
        vboxDetails.getChildren().clear();

        // Ajoutez les détails de chaque œuvre à la VBox
        oeuvres.forEach(oeuvre -> {
            Label labelTitre = new Label("Titre: " + oeuvre.getTitre());
            Label labelArtiste = new Label("Artiste: " + oeuvre.getArtiste());

            String imagePath = oeuvre.getImage();

// Créez une URL à partir du chemin du fichier
            File file = new File(imagePath);
            URL imageUrl = null;
            try {
                imageUrl = file.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

// Créez une ImageView avec l'URL de l'image
            ImageView imageView = new ImageView(new Image(imageUrl.toString()));

            // Créez une HBox pour les étoiles
            HBox starRating = new HBox();

            for (int i = 0; i < 5; i++) {
                final int index = i; // Créez une variable finale pour capturer la valeur de i
                Button starButton = new Button("★");
                starButton.setOnAction(event -> handleStarSelection(oeuvre, index + 1));
                starRating.getChildren().add(starButton);
            }

            VBox detailsOeuvre = new VBox(
                    labelTitre,
                    labelArtiste,
                    imageView,
                    starRating
            );

            // Ajoutez chaque VBox de détails d'œuvre à la VBox principale
            vboxDetails.getChildren().add(detailsOeuvre);
        });
    }

    // Méthode pour gérer la sélection des étoiles
    private void handleStarSelection(OeuvreArt oeuvre, int selectedStars) {
        // Ici, vous pouvez implémenter la logique pour enregistrer la note dans la table Vote
        // Supposons que vous ayez une classe de service pour gérer les votes, par exemple VoteService

        voteServices voteService = new voteServices();

        // Supposons que vous avez besoin de l'id du concours et de l'utilisateur actuel pour enregistrer le vote
        OeuvreConcoursService oeuvreConcoursService =new OeuvreConcoursService();
        int concoursId = oeuvreConcoursService.getConcoursIdByOeuvreId(oeuvre.getId()); // Obtenez l'id du concours associé à l'œuvre
        int userId = getCurrentUserId(); // Obtenez l'id de l'utilisateur actuel


        // Enregistrez le vote dans la base de données en utilisant le service VoteService
        boolean voteEnregistre = voteService.enregistrerVote(concoursId, userId, selectedStars);

        if (voteEnregistre) {
            System.out.println("Vote enregistré avec succès pour l'oeuvre: " + oeuvre.getTitre() + ", Note: " + selectedStars);
            // Vous pouvez également mettre à jour l'interface utilisateur pour refléter la nouvelle note ou fournir un feedback à l'utilisateur
        } else {
            System.out.println("Erreur lors de l'enregistrement du vote.");
            // Gérer l'erreur ou fournir un feedback à l'utilisateur en cas d'échec de l'enregistrement du vote
        }
    }

    // Méthode factice pour obtenir l'id de l'utilisateur actuel
    private int getCurrentUserId() {
        // Implémentez la logique pour obtenir l'id de l'utilisateur actuel
        // Cette méthode peut dépendre de la manière dont vous gérez les utilisateurs dans votre application
        return 123; // Par exemple, retourne une valeur factice pour l'exemple
    }

}

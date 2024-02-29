package Controles;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Vote;
import services.vote.voteServices;

public class VoteDetails {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxVotes;

    @FXML
    void initialize() {
        // Initialisez la VBox
        vboxVotes = new VBox();
        vboxVotes.setAlignment(Pos.CENTER);
        vboxVotes.setSpacing(20);

        // Initialisez la ScrollPane avec la VBox comme contenu
        scrollPane.setContent(vboxVotes);

        afficherVotesUtilisateur(1);
    }

    private VBox createVoteBox(Vote vote) {
        VBox voteBox = new VBox();
        voteBox.getStyleClass().add("vote-box"); // Ajoutez des styles CSS si nécessaire

        // Utilisez un GridPane pour organiser les éléments en deux colonnes
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Espace horizontal entre les colonnes

        Label labelTitre = new Label("Titre: " + vote.getTitreOeuvre());
        Label labelNote = new Label("Note: " + vote.getNote());

        // Ajoutez le code pour charger et afficher l'image de l'œuvre
        String imagePath = vote.getImageOeuvre();
        File file = new File(imagePath);
        URL imageUrl;
        try {
            imageUrl = file.toURI().toURL();
            ImageView imageView = new ImageView(new Image(imageUrl.toString()));
            imageView.setFitWidth(300); // Ajustez la largeur selon vos besoins
            imageView.setPreserveRatio(true);

            // Ajoutez les éléments au GridPane
            gridPane.add(labelTitre, 0, 0);
            gridPane.add(labelNote, 1, 0);
            gridPane.add(imageView, 0, 1, 2, 1); // Image sur deux colonnes

            // Centrez les éléments dans le GridPane
            GridPane.setHalignment(labelTitre, HPos.CENTER);
            GridPane.setHalignment(labelNote, HPos.CENTER);
            GridPane.setHalignment(imageView, HPos.CENTER);

            // Ajoutez le GridPane et les boutons au VBox
            voteBox.getChildren().addAll(gridPane, createButtonBar(vote));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return voteBox;
    }

    private VBox createButtonBar(Vote vote) {
        VBox buttonBar = new VBox();
        buttonBar.setSpacing(20);
        Button deleteButton = new Button("Supprimer");
        deleteButton.setOnAction(event -> handleDeleteVote(vote));

        Button editButton = new Button("Modifier");
        editButton.setOnAction(event -> handleEditVote(vote));

        buttonBar.getChildren().addAll(deleteButton, editButton);
        return buttonBar;
    }

    private void handleDeleteVote(Vote vote) {
        // Ajoutez la logique pour supprimer le vote de la base de données et de l'interface utilisateur
        voteServices voteService = new voteServices();

        // Supprimez le vote de la base de données en utilisant son ID
        boolean deleted = voteService.deleteVote(vote.getId());

        if (deleted) {
            // Si la suppression réussit, mettez à jour l'interface utilisateur
            vboxVotes.getChildren().clear(); // Effacez le conteneur de votes
            afficherVotesUtilisateur(1); // Réaffichez les votes mis à jour
        } else {
            // Gérez le cas où la suppression échoue (affichez un message d'erreur, etc.)
            System.out.println("La suppression du vote a échoué.");
        }
    }

    private void handleEditVote(Vote vote) {
        // Ajoutez la logique pour modifier le vote dans la base de données et mettre à jour l'interface utilisateur
    }

    private void afficherVotesUtilisateur(int userId) {
        voteServices voteService = new voteServices();
        List<Vote> votes = voteService.getVotesByUser(userId);

        // Ajoutez de l'espacement vertical entre les votes
        vboxVotes.setSpacing(20);

        // Utilisez une boucle pour afficher trois votes par ligne
        for (int i = 0; i < votes.size(); i += 3) {
            HBox voteRow = new HBox();
            voteRow.setSpacing(10); // Espace horizontal entre les votes

            // Ajoutez de l'espacement horizontal entre les votes dans une ligne
            voteRow.setSpacing(20);

            // Ajoutez les trois votes à la ligne
            for (int j = 0; j < 3 && i + j < votes.size(); j++) {
                Vote vote = votes.get(i + j);
                VBox voteBox = createVoteBox(vote);
                voteRow.getChildren().add(voteBox);
            }

            // Ajoutez la ligne de votes au conteneur principal
            vboxVotes.getChildren().add(voteRow);
        }
    }
}

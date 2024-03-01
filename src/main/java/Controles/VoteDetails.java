package Controles;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        private ScrollPane scrollPane;

        @FXML
        private VBox vboxVotes;

        private Map<Vote, VBox> voteBoxMap = new HashMap<>();
     private int idClient;

    public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de l'client connecté page votedetails : " + idClient);
    }

    @FXML
        void initialize() {


            // Initialisez la VBox
            vboxVotes = new VBox();
            vboxVotes.setSpacing(20);

            // Initialisez la ScrollPane avec la VBox comme contenu
            scrollPane.setContent(vboxVotes);

            afficherVotesUtilisateur(idClient);
        }

        private VBox getOrCreateVoteBox(Vote vote) {
            if (voteBoxMap.containsKey(vote)) {
                return voteBoxMap.get(vote);
            } else {
                VBox voteBox = createVoteBox(vote);
                voteBoxMap.put(vote, voteBox);
                return voteBox;
            }
        }

        private VBox createVoteBox(Vote vote) {
            VBox voteBox = new VBox();
            voteBox.getStyleClass().add("vote-box");

            // Utilisez un GridPane pour organiser les éléments en deux colonnes
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);

            Label labelTitre = new Label("Titre: " + vote.getTitreOeuvre());
            Label labelNote = new Label("Note: " + vote.getNote());
            Label labeltitreconcours = new Label("Concours: " + vote.getTitreConcours());

            // Ajoutez le code pour charger et afficher l'image de l'œuvre
            String imagePath = vote.getImageOeuvre();
            File file = new File(imagePath);
            URL imageUrl;
            try {
                imageUrl = file.toURI().toURL();
                ImageView imageView = new ImageView(new Image(imageUrl.toString()));
                imageView.setFitWidth(300);
                imageView.setFitHeight(300);
              //  imageView.setPreserveRatio(true);

                // Ajoutez les éléments au GridPane
                gridPane.add(labeltitreconcours, 0, 0);
                gridPane.add(labelTitre, 0, 1);
                gridPane.add(labelNote, 0, 2);
                gridPane.add(imageView, 0, 3, 1, 1);

                // Centrez les éléments dans le GridPane
                GridPane.setHalignment(labeltitreconcours, HPos.CENTER);
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
        voteServices voteService = new voteServices();
        try {
            boolean deleted = voteService.deleteVote(vote.getId());
            if (deleted) {
                // Supprimer le voteBox de la VBox
                VBox voteBox = voteBoxMap.remove(vote);
                vboxVotes.getChildren().remove(voteBox);

                // Mise à jour de l'affichage
                afficherVotesUtilisateur(idClient);
            } else {
                System.out.println("La suppression du vote a échoué.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la suppression du vote : " + e.getMessage());
        }
    }



    private void handleEditVote(Vote vote) {
            // Ajoutez la logique pour modifier le vote dans la base de données et mettre à jour l'interface utilisateur
        }

    private void afficherVotesUtilisateur(int idClient) {
        voteServices voteService = new voteServices();
        List<Vote> votes = voteService.getVotesUtilisateur(idClient);

        // Effacer tous les enfants de vboxVotes
        vboxVotes.getChildren().clear();

        vboxVotes.setSpacing(20);

        for (int i = 0; i < votes.size(); i += 3) {
            HBox voteRow = new HBox();
            voteRow.setSpacing(10);

            for (int j = 0; j < 3 && i + j < votes.size(); j++) {
                Vote vote = votes.get(i + j);
                VBox voteBox = getOrCreateVoteBox(vote);
                voteRow.getChildren().add(voteBox);
            }

            vboxVotes.getChildren().add(voteRow);
        }
    }

}




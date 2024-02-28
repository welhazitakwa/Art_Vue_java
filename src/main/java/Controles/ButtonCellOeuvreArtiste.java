package Controles;

import Controles.ModifierOeuvre;
import Controles.OeuvresPageArtiste;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Categorie;
import models.OeuvreArt;
import services.oeuvreArt.OeuvreArtService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ButtonCellOeuvreArtiste extends TableCell<OeuvreArt, Void> {

   /* private final Button modifierButton = new Button();
    private final Button deleteButton = new Button();
    private final OeuvresPageArtiste oeuvresPageArtiste;

    public ButtonCellOeuvreArtiste(OeuvresPageArtiste oeuvresPageArtiste) {
        this.oeuvresPageArtiste = oeuvresPageArtiste;

        // Charger les images pour les boutons
        Image deleteImage = new Image(getClass().getResourceAsStream("/image/delete-100-128.png"));
        Image modifierImage = new Image(getClass().getResourceAsStream("/image/modify-7-128.png"));

        // Créer les ImageView pour les images chargées
        ImageView deleteImageView = new ImageView(deleteImage);
        ImageView modifierImageView = new ImageView(modifierImage);

        // Ajuster la taille des images
        deleteImageView.setFitWidth(16);
        deleteImageView.setFitHeight(16);
        modifierImageView.setFitWidth(16);
        modifierImageView.setFitHeight(16);

        // Ajouter les ImageView aux boutons
        deleteButton.setGraphic(deleteImageView);
        modifierButton.setGraphic(modifierImageView);

        // Ajouter des styles aux boutons
        deleteButton.getStyleClass().add("button");
        modifierButton.getStyleClass().add("button");

        // Ajouter des gestionnaires d'événements pour les boutons

        deleteButton.setOnAction(event -> {
            OeuvreArt oeuvreArt = getTableView().getItems().get(getIndex());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette oeuvre d'art ?");
            alert.setContentText("L'oeuvre d'art \"" + oeuvreArt.getTitre() + "\" sera définitivement supprimée.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    OeuvreArtService oeuvreArtService = new OeuvreArtService();
                    oeuvreArtService.SupprimerOeuvreArt(oeuvreArt.getId());
                    // Rafraîchir le TableView après la suppression
                    getTableView().getItems().remove(oeuvreArt);
                    System.out.println("Oeuvre d'art supprimée avec succès !");
                    // Rafraîchir la liste des oeuvres d'art après la suppression
                    oeuvresPageArtiste.afficherOeuvreArt();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gérer les erreurs de suppression ici
                }
            }
        });

        modifierButton.setOnAction(event -> {
            try {
                OeuvreArt oeuvreArt = getTableView().getItems().get(getIndex());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/ModifierOeuvre.fxml"));
                Parent root = loader.load();
                ModifierOeuvre controller = loader.getController();
                controller.EnvoyerDataOeuvre(oeuvreArt);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            // Centrer les boutons dans la cellule en utilisant HBox avec alignement au centre
            HBox buttonsContainer = new HBox(deleteButton, modifierButton);
            buttonsContainer.setAlignment(javafx.geometry.Pos.CENTER);
            setGraphic(buttonsContainer);
        }
    }*/
}

package Controles;

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
import models.OeuvreArt;
import services.oeuvreArt.OeuvreArtService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ButtonCellOeuvre extends TableCell<OeuvreArt, Void> {

    private final Button detailButton = new Button();
    private final Button deleteButton = new Button();
    private final OeuvresArtController oeuvresArtController;

    public ButtonCellOeuvre(OeuvresArtController oeuvresArtController) {
        this.oeuvresArtController = oeuvresArtController;

        // Charger les images pour les boutons
        Image deleteImage = new Image(getClass().getResourceAsStream("/image/delete-100-128.png"));
        Image detailImage = new Image(getClass().getResourceAsStream("/image/details.png"));

        // Créer les ImageView pour les images chargées
        ImageView deleteImageView = new ImageView(deleteImage);
        ImageView detailImageView = new ImageView(detailImage);

        // Ajuster la taille des images
        deleteImageView.setFitWidth(16);
        deleteImageView.setFitHeight(16);
        detailImageView.setFitWidth(16);
        detailImageView.setFitHeight(16);

        // Ajouter les ImageView aux boutons
        deleteButton.setGraphic(deleteImageView);
        detailButton.setGraphic(detailImageView);

        // Ajouter des styles aux boutons
        deleteButton.getStyleClass().add("button");
        detailButton.getStyleClass().add("button");

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
                    oeuvresArtController.afficherOeuvreArt();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gérer les erreurs de suppression ici
                }
            }
        });

        detailButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/DetailsOeuvreArt.fxml"));
                Parent root = loader.load();
                DetailsOeuvreArtController controller = loader.getController();
                OeuvreArt oeuvreArt = getTableView().getItems().get(getIndex());
                controller.initData(oeuvreArt);
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
            // Afficher les boutons dans la cellule
            setGraphic(new HBox(deleteButton, detailButton));
        }
    }
}

package Controles;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import models.Categorie;
import services.categorie.CategorieService;

import java.sql.SQLException;
import java.util.Optional;

public class ButtonCellFactory extends TableCell<Categorie, Void> {

    private final Button editButton = new Button();

    private final Button deleteButton = new Button();
    private final CategoriePage categoriePage; // Ajout de l'objet CategoriePage

    public ButtonCellFactory(CategoriePage categoriePage) {
        this.categoriePage = categoriePage;

        // Charger les images de poubelle et de stylo
        Image deleteImage = new Image(getClass().getResourceAsStream("/image/delete-100-128.png"));
        Image editImage = new Image(getClass().getResourceAsStream("/image/modify-7-128.png"));

        // Créer des ImageView pour les images chargées
        ImageView deleteImageView = new ImageView(deleteImage);
        ImageView editImageView = new ImageView(editImage);

        // Ajuster la taille des images
        deleteImageView.setFitWidth(16);
        deleteImageView.setFitHeight(16);
        editImageView.setFitWidth(16);
        editImageView.setFitHeight(16);

        // Ajouter les ImageView aux boutons
        deleteButton.setGraphic(deleteImageView);
        editButton.setGraphic(editImageView);

        // Ajouter des styles aux boutons
        deleteButton.getStyleClass().add("button");
        editButton.getStyleClass().add("button");

        // Ajouter des gestionnaires d'événements pour les boutons
        editButton.setOnAction(event -> {
            Categorie categorie = getTableView().getItems().get(getIndex());
            // Créer et configurer la boîte de dialogue personnalisée
            ModifierCategorieDialog dialog = new ModifierCategorieDialog(categorie, categoriePage);
            dialog.show(); // Utilise show() au lieu de showAndWait()
        });

        deleteButton.setOnAction(event -> {
            Categorie categorie = getTableView().getItems().get(getIndex());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette catégorie ?");
            alert.setContentText("La catégorie \"" + categorie.getNomCategorie() + "\" sera définitivement supprimée.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    CategorieService categorieService = new CategorieService();
                    categorieService.SupprimerCategorie(categorie.getIdCategorie());
                    // Rafraîchir le tableau des catégories après la suppression
                    getTableView().getItems().remove(categorie);
                    System.out.println("Catégorie supprimée avec succès !");
                    // Rafraîchir la liste des catégories après la suppression
                    categoriePage.afficherCategories(); // Appel de la méthode pour rafraîchir les catégories
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gérer les erreurs de suppression ici
                }
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
            setGraphic(new HBox(editButton, deleteButton));
        }
    }
}

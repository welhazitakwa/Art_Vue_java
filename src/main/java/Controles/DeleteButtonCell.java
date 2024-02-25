package Controles;

import javafx.scene.control.*;
import models.Panier;
import services.Panier.PanierService;

import java.sql.SQLException;
import java.util.Optional;

public class DeleteButtonCell extends TableCell<Panier, Void> {
    private final Button deleteButton = new Button("Supprimer");
    private final PanierService panierService;

    public DeleteButtonCell(PanierService panierService) {
        this.panierService = panierService;
        deleteButton.setOnAction(event -> {
            Panier panier = getTableView().getItems().get(getIndex());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce panier ?");
            alert.setContentText("Le panier \"" + panier.getId() + "\" sera définitivement supprimé.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    panierService.SupprimerPanier(panier.getId());
                    // Rafraîchir le tableau des paniers après la suppression
                    getTableView().getItems().remove(panier);
                    System.out.println("Panier supprimé avec succès !");
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
            setGraphic(deleteButton);
        }
    }
}

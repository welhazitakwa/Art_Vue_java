package Controles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.OeuvreArt;
import models.Panier;
import models.panieroeuvre;
import services.panieroeuvre.PanieroeuvreService;

import java.sql.SQLException;
import java.util.List;

public class GestionOeuvresPanier {

    @FXML
    private Button AfficherOeuvres;
    @FXML
    private TableColumn<OeuvreArt, Integer> idOeuvreColumn;

    @FXML
    private TableColumn<OeuvreArt, Integer> nomOeuvreColumn;
    @FXML
    private ComboBox<Integer> panierIdCombo;

    @FXML
    private TableView<OeuvreArt> tableOeuvres;
    @FXML
    private TableColumn<OeuvreArt, Void> actionColumn;
    private PanieroeuvreService panieroeuvreService;

    @FXML
    void afficherOeuvres(ActionEvent event) {
        int idPanier = panierIdCombo.getValue();
        chargerOeuvresDuPanier(idPanier);
    }
    @FXML
    public void initialize() {
        panieroeuvreService = new PanieroeuvreService();
        idOeuvreColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomOeuvreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));

        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    OeuvreArt oeuvre = getTableView().getItems().get(getIndex());
                    supprimerOeuvreDuPanier(oeuvre);
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
        });
            remplirComboBoxPanier();
        }


    private void remplirComboBoxPanier() {
        try {
            PanieroeuvreService panieroeuvreService = new PanieroeuvreService();
            List<Panier> paniers = panieroeuvreService.getListePaniers();
            ObservableList<Integer> panierIdList = FXCollections.observableArrayList();
            for (Panier panier : paniers) {
                panierIdList.add(panier.getId());
            }
            panierIdCombo.setItems(panierIdList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        private void chargerOeuvresDuPanier(int idPanier) {
            try {
                List<OeuvreArt> oeuvresDuPanier = panieroeuvreService.getOeuvresDuPanier(idPanier);
                tableOeuvres.setItems(FXCollections.observableArrayList(oeuvresDuPanier));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    private void supprimerOeuvreDuPanier(OeuvreArt oeuvre) {
        // Obtenez l'ID de l'œuvre et l'ID du panier
        int idOeuvre = oeuvre.getId();
        int idPanier = panierIdCombo.getValue();

        try {
            // Appelez la méthode pour supprimer l'œuvre du panier
            panieroeuvreService.supprimerOeuvreDuPanier(idPanier, idOeuvre);

            // Actualisez la liste des œuvres du panier dans la TableView
            chargerOeuvresDuPanier(idPanier);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez l'erreur en affichant un message d'erreur à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la suppression de l'œuvre du panier. Veuillez réessayer.");
            alert.showAndWait();
        }
    }



    }


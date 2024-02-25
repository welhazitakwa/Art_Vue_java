package Controles;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Panier;
import models.Utilisateur;
import services.Panier.PanierService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PanierAdmin {

    @FXML
    private Button Ajouter;

    @FXML
    private TableColumn<Panier, Integer> clientColumn;

    @FXML
    private ComboBox<Integer> clientIdCombo;

    @FXML
    private TableColumn<Panier, Date> dateColumn;

    @FXML
    private TableColumn<Panier, Integer> idColumn;

    @FXML
    private TableColumn<Panier, Void> deleteColumn;

    @FXML
    private TableView<Panier> tablePaniers;

    private PanierService panierService;

    public void initialize() {
        panierService = new PanierService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));

        clientColumn.setCellValueFactory(cellData -> {
            Utilisateur client = cellData.getValue().getClient();
            if (client != null) {
                return new SimpleObjectProperty<>(client.getId());
            } else {
                return new SimpleObjectProperty<>(null);
            }
        });

        remplirComboBoxClients();
        AfficherPanier();

        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        deleteColumn.setCellFactory(param -> new DeleteButtonCell(panierService));
    }

    private void AfficherPanier() {
        try {
            if (panierService != null) {
                List<Panier> paniers = panierService.AfficherPanier();
                ObservableList<Panier> panierObservableList = FXCollections.observableArrayList(paniers);
                tablePaniers.setItems(panierObservableList);
                System.out.println("Paniers affichés avec succès : " + paniers.size());
            } else {
                System.err.println("panierService n'est pas initialisé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ajouterPanier(ActionEvent event) {
        Panier nouveauPanier = new Panier();
        nouveauPanier.setDateAjout(new Date());
        Integer clientId = clientIdCombo.getValue();
        if (clientId != null) {
            Utilisateur client = new Utilisateur();
            client.setId(clientId);
            nouveauPanier.setClient(client);
            try {
                boolean ajoutSucces = panierService.AjouterPanier(nouveauPanier);
                if (ajoutSucces) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Le panier " + clientIdCombo.getValue() + " a été ajouté avec succès");
                    alert.show();
                    AfficherPanier();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Impossible d'ajouter le panier : un panier avec cet ID existe déjà.");
                    alert.show();
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de l'ajout du panier pour le client avec l'ID " + clientId + ": " + e.getMessage());
            }
        }
    }

    private void remplirComboBoxClients() {
        try {
            List<Utilisateur> clients = panierService.getListeClients();
            ObservableList<Integer> clientIdList = FXCollections.observableArrayList();
            for (Utilisateur client : clients) {
                clientIdList.add(client.getId());
            }
            clientIdCombo.setItems(clientIdList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
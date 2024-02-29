package Controles;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
    private TableView<Panier> tablePaniers;
    private PanierService panierService;
public  void initialize()
{
    panierService  = new PanierService();

    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));
   // clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
    clientColumn.setCellValueFactory(cellData -> {
        Utilisateur client = cellData.getValue().getClient();
        if (client != null) {
            return new SimpleObjectProperty<>(client.getId()); // Remplacez getId() par la méthode appropriée pour obtenir l'ID de l'utilisateur
        } else {
            return new SimpleObjectProperty<>(null); // Retourner null si le client est null
        }
    });

    remplirComboBoxClients();
    AfficherPanier();
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
        // Créer un nouveau panier avec la date actuelle et l'ID du client sélectionné dans le ComboBox
        Panier nouveauPanier = new Panier();
        nouveauPanier.setDateAjout(new Date()); // Utilisez la date actuelle
        // Récupérez l'ID du client sélectionné dans le ComboBox
        Integer clientId = clientIdCombo.getValue();
        if (clientId != null ) {
            Utilisateur client = new Utilisateur();
            client.setId(clientId);
            nouveauPanier.setClient(client);
            try {
                // Appeler la méthode AjouterPanier de PanierService pour ajouter le panier
                boolean ajoutSucces = panierService.AjouterPanier(nouveauPanier);
                if (ajoutSucces) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Le panier " + clientIdCombo.getValue() + " a été ajoutée avec succès");
                    alert.show();
                    // Actualiser l'affichage des paniers
                    AfficherPanier();
                } else {
                    // Afficher une alerte si le panier n'a pas pu être ajouté
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
            // Récupérer la liste des clients depuis la base de données
            List<Utilisateur> clients = panierService.getListeClients();

            // Créer une liste d'IDs de clients à partir de la liste des clients
            ObservableList<Integer> clientIdList = FXCollections.observableArrayList();
            for (Utilisateur client : clients) {
                clientIdList.add(client.getId());
            }

            // Ajouter la liste d'IDs de clients au ComboBox
            clientIdCombo.setItems(clientIdList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
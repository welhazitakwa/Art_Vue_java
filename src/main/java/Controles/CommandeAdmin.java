package Controles;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Commande;
import models.Panier;
import models.Utilisateur;
import services.Commande.CommandeService;
import services.Panier.PanierService;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CommandeAdmin {
    @FXML
    private TableColumn<Commande, Void> deleteColumn;

    @FXML
    private TableColumn<Commande, Date> dateColumn;

    @FXML
    private TableColumn<Commande, String> etatColumn;

    @FXML
    private TableColumn<Commande, Integer> idColumn;

    @FXML
    private TableColumn<Commande, Float> montantColumn;

    @FXML
    private TableColumn<Commande, Integer> panierColumn;

    @FXML
    private TableView<Commande> tableCommandes;
    private CommandeService commandeService ;

    public void initialize() {
        commandeService = new CommandeService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
     etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
     panierColumn.setCellValueFactory(cellData -> {
            Panier panier = cellData.getValue().getPanier();
            if (panier != null) {
                return new SimpleObjectProperty<>(panier.getId());
            } else {
                return new SimpleObjectProperty<>(null);
            }
        });

        AfficherCommande();
    }
    private void AfficherCommande() {
        try {
            if (commandeService != null) {
                List<Commande> commandes = commandeService .AfficherCommande();
                ObservableList<Commande> commandeObservableList = FXCollections.observableArrayList(commandes);
                tableCommandes.setItems(commandeObservableList);
                System.out.println("Commandes affichées avec succès : " + commandes.size());
            } else {
                System.err.println("commandeService n'est pas initialisé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}





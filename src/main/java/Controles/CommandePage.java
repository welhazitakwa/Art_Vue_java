package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Commande;
import models.OeuvreArt;
import services.Commande.CommandeService;
import services.Panier.PanierService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandePage implements Initializable {
    private Commande commande;
    @FXML
    private Button BtnToAccueil;

    @FXML
    private Button BtnToOeuvreArt;

    @FXML
    private Label date;

    @FXML
    private Label etat;

    @FXML
    private Button idBTNCategorie;

    @FXML
    private Button idBTNCategorie1;

    @FXML
    private Label montant;
    private CommandeService commandeService = new CommandeService();
    private int idClient;
    private PanierService panierService;
    public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de le client connecté page commande : " + idClient);
        afficherDetailsCommandeUtilisateurActuel();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panierService = new PanierService();
        afficherDetailsCommandeUtilisateurActuel();    }

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOeuvre.fxml"));
            Parent registerParent = loader.load();
            PageOeuvre pageOeuvre = loader.getController();
            pageOeuvre.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void To_Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/Acceuil.fxml"));
            Parent registerParent = loader.load();
            Acceuil acceuil = loader.getController();
            acceuil.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void To_Apropos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/Apropos.fxml"));
            Parent registerParent = loader.load();
            Apropos apropos = loader.getController();
            apropos.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherDetailsCommandeUtilisateurActuel() {
        try {
            int idPanier = panierService.getPanierIdByClientId(idClient);

            Commande commande = commandeService.getCommandeByPanierId(idPanier);
            if (commande != null) {

                montant.setText(": " + commande.getMontant());
                date.setText(": " + commande.getDate());
                etat.setText(": " + commande.getEtat());
            } else {
                System.out.println("Aucune commande trouvée");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les erreurs lors de la récupération de la commande de l'utilisateur
        }
    }
}



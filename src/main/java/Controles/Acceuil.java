package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Concours;
import services.concours.ConcoursService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acceuil {

    @FXML
    private Button BtnToOeuvreArt;
    @FXML
    private Button tocompetition;
    private int idClient;


    public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de l'client connecté page accueil : " + idClient);
    }

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
    void tocompetition(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AfficherConcoursUser.fxml"));
            Parent registerParent = loader.load();
            AfficherConcoursUser page = loader.getController();
            page.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
            // Obtenez le contrôleur AfficherConcoursUser du chargeur
            AfficherConcoursUser afficherConcoursUser = loader.getController();

            // Obtenez le serviceConcours à partir de l'instance de ConcoursService
            ConcoursService concoursService = new ConcoursService();

            // Supposons que vous avez une liste de concours que vous souhaitez afficher
            List<Concours> concoursList = concoursService.getConcoursList(); // Utilisez l'instance du service

            // Appelez la méthode pour initialiser la liste dans votre interface utilisateur
            afficherConcoursUser.initialiserListeConcours(concoursList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

    public void topropos(ActionEvent event) {
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
}

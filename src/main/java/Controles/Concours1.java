package Controles;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import services.concours.ConcoursService;
import models.Concours;


public class Concours1 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Admin;

    @FXML
    private Button User;


    @FXML
    void admin(ActionEvent event) {

        try {
            // Chargez le fichier FXML pour la page d'ajout de concours
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AjoutConcour.fxml"));
            Parent root = loader.load();

            // Créez une nouvelle scène avec la page d'ajout de concours
            Scene scene = new Scene(root);

            // Obtenez la fenêtre (stage) actuelle à partir du bouton
            Stage stage = (Stage) Admin.getScene().getWindow();

            // Définissez la nouvelle scène sur le stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }
    }
    @FXML
    private Stage primaryStage;

    public void initialize(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    void user(ActionEvent event) throws IOException {
        try {
            // Chargez le fichier FXML pour la page d'ajout de concours
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AfficherConcoursUser.fxml"));
            Parent root = loader.load();

            // Créez une nouvelle scène avec la page d'ajout de concours
            Scene scene = new Scene(root);

            // Obtenez la fenêtre (stage) actuelle à partir du bouton
            Stage stage = (Stage) User.getScene().getWindow();

            // Définissez la nouvelle scène sur le stage
            stage.setScene(scene);

            // Obtenez le contrôleur AfficherConcoursUser du chargeur
            AfficherConcoursUser afficherConcoursUser = loader.getController();

            // Obtenez le serviceConcours à partir de l'instance de ConcoursService
            ConcoursService concoursService = new ConcoursService();

            // Supposons que vous avez une liste de concours que vous souhaitez afficher
            List<Concours> concoursList = concoursService.getConcoursList(); // Utilisez l'instance du service

            // Appelez la méthode pour initialiser la liste dans votre interface utilisateur
            afficherConcoursUser.initialiserListeConcours(concoursList);
        } catch (IOException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }
    }


    @FXML
    void initialize() {

    }

}

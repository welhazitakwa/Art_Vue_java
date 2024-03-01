package Controles;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Concours;
import models.OeuvreArt;
import services.concours.ConcoursService;
import services.concours.OeuvreConcoursService;

public class AfficherConcoursUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FlowPane flowPane;
     int idClient ;

   public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de l'client connecté page Oconcouruser : " + idClient);
    }
    @FXML
    void initialize() {
        assert flowPane != null : "fx:id=\"flowPane\" was not injected: check your FXML file 'AfficherConcoursUser.fxml'.";

    }
    @FXML
    private void afficherOeuvres(Concours concours) {
        try {

            int concoursId = concours.getId();
            // Chargez le fichier FXML pour la page d'affichage des œuvres
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AfficherOeuvreUser.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur associé à la vue chargée
            AfficherOeuvreConcoursUser controller = loader.getController();
            controller.setParametre(idClient);

            OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();
            List<OeuvreArt> oeuvres = oeuvreConcoursService.getOeuvresByConcoursId(concours.getId());

            // Récupérez l'ID de l'utilisateur connecté à l'aide de votre classe d'authentification
           // int userId = AuthenticationService.getUserId(); // Assurez-vous d'adapter cette méthode en fonction de votre classe d'authentification
           // int userId =1;
                    // Appelez la méthode pour initialiser les détails des œuvres en passant l'ID de l'utilisateur
            controller.initialiserDetailsOeuvres(oeuvres, concoursId);

            // Créez une nouvelle scène avec la page d'affichage des œuvres
            Scene scene = new Scene(root);

            // Créez une nouvelle fenêtre pour afficher les détails des œuvres
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Détails des Œuvres");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }

    }

    public void initialiserListeConcours(List<Concours> concoursList) {
        // Supprimez tous les éléments existants de la liste
        flowPane.getChildren().clear(); // Assurez-vous d'avoir une instance de FlowPane

        // Configurez le FlowPane
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(20); // Espacement horizontal entre les concours
        flowPane.setVgap(20); // Espacement vertical entre les concours

        // Ajoutez chaque concours à la liste avec le bouton "Voir Œuvres"
        concoursList.forEach(concours -> {
            Button btnVoirOeuvres = new Button("Voir Œuvres");
            btnVoirOeuvres.setOnAction(event -> afficherOeuvres(concours));

            VBox vbox = new VBox(
                    new Label(concours.toString()),
                    btnVoirOeuvres
            );
;
            // Ajoutez chaque VBox à la FlowPane
            flowPane.getChildren().add(vbox);
        });
    }

}

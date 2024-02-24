package Controles;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Concours;
import services.concours.ConcoursService;

public class AfficherConcours {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<VBox> listeConcours;



    @FXML
    void initialize() {

    }


    public void initialiserListeConcours(List<Concours> concoursList) {
        // Supprimez tous les éléments existants de la liste
        listeConcours.getItems().clear();

        // Créez la liste une seule fois à l'extérieur de la boucle
        List<VBox> vboxList = new ArrayList<>();

        // Ajoutez chaque concours à la liste avec des boutons associés
        concoursList.forEach(concours -> {
            Button btnSupprimer = new Button("Supprimer");
            btnSupprimer.setOnAction(event -> supprimerConcours(concours));

            Button btnModifier = new Button("Modifier");
            btnModifier.setOnAction(event -> modifierConcours(concours));

            Button btnVoirOeuvres = new Button("Voir Œuvres");
            btnVoirOeuvres.setOnAction(event -> afficherOeuvres(concours));

            VBox vbox = new VBox(
                    new Label(concours.toString()),
                    btnSupprimer,
                    btnModifier,
                    btnVoirOeuvres
            );

            // Ajoutez chaque VBox à la liste
            vboxList.add(vbox);
        });

        // Ajoutez la liste complète à votre ListView
        listeConcours.getItems().addAll(vboxList);
    }
    // Méthode appelée lors du clic sur le bouton "Supprimer"
    private void supprimerConcours(Concours concours) {
        // Créez une instance de ConcoursService
        ConcoursService concoursService = new ConcoursService();

        // Appelez la méthode supprimerConcours de votre service
        concoursService.supprimerConcours(concours);

        // Rafraîchissez votre interface en rechargeant la liste des concours mise à jour
        List<Concours> nouvelleListe = concoursService.getConcoursList();
        initialiserListeConcours(nouvelleListe);
    }

    // Méthode appelée lors du clic sur le bouton "Modifier"
    private void modifierConcours(Concours concours) {
        try {
            // Chargez le fichier FXML pour la fenêtre de modification
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ModifierConcours.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur de la fenêtre de modification
            ModifierConcours controller = loader.getController();

            // Initialisez les champs du contrôleur avec les données actuelles du concours
            controller.initData(concours);

            // Créez une nouvelle scène avec la fenêtre de modification
            Scene scene = new Scene(root);

            // Créez une nouvelle étape (stage) pour la fenêtre de modification
            Stage stage = new Stage();
            stage.setTitle("Modifier Concours");
            stage.setScene(scene);

            // Affichez la fenêtre de modification
            stage.showAndWait();

            // Rafraîchissez la liste après la modification
            List<Concours> nouvelleListe = ConcoursService.getConcoursList();
            initialiserListeConcours(nouvelleListe);
        } catch (IOException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }
    }


   // Méthode appelée lors du clic sur le bouton "Voir Œuvres"
    private void afficherOeuvres(Concours concours) {
        // Implémentez la logique pour afficher les œuvres du concours
        // ...
    }


}

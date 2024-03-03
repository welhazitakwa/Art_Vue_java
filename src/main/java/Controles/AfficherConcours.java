package Controles;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Concours;
import models.OeuvreArt;
import services.concours.ConcoursService;
import services.concours.OeuvreConcoursService;

public class AfficherConcours {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<VBox> listeConcours;

    @FXML
    private Button retourButton;

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

            Label titleLabel = new Label(concours.toString());
            titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            titleLabel.setAlignment(Pos.CENTER);

            Button btnSupprimer = new Button("Supprimer");
            btnSupprimer.setOnAction(event -> supprimerConcours(concours));

            Button btnModifier = new Button("Modifier");
            btnModifier.setOnAction(event -> modifierConcours(concours));

            Button btnVoirOeuvres = new Button("Voir Œuvres");
            btnVoirOeuvres.setOnAction(event -> {
                try {
                    afficherOeuvres(concours);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            });

            // Créez un HBox pour les boutons avec un espacement
            HBox buttonBox = new HBox(10); // Espacement de 10 pixels
            buttonBox.getChildren().addAll(btnSupprimer, btnModifier, btnVoirOeuvres);
            buttonBox.setAlignment(Pos.CENTER);
            // Créez une VBox pour chaque concours avec le titre et les boutons
            VBox vbox = new VBox(titleLabel, buttonBox);
            vbox.setAlignment(Pos.CENTER); // Alignez la VBox au centre
            vbox.setSpacing(10); // Espacement entre le titre et les boutons


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


  /* // Méthode appelée lors du clic sur le bouton "Voir Œuvres"
    private void afficherOeuvres(Concours concours) {
        // Récupérez les œuvres du concours en utilisant le service approprié
        OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();
        List<OeuvreArt> oeuvres = oeuvreConcoursService.getOeuvresByConcoursId(concours.getId());

        if (oeuvres.isEmpty()) {
            // Si la liste d'œuvres est vide, affichez un message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Œuvres du Concours");
            alert.setHeaderText("Aucune œuvre trouvée pour le concours : " + concours.getTitre());
            alert.showAndWait();
        } else {
            // Créez une liste observable pour la ListView
            ObservableList<OeuvreArt> observableOeuvres = FXCollections.observableArrayList(oeuvres);

            // Créez une ListView pour afficher les œuvres
            ListView<OeuvreArt> listView = new ListView<>(observableOeuvres);

            // Créez une boîte de dialogue pour afficher la liste des œuvres
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Œuvres du Concours");
            alert.setHeaderText("Liste des œuvres pour le concours : " + concours.getTitre());
            alert.getDialogPane().setContent(new VBox(listView));
            alert.showAndWait();
        }
    }*/
  // Méthode appelée lors du clic sur le bouton "Voir Œuvres"

      private void afficherOeuvres(Concours concours) throws MalformedURLException {
          // Récupérez les œuvres du concours en utilisant le service approprié
          OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();
          List<OeuvreArt> oeuvres = oeuvreConcoursService.getOeuvresByConcoursId(concours.getId());

          if (oeuvres.isEmpty()) {
              // Si la liste d'œuvres est vide, affichez un message
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Œuvres du Concours");
              alert.setHeaderText("Aucune œuvre trouvée pour le concours : " + concours.getTitre());
              alert.showAndWait();
          } else {
              // Créez une GridPane pour afficher les œuvres avec des images
              GridPane gridPane = new GridPane();
              gridPane.setHgap(10);
              gridPane.setVgap(10);

              int rowIndex = 0;
              int columnIndex = 0;

              for (OeuvreArt oeuvre : oeuvres) {
                  String imageUrl = oeuvre.getImage();

// Créez une URL à partir de la chaîne d'URL
                  File file = new File(imageUrl);
                  URL url = file.toURI().toURL();

// Créez une ImageView avec l'image de l'œuvre
                  ImageView imageView = new ImageView(new Image(url.toString()));


                  // Redimensionnez l'image si nécessaire
                  imageView.setFitWidth(100);
                  imageView.setFitHeight(100);

                  // Ajoutez l'ImageView à la GridPane
                  gridPane.add(imageView, columnIndex, rowIndex);

                  // Ajoutez d'autres informations sur l'œuvre (nom, artiste, etc.) s'il y a lieu
                  Label label = new Label(oeuvre.getTitre()); // Remplacez par la méthode appropriée pour obtenir le nom de l'œuvre
                  gridPane.add(label, columnIndex, rowIndex + 1);

                  // Passez à la prochaine colonne
                  columnIndex++;

                  // Changez de ligne après un certain nombre de colonnes (vous pouvez ajuster cela selon vos besoins)
                  if (columnIndex > 2) {
                      columnIndex = 0;
                      rowIndex += 2; // Passez deux lignes pour la prochaine œuvre
                  }
              }

              // Créez une boîte de dialogue pour afficher la liste des œuvres
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Œuvres du Concours");
              alert.setHeaderText("Liste des œuvres pour le concours : " + concours.getTitre());
              alert.getDialogPane().setContent(gridPane);
              alert.showAndWait();
          }
      }


    @FXML
    private void retournerPagePrecedente() {
        try {
            // Chargez le fichier FXML pour la page d'ajout de concours
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AjoutConcour.fxml"));
            Parent root = loader.load();

            // Créez une nouvelle scène avec la page d'ajout de concours
            Scene scene = new Scene(root);

            // Obtenez la fenêtre (stage) actuelle à partir du bouton de retour
            Stage stage = (Stage) retourButton.getScene().getWindow();

            // Définissez la nouvelle scène sur le stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }
    }

}

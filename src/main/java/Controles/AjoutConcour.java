package Controles;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Concours;
import models.OeuvreArt;
import services.concours.ConcoursService;
import services.oeuvreArt.OeuvreArtService;

public class AjoutConcour {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private Button ajouter3;


    @FXML
    private DatePicker datedebutTF;

    @FXML
    private DatePicker datefinTF;

    @FXML
    private TextArea descriptionTF;

    @FXML
    private ListView<OeuvreArt> listeOeuvresDisponibles;


    @FXML
    private TextField titreTF;

    @FXML
    private OeuvreArtService serviceOeuvreArt;
    @FXML
    private ConcoursService serviceConcours;



    @FXML
    void ajouter3(ActionEvent event) {
        String titreConcours = titreTF.getText();
        LocalDate dateDebut = datedebutTF.getValue();
        LocalDate dateFin = datefinTF.getValue();
        String description = descriptionTF.getText();
        List<OeuvreArt> oeuvresSelectionnees = listeOeuvresDisponibles.getSelectionModel().getSelectedItems();

        // Contrôle de saisie pour les dates
        if (dateDebut == null || dateFin == null || dateFin.isBefore(dateDebut)) {
            // Affichez un message d'erreur, par exemple avec une boîte de dialogue
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez vérifier les dates saisies.");
            alert.showAndWait();
            return;
        }
        if (serviceConcours.titreExisteDeja(titreConcours)) {
            // Affichez un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setHeaderText(null);
            alert.setContentText("Le titre existe déjà. Veuillez choisir un autre titre.");
            alert.showAndWait();
            return;
        }

        // Créer un objet Concours avec les informations saisies
        Concours concours = new Concours(titreConcours, dateDebut, dateFin, description);

        // Appeler le service pour ajouter le concours avec les oeuvres associées
        serviceConcours.ajouterConcoursAvecOeuvres(concours, oeuvresSelectionnees);

        // Vous pouvez également ajouter des messages de succès ou d'erreur ici
    }


    @FXML
    void initialize() {
        serviceOeuvreArt = new OeuvreArtService();
        serviceConcours = new ConcoursService();

        // Charger la liste des oeuvres disponibles dans la ListView
        List<OeuvreArt> oeuvres = serviceOeuvreArt.getOeuvres();

        // Configurer la ListView pour permettre la sélection multiple
        listeOeuvresDisponibles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Vérifier si la ListView est déjà initialisée
        if (listeOeuvresDisponibles != null) {
            // Si oui, ajouter les éléments directement
            listeOeuvresDisponibles.getItems().addAll(oeuvres);
        } else {
            // Si non, initialiser la ListView avec les éléments
            ObservableList<OeuvreArt> observableList = FXCollections.observableArrayList(oeuvres);
            listeOeuvresDisponibles.setItems(observableList);
        }
    }

    @FXML
    private Stage primaryStage;

    public void initialize(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void afficherConcours(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AfficherConcours.fxml"));
        try {
            Parent root = loader.load();
            AfficherConcours afficherConcours = loader.getController();

            // Obtenez la liste des concours depuis votre service
            List<Concours> concoursList = ConcoursService.getConcoursList();

            // Initialisez la liste des concours dans votre contrôleur
            afficherConcours.initialiserListeConcours(concoursList);

            // Remplacez la scène actuelle par la nouvelle scène avec les concours affichés
            titreTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
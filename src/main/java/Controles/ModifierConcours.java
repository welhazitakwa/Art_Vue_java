package Controles;

import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Concours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.OeuvreArt;
import services.concours.ConcoursService;
import services.concours.OeuvreConcoursService;
import services.oeuvreArt.OeuvreArtService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModifierConcours {

    // Déclarez une instance de ConcoursService
    private ConcoursService concoursService;

    @FXML
    private TextField titreTextField;

    @FXML
    private DatePicker dateDebutPicker;

    @FXML
    private DatePicker dateFinPicker;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private ListView<OeuvreArt> oeuvresListView;

    @FXML
    private Button confirmerButton;

    private Concours concours;

    // Méthode appelée lors de l'initialisation du contrôleur
    public void initialize() {
        // Initialisez votre service ConcoursService
        concoursService = new ConcoursService();
    }

    public void initData(Concours concours) {
        this.concours = concours;

        // Initialisez les champs avec les données actuelles du concours
        titreTextField.setText(concours.getTitre());
        dateDebutPicker.setValue(concours.getDate_debut());
        dateFinPicker.setValue(concours.getDate_fin());
        descriptionTextField.setText(concours.getDescription());
        OeuvreArtService oeuvreArtService = new OeuvreArtService();

        // Utilisez votre service pour obtenir toutes les œuvres disponibles
        List<OeuvreArt> toutesLesOeuvres = oeuvreArtService.getOeuvres();

        // Configurer la ListView pour permettre la sélection multiple
        oeuvresListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Vérifier si la ListView est déjà initialisée
        if (oeuvresListView.getItems() != null) {
            // Si oui, ajouter les éléments directement
            oeuvresListView.getItems().addAll(toutesLesOeuvres);
        } else {
            // Si non, initialiser la ListView avec les éléments
            ObservableList<OeuvreArt> observableList = FXCollections.observableArrayList(toutesLesOeuvres);
            oeuvresListView.setItems(observableList);
        }
    }

    // Méthode appelée lorsque l'utilisateur confirme les modifications
    @FXML
    private void confirmerModification() {
        // Récupérez les nouvelles informations saisies par l'utilisateur
        String nouveauTitre = titreTextField.getText();
        LocalDate nouvelleDateDebut = dateDebutPicker.getValue();
        LocalDate nouvelleDateFin = dateFinPicker.getValue();
        String nouvelleDescription = descriptionTextField.getText();

        // Récupérez les œuvres sélectionnées dans la ListView
        ObservableList<OeuvreArt> oeuvresSelectionnees = oeuvresListView.getSelectionModel().getSelectedItems();

        // Mettez à jour les données du concours avec les nouvelles informations
        concours.setTitre(nouveauTitre);
        concours.setDate_debut(nouvelleDateDebut);
        concours.setDate_fin(nouvelleDateFin);
        concours.setDescription(nouvelleDescription);
// Mise à jour de la table oeuvre_concours pour refléter les œuvres associées au concours
        OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();

        // Supprimer toutes les entrées existantes pour ce concours
        oeuvreConcoursService.supprimerOeuvresDuConcours(concours.getId());
        // Ajouter les nouvelles œuvres sélectionnées
        List<OeuvreArt> nouvellesOeuvres = new ArrayList<>(oeuvresSelectionnees);
        oeuvreConcoursService.ajouterOeuvresAuConcours(concours.getId(), nouvellesOeuvres);

        // Appelez la méthode ModifierConcours de votre service ConcoursService
        try {
            concoursService.ModifierConcours(concours);
            System.out.println("Concours modifié avec succès dans la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Une erreur s'est produite lors de la modification du concours dans la base de données.");

        }

        // Fermez la fenêtre de modification ou effectuez d'autres actions nécessaires
        // ...
        Stage stage = (Stage) confirmerButton.getScene().getWindow();
        stage.close();
    }
}

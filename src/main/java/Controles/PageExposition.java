package Controles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Exposition;
import models.VenteEncheres;
import services.Exposition.ExpositionService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javafx.fxml.Initializable;
import services.venteencheres.VenteEncheresService;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PageExposition implements Initializable{
    @FXML
    private TableView<Exposition> exposition_tableView;

    @FXML
    private TableColumn<Exposition,String> NomE_tableE;

    @FXML
    private TextField NomE_textFile;

    @FXML
    private TableColumn<Exposition,Integer> idE_tableE;



    @FXML
    private TableColumn<Exposition,Date> DateDebutE_tableE;

    @FXML
    private DatePicker DateDebutE_textFile;
    @FXML
    private TableColumn<Exposition,Date> DateFinE_tableE;

    @FXML
    private DatePicker DateFinE_textFile;
    @FXML
    private TableColumn<Exposition,Integer> NbrOeuvreE_tableE;

    @FXML
    private TextField NbrOeuvreE_textFile;
    private ExpositionService expositionService;
    @FXML
    private ComboBox<Exposition> expositionComboBox;


    @FXML
    private TableView<VenteEncheres> venteEnchere_tableView;

    @FXML
    private TableColumn<VenteEncheres,Integer> idV_tableV;

    @FXML
    private TextField idV_textFile;

    @FXML
    private TableColumn<VenteEncheres, Date> DateDebutV_tableV;

    @FXML
    private DatePicker DateDebutV_textFile;
    @FXML
    private TableColumn<VenteEncheres,Date> DateFinV_tableV;

    @FXML
    private DatePicker DateFinV_textFile;
    @FXML
    private TableColumn<VenteEncheres, Float> PrixDepart_tableV;

    @FXML
    private TextField prixDepartV_textFile;
    @FXML
    private TableColumn<VenteEncheres, String> Statue_tableV;

    @FXML
    private TextField StatueV_textFile;
    private VenteEncheresService venteEncheresService;
    public void ajouter_exposition(ActionEvent actionEvent) throws SQLException {
        ExpositionService cs = new ExpositionService();
        LocalDate dateDebut = DateDebutE_textFile.getValue();
        LocalDate dateFin = DateFinE_textFile.getValue();

        // Vérifier si la date de fin est postérieure à la date de début
        if (dateDebut != null && dateFin != null && dateFin.isBefore(dateDebut)) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Date de fin invalide");
            alert.setContentText("La date de fin doit être postérieure à la date de début.");
            alert.showAndWait();
            return; // Sortir de la méthode si la validation échoue
        }
        // Convertir en java.sql.Date si nécessaire
        Date dateDebutSQL = Date.valueOf(dateDebut);
        Date dateFinSQL = Date.valueOf(dateFin);
        cs.AjouterExposition(new Exposition(-1,NomE_textFile.getText(),dateDebutSQL,dateFinSQL,Integer.parseInt(NbrOeuvreE_textFile.getText())));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exposition ajoutée");
        alert.setContentText("Exposition ajoutée !");
        alert.show();
        idE_tableE.setCellValueFactory(new PropertyValueFactory<Exposition,Integer>("id_exposition"));
        NomE_tableE.setCellValueFactory(new PropertyValueFactory<Exposition,String>("nom_exposition"));
        DateDebutE_tableE.setCellValueFactory(new PropertyValueFactory<Exposition,java.sql.Date>("DateDebut"));
        DateFinE_tableE.setCellValueFactory(new PropertyValueFactory<Exposition,java.sql.Date>("DateFin"));
        NbrOeuvreE_tableE.setCellValueFactory(new PropertyValueFactory<Exposition,Integer>("NbrOeuvreE"));
        //ObservableList<Exposition> list = FXCollections.observableArrayList();
        expositionService = new ExpositionService();
        initialiserTableView();
        chargerDonnees();
        /*list.addAll(cs.AfficherExposition());
        exposition_tableView.setItems(list);
        exposition_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Exposition selectedUser = exposition_tableView.getSelectionModel().getSelectedItem();
                idE_textFile.setText(String.valueOf(selectedUser.getId()));
                NomE_textFile.setText(selectedUser.getNom());
                DateDebutE_textFile.setValue(selectedUser.getDateDebut());
            }
        });*/
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources){
        System.out.println("Méthode initialize appelée.");
        expositionService = new ExpositionService();
        initialiserTableView();
        chargerDonnees();
        venteEncheresService = new VenteEncheresService();
        initialiserTableViewV();
        chargerDonneesV();
        // Charger les expositions depuis la base de données et les ajouter au ComboBox
        List<Exposition> expositions = null;
        try {
            expositions = expositionService.AfficherExposition();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        expositionComboBox.setItems(FXCollections.observableArrayList(expositions));
    }


    public void modifier_exposition(ActionEvent actionEvent) {
        Exposition expositionSelectionnee = exposition_tableView.getSelectionModel().getSelectedItem();
        if (expositionSelectionnee == null) {
            // Aucune exposition sélectionnée, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune exposition sélectionnée");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une exposition à modifier.");
            alert.showAndWait();
            return;
        }

        // Récupérer les nouvelles valeurs depuis les champs de texte

        String nomExposition = NomE_textFile.getText();
        LocalDate dateDebut = DateDebutE_textFile.getValue();
        LocalDate dateFin = DateFinE_textFile.getValue();
        int nbrOeuvre = Integer.parseInt(NbrOeuvreE_textFile.getText());

        // Mettre à jour l'exposition sélectionnée avec les nouvelles valeurs

        expositionSelectionnee.setNom(nomExposition);
        expositionSelectionnee.setDateDebut(Date.valueOf(dateDebut));
        expositionSelectionnee.setDateFin(Date.valueOf(dateFin));
        expositionSelectionnee.setNbrOeuvre(nbrOeuvre);

        // Appeler la méthode pour modifier l'exposition dans la base de données
        try {
            expositionService.ModifierExposition(expositionSelectionnee);

            // Rafraîchir la table view pour refléter les modifications
            chargerDonnees();

            // Afficher un message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exposition modifiée");
            alert.setHeaderText(null);
            alert.setContentText("Les informations de l'exposition ont été modifiées avec succès.");
            alert.showAndWait();
        } catch (SQLException e) {
            // En cas d'erreur lors de la modification de l'exposition, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de la modification");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la modification de l'exposition : " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void supprimer_exposition(ActionEvent actionEvent) {
        // Récupérer l'exposition sélectionnée dans la TableView
        Exposition expositionSelectionnee = exposition_tableView.getSelectionModel().getSelectedItem();

        // Vérifier si une exposition est sélectionnée
        if (expositionSelectionnee == null) {
            // Afficher un message d'erreur si aucune exposition n'est sélectionnée
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune exposition sélectionnée");
            alert.setContentText("Veuillez sélectionner une exposition à supprimer.");
            alert.showAndWait();
            return;
        }

        // Confirmer la suppression avec une boîte de dialogue
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation de suppression");
        confirmationAlert.setHeaderText("Supprimer l'exposition sélectionnée ?");
        confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer l'exposition sélectionnée ?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        // Vérifier si l'utilisateur a confirmé la suppression
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Supprimer l'exposition de la base de données en utilisant le service
                expositionService.SupprimerExposition(expositionSelectionnee.getId());

                // Mettre à jour la TableView après la suppression
                chargerDonnees();

                // Afficher un message de succès
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Exposition supprimée");
                successAlert.setHeaderText(null);
                successAlert.setContentText("L'exposition a été supprimée avec succès.");
                successAlert.showAndWait();
            } catch (SQLException e) {
                // Afficher une alerte en cas d'erreur lors de la suppression
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText("Erreur lors de la suppression de l'exposition");
                errorAlert.setContentText("Une erreur est survenue lors de la suppression de l'exposition. Veuillez réessayer.");
                errorAlert.showAndWait();
                e.printStackTrace();
            }
        }
    }

    private void initialiserTableView() {
        idE_tableE.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomE_tableE.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DateDebutE_tableE.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        DateFinE_tableE.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        NbrOeuvreE_tableE.setCellValueFactory(new PropertyValueFactory<>("nbrOeuvre"));
    }

    private void chargerDonnees() {
       /* try {
            List<Exposition> expositions = expositionService.AfficherExposition();
            ObservableList<Exposition> observableList = FXCollections.observableArrayList(expositions);
            exposition_tableView.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        try {
            if (expositionService != null) { // Vérifier si categorieService est initialisé
                List<Exposition> expositions = expositionService.AfficherExposition();
                ObservableList<Exposition> expositionsObservableList = FXCollections.observableArrayList(expositions);
                exposition_tableView.setItems(expositionsObservableList);
                System.out.println("Exposition affichées avec succès : " + expositions.size());
            } else {
                System.err.println("ExpositionService n'est pas initialisé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialiserTableViewV() {
        idV_tableV.setCellValueFactory(new PropertyValueFactory<>("id"));
        DateDebutV_tableV.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        DateFinV_tableV.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        PrixDepart_tableV.setCellValueFactory(new PropertyValueFactory<>("prixDepart"));
        Statue_tableV.setCellValueFactory(new PropertyValueFactory<>("statue"));
    }

    private void chargerDonneesV() {
       /* try {
            List<Exposition> expositions = expositionService.AfficherExposition();
            ObservableList<Exposition> observableList = FXCollections.observableArrayList(expositions);
            exposition_tableView.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        try {
            if (venteEncheresService != null) { // Vérifier si categorieService est initialisé
                List<VenteEncheres> venteEncheres = venteEncheresService.AfficherVenteEncheres();
                ObservableList<VenteEncheres> venteEncheresObservableList = FXCollections.observableArrayList(venteEncheres);
                venteEnchere_tableView.setItems(venteEncheresObservableList);
                System.out.println("VenteEncheres affichées avec succès : " + venteEncheres.size());
            } else {
                System.err.println("VenteEncheresService n'est pas initialisé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void associerVenteExposition(ActionEvent actionEvent) {
        // Récupérer l'exposition sélectionnée dans le ComboBox
        Exposition expositionSelectionnee = expositionComboBox.getValue();
        if (expositionSelectionnee == null) {
            // Si aucune exposition n'est sélectionnée, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune exposition sélectionnée");
            alert.setContentText("Veuillez sélectionner une exposition avant d'associer une vente aux enchères.");
            alert.showAndWait();
            return;
        }

        // Récupérer la vente aux enchères sélectionnée dans la TableView
        VenteEncheres venteEncheresSelectionnee = venteEnchere_tableView.getSelectionModel().getSelectedItem();
        if (venteEncheresSelectionnee == null) {
            // Si aucune vente aux enchères n'est sélectionnée, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune vente aux enchères sélectionnée");
            alert.setContentText("Veuillez sélectionner une vente aux enchères à associer à l'exposition.");
            alert.showAndWait();
            return;
        }

        try {
            // Appeler la méthode pour associer la vente aux enchères à l'exposition
            expositionService.associerVenteExposition(venteEncheresSelectionnee.getId(), expositionSelectionnee.getId());

            // Afficher un message de succès
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Succès");
            successAlert.setHeaderText(null);
            successAlert.setContentText("La vente aux enchères a été associée à l'exposition avec succès.");
            successAlert.showAndWait();
        } catch (SQLException e) {
            // En cas d'erreur, afficher un message d'erreur
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Une erreur est survenue lors de l'association de la vente aux enchères à l'exposition : " + e.getMessage());
            errorAlert.showAndWait();
        }
    }
}
package Controles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Exposition;
import models.OffreEnchere;
import models.VenteEncheres;
import services.offreenchere.OffreEnchereService;
import services.venteencheres.VenteEncheresService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PageOffreEnchere implements Initializable {
@FXML
    private TableView<OffreEnchere> offreEnchere_tableView;

    @FXML
    private TableColumn<OffreEnchere,Integer> idO_tableO;

    @FXML
    private TextField idO_textFile;

    @FXML
    private TableColumn<OffreEnchere, Date> DateO_tableO;

    @FXML
    private DatePicker DateO_textFile;

    @FXML
    private TableColumn<OffreEnchere, Float> montantO_tableO;

    @FXML
    private TextField montantO_textFile;

    private OffreEnchereService offreEnchereService;
    public void ajouter_exposition(ActionEvent actionEvent) throws SQLException {
        OffreEnchereService cs = new OffreEnchereService();
        LocalDate date = DateO_textFile.getValue();
        // Convertir en java.sql.Date si nécessaire
        Date dateSQL = Date.valueOf(date);
        cs.AjouterOffreEnchere(new OffreEnchere(Integer.parseInt(idO_textFile.getText()),Float.parseFloat(montantO_textFile.getText()),dateSQL));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Categorie ajoutée");
        alert.setContentText("Categorie ajoutée !");
        alert.show();
        idO_tableO.setCellValueFactory(new PropertyValueFactory<OffreEnchere,Integer>("id_offreEncheres"));
        montantO_tableO.setCellValueFactory(new PropertyValueFactory<OffreEnchere,Float>("Montant"));
        DateO_tableO.setCellValueFactory(new PropertyValueFactory<OffreEnchere,java.sql.Date>("Date"));
        ObservableList<OffreEnchere> list = FXCollections.observableArrayList();
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
        offreEnchereService = new OffreEnchereService();
        initialiserTableView();
        chargerDonnees();
    }

    public void modifier_exposition(ActionEvent actionEvent) {
    }

    public void supprimer_exposition(ActionEvent actionEvent) {
    }

    private void initialiserTableView() {
        idO_tableO.setCellValueFactory(new PropertyValueFactory<>("id"));
        montantO_tableO.setCellValueFactory(new PropertyValueFactory<>("montant"));
        DateO_tableO.setCellValueFactory(new PropertyValueFactory<>("date"));
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
            if (offreEnchereService != null) { // Vérifier si categorieService est initialisé
                List<OffreEnchere> offreEnchere = offreEnchereService.AfficherOffreEnchere();
                ObservableList<OffreEnchere> offreEnchereObservableList = FXCollections.observableArrayList(offreEnchere);
                offreEnchere_tableView.setItems(offreEnchereObservableList);
                System.out.println("Catégories affichées avec succès : " + offreEnchere.size());
            } else {
                System.err.println("categorieService n'est pas initialisé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

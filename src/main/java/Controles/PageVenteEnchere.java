package Controles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Exposition;
import models.VenteEncheres;
import services.Exposition.ExpositionService;
import services.venteencheres.VenteEncheresService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PageVenteEnchere implements Initializable {
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
        VenteEncheresService cs = new VenteEncheresService();
        LocalDate dateDebut = DateDebutV_textFile.getValue();
        LocalDate dateFin = DateFinV_textFile.getValue();
        // Convertir en java.sql.Date si nécessaire
        Date dateDebutSQL = Date.valueOf(dateDebut);
        Date dateFinSQL = Date.valueOf(dateFin);
        cs.AjouterVenteEncheres(new VenteEncheres(Integer.parseInt(idV_textFile.getText()),dateDebutSQL,dateFinSQL,Float.parseFloat(prixDepartV_textFile.getText()),StatueV_textFile.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Categorie ajoutée");
        alert.setContentText("Categorie ajoutée !");
        alert.show();
        idV_tableV.setCellValueFactory(new PropertyValueFactory<VenteEncheres,Integer>("id_ventEncheres"));
        DateDebutV_tableV.setCellValueFactory(new PropertyValueFactory<VenteEncheres,java.sql.Date>("DateDebut"));
        DateFinV_tableV.setCellValueFactory(new PropertyValueFactory<VenteEncheres,java.sql.Date>("DateFin"));
        PrixDepart_tableV.setCellValueFactory(new PropertyValueFactory<VenteEncheres,Float>("PrixDepart"));
        Statue_tableV.setCellValueFactory(new PropertyValueFactory<VenteEncheres,String>("Statue"));
        ObservableList<VenteEncheres> list = FXCollections.observableArrayList();
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
        venteEncheresService = new VenteEncheresService();
        initialiserTableView();
        chargerDonnees();
    }

    public void modifier_exposition(ActionEvent actionEvent) {
    }

    public void supprimer_exposition(ActionEvent actionEvent) {
    }

    private void initialiserTableView() {
        idV_tableV.setCellValueFactory(new PropertyValueFactory<>("id"));
        DateDebutV_tableV.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        DateFinV_tableV.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        PrixDepart_tableV.setCellValueFactory(new PropertyValueFactory<>("prixDepart"));
        Statue_tableV.setCellValueFactory(new PropertyValueFactory<>("statue"));
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
            if (venteEncheresService != null) { // Vérifier si categorieService est initialisé
                List<VenteEncheres> venteEncheres = venteEncheresService.AfficherVenteEncheres();
                ObservableList<VenteEncheres> venteEncheresObservableList = FXCollections.observableArrayList(venteEncheres);
                venteEnchere_tableView.setItems(venteEncheresObservableList);
                System.out.println("Catégories affichées avec succès : " + venteEncheres.size());
            } else {
                System.err.println("categorieService n'est pas initialisé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package Controles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Exposition;
import services.Exposition.ExpositionService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javafx.fxml.Initializable;
import java.net.URL;
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
    private TextField idE_textFile;

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
    public void ajouter_exposition(ActionEvent actionEvent) throws SQLException {
        ExpositionService cs = new ExpositionService();
        LocalDate dateDebut = DateDebutE_textFile.getValue();
        LocalDate dateFin = DateFinE_textFile.getValue();
        // Convertir en java.sql.Date si nécessaire
        Date dateDebutSQL = Date.valueOf(dateDebut);
        Date dateFinSQL = Date.valueOf(dateFin);
        cs.AjouterExposition(new Exposition(Integer.parseInt(idE_textFile.getText()),NomE_textFile.getText(),dateDebutSQL,dateFinSQL,Integer.parseInt(NbrOeuvreE_textFile.getText())));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Categorie ajoutée");
        alert.setContentText("Categorie ajoutée !");
        alert.show();
        idE_tableE.setCellValueFactory(new PropertyValueFactory<Exposition,Integer>("id_exposition"));
        NomE_tableE.setCellValueFactory(new PropertyValueFactory<Exposition,String>("nom_exposition"));
        DateDebutE_tableE.setCellValueFactory(new PropertyValueFactory<Exposition,java.sql.Date>("DateDebut"));
        DateFinE_tableE.setCellValueFactory(new PropertyValueFactory<Exposition,java.sql.Date>("DateFin"));
        NbrOeuvreE_tableE.setCellValueFactory(new PropertyValueFactory<Exposition,Integer>("NbrOeuvreE"));
        ObservableList<Exposition> list = FXCollections.observableArrayList();
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
    }

    public void modifier_exposition(ActionEvent actionEvent) {
    }

    public void supprimer_exposition(ActionEvent actionEvent) {
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
                System.out.println("Catégories affichées avec succès : " + expositions.size());
            } else {
                System.err.println("categorieService n'est pas initialisé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

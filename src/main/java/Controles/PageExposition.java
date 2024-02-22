package Controles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Exposition;
import services.Exposition.ExpositionService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.net.URL;
import java.util.ResourceBundle;

public class PageExposition {
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
    private TableColumn<Exposition,String> DateDebutE_tableE;

    @FXML
    private DatePicker DateDebutE_textFile;
    @FXML
    private TableColumn<Exposition,String> DateFinE_tableE;

    @FXML
    private DatePicker DateFinE_textFile;
    @FXML
    private TableColumn<Exposition,String> NbrOeuvreE_tableE;

    @FXML
    private TextField NbrOeuvreE_textFile;
    private ExpositionService expositionService;
    public void ajouter_exposition(ActionEvent actionEvent) {
       /* ExpositionService cs = new ExpositionService();
        cs.AjouterExposition(new Exposition( NomE_textFile.getText(), DateDebutE_textFile,DateFinE_textFile,NbrOeuvreE_textFile.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Categorie ajoutée");
        alert.setContentText("Categorie ajoutée !");
        alert.show();
        idE_tableE.setCellValueFactory(new PropertyValueFactory<Exposition,Integer>("id_categorie&"));
        nomC_tableC.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nom_categorie"));
        descriptionC_tableC.setCellValueFactory(new PropertyValueFactory<Categorie,String>("description"));
        ObservableList<Categorie> list = FXCollections.observableArrayList();
        list.addAll(cs.read());
        categorie_tableView.setItems(list);
        categorie_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Categorie selectedUser = categorie_tableView.getSelectionModel().getSelectedItem();
                idC_textFile.setText(String.valueOf(selectedUser.getId_categorie()));
                nomC_textFile.setText(selectedUser.getNom_categorie());
                descriptionC_textFile.setText(selectedUser.getDescription());
            }
        });*/
    }

    public void modifier_exposition(ActionEvent actionEvent) {
    }

    public void supprimer_exposition(ActionEvent actionEvent) {
    }
    public void initialize(URL location, ResourceBundle resources) {
        expositionService = new ExpositionService();
        initialiserTableView();
        chargerDonnees();
    }

    private void initialiserTableView() {
        idE_tableE.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomE_tableE.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DateDebutE_tableE.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        DateFinE_tableE.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        NbrOeuvreE_tableE.setCellValueFactory(new PropertyValueFactory<>("nbrOeuvre"));
    }

    private void chargerDonnees() {
        try {
            List<Exposition> expositions = expositionService.AfficherExposition();
            ObservableList<Exposition> observableList = FXCollections.observableArrayList(expositions);
            exposition_tableView.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package Controles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.Exposition;
import services.Exposition.ExpositionService;
import javafx.scene.control.TableColumn;


import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherExposition  {
    @FXML
    private TableView<Exposition> tableView;

    @FXML
    private TableColumn<Exposition, Integer> idColumn;

    @FXML
    private TableColumn<Exposition, String> nomColumn;

    @FXML
    private TableColumn<Exposition, java.sql.Date> dateDebutColumn;

    @FXML
    private TableColumn<Exposition, java.sql.Date> dateFinColumn;

    @FXML
    private TableColumn<Exposition, Integer> nbrOeuvreColumn;

    private ExpositionService expositionService;

    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        expositionService = new ExpositionService();
        initialiserTableView();
        chargerDonnees();
    }

    private void initialiserTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        nbrOeuvreColumn.setCellValueFactory(new PropertyValueFactory<>("nbrOeuvre"));
    }

    private void chargerDonnees() {
        try {
            List<Exposition> expositions = expositionService.AfficherExposition();
            ObservableList<Exposition> observableList = FXCollections.observableArrayList(expositions);
            tableView.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    @FXML
    private VBox contentBox;

    @FXML
    public void loadPage1(javafx.event.ActionEvent event) {
        // Charger le contenu de la page 1
        contentBox.getChildren().clear();
        contentBox.getChildren().add(new Button("Contenu de la page 1"));
    }

    @FXML
    void loadPage2(javafx.event.ActionEvent event) {
        // Charger le contenu de la page 2
        contentBox.getChildren().clear();
        contentBox.getChildren().add(new Button("Contenu de la page 2"));
    }

    @FXML
    void loadPage3(javafx.event.ActionEvent event) {
        // Charger le contenu de la page 3
        contentBox.getChildren().clear();
        contentBox.getChildren().add(new Button("Contenu de la page 3"));
    }

    @FXML
    void loadPage4(javafx.event.ActionEvent event) {
        // Charger le contenu de la page 4
        contentBox.getChildren().clear();
        contentBox.getChildren().add(new Button("Contenu de la page 4"));
    }



}

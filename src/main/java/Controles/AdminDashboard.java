package Controles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class AdminDashboard implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox contentArea;

    @FXML
    private Button idBTNCategorie;

    @FXML
    private Button id_pageconcoursmenu;

    @FXML
    private Button btnExit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            pageDashboard(null);

    }

    @FXML
    void pageCategorie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/CategoriePage.fxml"));
            Parent categoriePage = loader.load();
            CategoriePage categorieController = loader.getController();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(categoriePage);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML

    public void pageDashboard(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/Dashboard.fxml"));
            Parent Dashboard = loader.load();
            DashboardStat dashboardStat = loader.getController();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(Dashboard);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void to_ouevre_art(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/OeuvreArtPage.fxml"));
            Parent OeuvreArtPage = loader.load();
            OeuvresArtController oeuvresArtController = loader.getController();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(OeuvreArtPage);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void pageconcoursmenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Concours.fxml"));
            Parent Concours1 = loader.load();

           Concours1 ConcoursController = loader.getController();

            // Effacez le contenu existant et affichez la page de catégorie
            contentArea.getChildren().clear();
            contentArea.getChildren().add(Concours1);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


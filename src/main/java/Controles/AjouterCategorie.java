package Controles;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Categorie;
import services.categorie.CategorieService;

public class AjouterCategorie {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NomCategorieTextField;

    @FXML
    void AjouterCategorie(ActionEvent event) {
        Categorie categorie = new Categorie(NomCategorieTextField.getText());
        CategorieService categorieService = new CategorieService();
        try {
            categorieService.AjouterCategorie(categorie);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("La catégorie " +NomCategorieTextField.getText()+" a été ajouté avec succés" );
            alert.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    void initialize() {

    }

}


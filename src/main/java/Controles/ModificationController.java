package Controles;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.VenteEncheres;
import services.venteencheres.VenteEncheresService;
import javafx.fxml.FXMLLoader;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModificationController implements Initializable {
    @FXML
    private DatePicker dateFinPicker;

    @FXML
    private TextField prixDepartField;

    @FXML
    private ComboBox<String> statutComboBox;

    private VenteEncheres venteEnchere;
    private VenteEncheresService venteEncheresService;

    public void setVenteEnchere(VenteEncheres venteEnchere) {
        this.venteEnchere = venteEnchere;
        System.out.println("Données de la ligne : " + venteEnchere);
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        if (venteEnchere != null && venteEnchere.getDateFin() != null) {
            java.sql.Date dateFinSQL = new java.sql.Date(venteEnchere.getDateFin().getTime());
            LocalDate dateFinLocalDate = dateFinSQL.toLocalDate();
            dateFinPicker.setValue(dateFinLocalDate);
            prixDepartField.setText(String.valueOf(venteEnchere.getPrixDepart()));
        }
        else {
            System.err.println("Erreur : venteEncheres n'est pas initialisé.");
        }

        // Ajouter les options à la ComboBox statutComboBox
        statutComboBox.getItems().addAll("disponible", "indisponible");

        // Définir la valeur par défaut de la ComboBox
        statutComboBox.setValue("disponible");
    }


    public void modifierVenteEnchere(javafx.event.ActionEvent actionEvent) throws SQLException {
        venteEncheresService = new VenteEncheresService();
        // Récupérer les valeurs modifiées
        LocalDate nouvelleDateFin = dateFinPicker.getValue();
        float nouveauPrixDepart = Float.parseFloat(prixDepartField.getText());
        String nouveauStatut = statutComboBox.getValue();

        venteEnchere.setDateFin(Date.valueOf(nouvelleDateFin));
        venteEnchere.setPrixDepart(nouveauPrixDepart);
        venteEnchere.setStatue(nouveauStatut);

        venteEncheresService.ModifierVenteEncheres(venteEnchere);

        ((Stage)((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }
}

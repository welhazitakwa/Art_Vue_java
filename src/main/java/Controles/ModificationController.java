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
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        if (venteEnchere != null && venteEnchere.getDateFin() != null) {
            // Initialiser les champs avec les valeurs de la vente aux enchères sélectionnée
            java.sql.Date dateFinSQL = new java.sql.Date(venteEnchere.getDateFin().getTime());

            // Ensuite, vous pouvez utiliser la méthode toLocalDate()
            LocalDate dateFinLocalDate = dateFinSQL.toLocalDate();
            dateFinPicker.setValue(dateFinLocalDate);
            prixDepartField.setText(String.valueOf(venteEnchere.getPrixDepart()));

            // Remplir le ComboBox avec les options "disponible" et "indisponible"
            statutComboBox.setItems(FXCollections.observableArrayList("disponible", "indisponible"));
            statutComboBox.setValue(venteEnchere.getStatue());
        }
    }


    public void modifierVenteEnchere(javafx.event.ActionEvent actionEvent) throws SQLException {
        // Récupérer les valeurs modifiées
        LocalDate nouvelleDateFin = dateFinPicker.getValue();
        float nouveauPrixDepart = Float.parseFloat(prixDepartField.getText());
        String nouveauStatut = statutComboBox.getValue();

        // Modifier la vente aux enchères
        venteEnchere.setDateFin(Date.valueOf(nouvelleDateFin));
        venteEnchere.setPrixDepart(nouveauPrixDepart);
        venteEnchere.setStatue(nouveauStatut);

        // Appeler la méthode de modification de la vente aux enchères
        venteEncheresService.ModifierVenteEncheres(venteEnchere);

        // Fermer la fenêtre de modification
        ((Stage)((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }
}

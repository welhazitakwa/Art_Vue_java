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
        private TextField montantTextField;



    private int venteEncheresId;

    private OffreEnchereService offreEnchereService;

    public void initData(int venteEncheresId) {
        this.venteEncheresId = venteEncheresId;
        System.out.println("ID de la vente aux enchères : " + venteEncheresId);
        // Initialisez la date avec la date actuelle
       // dateLabel.setText(LocalDate.now().toString());
    }

    @FXML
    void submitOffer(ActionEvent event) {
        String montantText = montantTextField.getText();
        // Vérifiez que le montant est valide
        try {
            float montant = Float.parseFloat(montantText);
            System.out.println("ID de la vente aux enchères : " + venteEncheresId);
            // Utilisez le service OffreEnchereService pour ajouter l'offre
            offreEnchereService = new OffreEnchereService();
            OffreEnchere offreEnchere = new OffreEnchere();
            offreEnchere.setId_VenteEnchere(venteEncheresId);
            System.out.println("ID de la vente aux enchères : " + offreEnchere.getId_VenteEnchere());
            offreEnchere.setMontant(montant);
            offreEnchere.setDate(Date.valueOf(LocalDate.now()));
            offreEnchereService.ajouterOffreEnchere(offreEnchere);
            // Fermez la fenêtre modale après soumission de l'offre
            montantTextField.getScene().getWindow().hide();
        } catch (NumberFormatException e) {
            // Gérez l'erreur si le montant n'est pas un nombre valide
            // Affichez un message d'erreur à l'utilisateur par exemple
            System.out.println("Montant invalide : " + montantText);
        } catch (SQLException e) {
            // Gérez les erreurs liées à la base de données
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

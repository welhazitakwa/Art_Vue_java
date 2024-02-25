package Controles;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.OeuvreArt;
import models.Panier;

import models.Utilisateur;
import models.panieroeuvre;
import services.Panier.PanierService;
import services.panieroeuvre.PanieroeuvreService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AjouterAuPanier {

        @FXML
        private Button Ajouter;

        @FXML
        private ComboBox<Integer> oeuvreIdCombo;

        @FXML
        private ComboBox<Integer> panierIdCombo;

    @FXML
    private TextField quantiteField;
    private PanieroeuvreService panieroeuvreService;
@FXML
public void initialize()
{
    panieroeuvreService = new PanieroeuvreService();
    remplirComboBoxPanier();
    remplirComboBoxOeuvre();
}
    @FXML
    void ajouterAuPanier(ActionEvent event) {
        // Récupérer les valeurs sélectionnées dans les ComboBox et le TextField
        int idPanier = panierIdCombo.getValue();
        int idOeuvre = oeuvreIdCombo.getValue();
        int quantite = Integer.parseInt(quantiteField.getText());

        try {
            // Vérifier si l'œuvre existe déjà dans le panier
            boolean existeDeja = panieroeuvreService.verifierExistenceOeuvreDansPanier(idPanier, idOeuvre);
            if (existeDeja) {
                // Afficher une alerte indiquant que l'œuvre existe déjà dans le panier
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Oeuvre déjà existante");
                alert.setHeaderText(null);
                alert.setContentText("L'œuvre que vous essayez d'ajouter existe déjà dans ce panier.");
                alert.showAndWait();
                return; // Sortir de la méthode sans ajouter l'œuvre au panier
            }

            // Si l'œuvre n'existe pas déjà dans le panier, l'ajouter
            panieroeuvreService.ajouterOeuvreAuPanier(idPanier, idOeuvre, quantite);

            // Afficher un message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText(null);
            alert.setContentText("L'œuvre a été ajoutée au panier avec succès.");
            alert.showAndWait();

            // Effacer les champs après l'ajout
            panierIdCombo.setValue(null);
            oeuvreIdCombo.setValue(null);
            quantiteField.clear();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'erreur en affichant un message d'erreur à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout de l'œuvre au panier. Veuillez réessayer.");
            alert.showAndWait();
        }
    }
    private void remplirComboBoxPanier() {
        try {
            PanieroeuvreService panieroeuvreService = new PanieroeuvreService();
            List<Panier> paniers = panieroeuvreService.getListePaniers();
            ObservableList<Integer> panierIdList = FXCollections.observableArrayList();
            for (Panier panier : paniers) {
                panierIdList.add(panier.getId());
            }
            panierIdCombo.setItems(panierIdList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        private void remplirComboBoxOeuvre() {
            try {
                PanieroeuvreService panieroeuvreService = new PanieroeuvreService();
                List<OeuvreArt> oeuvres = panieroeuvreService.getListeOeuvres();
                ObservableList<Integer> oeuvreIdList = FXCollections.observableArrayList();
                for (OeuvreArt oeuvreArt: oeuvres) {
                    oeuvreIdList.add(oeuvreArt.getId());
                }
                oeuvreIdCombo.setItems(oeuvreIdList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    }








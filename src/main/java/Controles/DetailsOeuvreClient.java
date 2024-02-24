package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.OeuvreArt;
import models.Utilisateur;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetailsOeuvreClient {

    @FXML
    private Button BtnToAccueil;

    @FXML
    private Button BtnToOeuvreArt;

    @FXML
    private Label idArtiste;

    @FXML
    private Button idBTNCategorie;

    @FXML
    private Button idBTNCategorie1;

    @FXML
    private Button idBtnAjoutPanier;

    @FXML
    private Label idCategorie;

    @FXML
    private Label idDescription;

    @FXML
    private Label idEmailArtiste;

    @FXML
    private ImageView idImage;

    @FXML
    private Label idPrix;
    @FXML
    private Label IdTitre;

    @FXML
    private Label idStatus;

    @FXML
    private Label idTelArtiste;

    @FXML
    private Label idTitrePageLabel;

    private OeuvreArt oeuvreArt;
    private Utilisateur artiste;

    public void initData(OeuvreArt oeuvreArt) {
        this.oeuvreArt = oeuvreArt;
        this.artiste = oeuvreArt.getArtiste();
        afficherDetailsOeuvre();
    }

    private void afficherDetailsOeuvre() {
        idTitrePageLabel.setText("Détails de l'oeuvre d'art " + oeuvreArt.getTitre());
        IdTitre.setText(oeuvreArt.getTitre());
        idDescription.setText(oeuvreArt.getDescription());
        idPrix.setText("Prix: " + oeuvreArt.getPrixVente() + " DT");
        idArtiste.setText("Artiste: " + artiste.getNom() + " " + artiste.getPrenom());
        idCategorie.setText("Catégorie: " + oeuvreArt.getCategorie().getNomCategorie());
        idStatus.setText(oeuvreArt.getStatus());
        idEmailArtiste.setText(oeuvreArt.getArtiste().getEmail());
        idTelArtiste.setText(String.valueOf(oeuvreArt.getArtiste().getNumTel()));
        // Charger l'image de l'oeuvre d'art
        // Assurez-vous d'avoir une méthode pour charger l'image depuis l'URL ou le chemin spécifié
        // par exemple : idImage.setImage(new Image(oeuvreArt.getImageUrl()));
    }


    @FXML
    void Ajouter_ToPanier(ActionEvent event) {
        // Ajouter la logique pour ajouter l'oeuvre d'art au panier
    }

    @FXML
    void To_Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/Acceuil.fxml"));
            Parent pageAccueil = loader.load();
            Stage stage = new Stage();
            stage.setScene(new javafx.scene.Scene(pageAccueil));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOeuvre.fxml"));
            Parent pageOeuvre = loader.load();
            Stage stage = new Stage();
            stage.setScene(new javafx.scene.Scene(pageOeuvre));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

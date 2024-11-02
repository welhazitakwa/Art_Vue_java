package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.OeuvreArt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public void initData(OeuvreArt oeuvreArt) {
        this.oeuvreArt = oeuvreArt;
        afficherDetailsOeuvre();
    }

    private void afficherDetailsOeuvre() {
        idTitrePageLabel.setText("Détails de l'oeuvre d'art " + oeuvreArt.getTitre());
        IdTitre.setText(oeuvreArt.getTitre());
        idDescription.setText(oeuvreArt.getDescription());
        idPrix.setText(oeuvreArt.getPrixVente() + " DT");
        idArtiste.setText(oeuvreArt.getArtiste().getNom() + " " + oeuvreArt.getArtiste().getPrenom());
        idCategorie.setText(oeuvreArt.getCategorie().getNomCategorie());
        idStatus.setText(oeuvreArt.getStatus());
        idEmailArtiste.setText(oeuvreArt.getArtiste().getEmail());
        idTelArtiste.setText(String.valueOf(oeuvreArt.getArtiste().getNumTel()));
        String imageUrl = "file:///C:/Users/LENOVO/Desktop/Esprit-2024/PIDEV/Partie_Symfony/Art_Vue_Symfony/public/oeuvre/" + oeuvreArt.getImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Image image = new Image(imageUrl);
            idImage.setImage(image);
        }
    }

    @FXML
    void Ajouter_ToPanier(ActionEvent event) {

    }

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        loadNewPage("/fxml/fxmlClient/PageOeuvre.fxml", event);
    }

    @FXML
    public void To_Accueil(ActionEvent event) {
        loadNewPage("/fxml/fxmlClient/Acceuil.fxml", event);
    }

    private void loadNewPage(String fxmlFilePath, ActionEvent event) {
        try {
            // Charger la nouvelle page depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent newPage = loader.load();
            // Accéder au stage actuel à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Remplacer la scène actuelle par la nouvelle scène
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void To_Apropos(ActionEvent event) {
        loadNewPage("/fxml/fxmlClient/Apropos.fxml", event);
    }
}

package Controles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.OeuvreArt;
import java.net.URL;
import java.util.ResourceBundle;


public class DetailsOeuvreArtController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label artisteLabel;

    @FXML
    private Label categorieLabel;

    @FXML
    private Label dateAjoutLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label prixLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label titreLabel;


    public void initData(OeuvreArt oeuvreArt) {
        titreLabel.setText(oeuvreArt.getTitre());
        descriptionLabel.setText(oeuvreArt.getDescription());
        prixLabel.setText(String.valueOf(oeuvreArt.getPrixVente()));
        artisteLabel.setText(String.valueOf(oeuvreArt.getArtiste().getNom() + " " + oeuvreArt.getArtiste().getPrenom()));
        statusLabel.setText(String.valueOf(oeuvreArt.getStatus()));
        dateAjoutLabel.setText(String.valueOf(oeuvreArt.getDateAjout()));
        categorieLabel.setText(String.valueOf(oeuvreArt.getCategorie().getNomCategorie()));


    }


}

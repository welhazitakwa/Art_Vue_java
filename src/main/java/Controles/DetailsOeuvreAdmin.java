package Controles;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import models.OeuvreArt;
import java.net.URL;
import java.util.ResourceBundle;


public class DetailsOeuvreAdmin {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label IdTitre;

    @FXML
    private Label idArtiste;

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
    private Label idStatus;

    @FXML
    private Label idTelArtiste;

    @FXML
    private Label idTitrePageLabel;


    public void initData(OeuvreArt oeuvreArt) {
        idTitrePageLabel.setText(" Détails de l'oeuvre d'art " +oeuvreArt.getTitre());
        IdTitre.setText(" Titre: " +oeuvreArt.getTitre());
        idDescription.setText(oeuvreArt.getDescription());
        idPrix.setText(String.valueOf(oeuvreArt.getPrixVente()));
        idArtiste.setText(" Artiste: " + String.valueOf(oeuvreArt.getArtiste().getNom() + " " + oeuvreArt.getArtiste().getPrenom()));
        idStatus.setText(String.valueOf(oeuvreArt.getStatus()));
        //dateAjoutLabel.setText(String.valueOf(oeuvreArt.getDateAjout()));
        idCategorie.setText(" Catégorie: " + String.valueOf(oeuvreArt.getCategorie().getNomCategorie()));


    }


}

package Controles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.OeuvreArt;

public class DetailsOeuvreArtController {

    @FXML
    private Label titreLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label prixVenteLabel;

    public void initData(OeuvreArt oeuvreArt) {
        titreLabel.setText(oeuvreArt.getTitre());
        descriptionLabel.setText(oeuvreArt.getDescription());
        prixVenteLabel.setText(String.valueOf(oeuvreArt.getPrixVente()));
        // Ajoutez d'autres champs ici si nécessaire
    }
}

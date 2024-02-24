package Controles;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import models.OeuvreArt;
import services.oeuvreArt.OeuvreArtService;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class OeuvreArtControllerClient implements Initializable {

    @FXML
    private FlowPane flowPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficherOeuvresArt();
    }

    private void afficherOeuvresArt() {
        OeuvreArtService service = new OeuvreArtService();
        try {
            List<OeuvreArt> oeuvres = service.AfficherOeuvreArt();
            for (OeuvreArt oeuvre : oeuvres) {
                Node cardNode = createCard(oeuvre);
                flowPane.getChildren().add(cardNode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Node createCard(OeuvreArt oeuvre) {
        AnchorPane card = new AnchorPane();
        card.getStyleClass().add("card");
        card.setPrefSize(200, 250);

        Label titleLabel = new Label(oeuvre.getTitre());
        titleLabel.setWrapText(true);
        titleLabel.setMaxWidth(180);
        AnchorPane.setTopAnchor(titleLabel, 160.0);
        AnchorPane.setLeftAnchor(titleLabel, 10.0);
        AnchorPane.setRightAnchor(titleLabel, 10.0);

        Label artistLabel = new Label("Artiste: " + oeuvre.getArtiste().getNom() + " " + oeuvre.getArtiste().getPrenom());
        AnchorPane.setBottomAnchor(artistLabel, 10.0);
        AnchorPane.setLeftAnchor(artistLabel, 10.0);
        AnchorPane.setRightAnchor(artistLabel, 10.0);

        Label priceLabel = new Label("Prix: " + oeuvre.getPrixVente() + " €");
        AnchorPane.setBottomAnchor(priceLabel, 0.0);
        AnchorPane.setLeftAnchor(priceLabel, 10.0);
        AnchorPane.setRightAnchor(priceLabel, 10.0);

        card.getChildren().addAll( titleLabel, artistLabel, priceLabel);

        return card;
    }
}

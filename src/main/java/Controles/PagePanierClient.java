package Controles;

import javafx.event.ActionEvent;
import models.OeuvreArt;
import models.Utilisateur;

public class PagePanierClient {
    private OeuvreArt oeuvreArt;
    private Utilisateur artiste;
    public void initData(OeuvreArt oeuvreArt) {
        this.oeuvreArt = oeuvreArt;
        this.artiste = oeuvreArt.getArtiste();

    }

    public void commende(ActionEvent event) {
    }
}

package Controles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Concours;
import models.OeuvreArt;
import services.concours.ConcoursService;
import services.concours.OeuvreConcoursService;

import java.io.File;
import java.util.List;

public class ClassementConcours {

    @FXML
    private VBox classementVBox;

    public void initialize() {
        // Initialisation, si nécessaire
        afficherClassement();
    }

    private void afficherClassement() {
        // Récupérez la liste de tous les concours
        ConcoursService concoursService = new ConcoursService();
        List<Concours> concoursList = concoursService.getConcoursList();

        // Parcourez la liste des concours
        for (Concours concours : concoursList) {
            // Ajoutez un titre ou une étiquette pour le concours actuel
            Label concoursLabel = new Label("Classement pour le concours : " + concours.getTitre());
            classementVBox.getChildren().add(concoursLabel);

            // Récupérez la liste des œuvres du classement pour ce concours
            OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();
            List<OeuvreArt> classement = oeuvreConcoursService.getTop3ClassementConcours(concours.getId());

            // Ajoutez les éléments graphiques à votre conteneur
            for (OeuvreArt oeuvre : classement) {
                // Créez un conteneur pour chaque œuvre (HBox pour le titre et l'image)
                HBox oeuvreContainer = new HBox();

                // Ajoutez le titre de l'œuvre
                Label oeuvreLabel = new Label("Titre de l'œuvre : " + oeuvre.getTitre());
                oeuvreContainer.getChildren().add(oeuvreLabel);

                // Ajoutez l'image de l'œuvre
                ImageView imageView = new ImageView(new Image(new File(oeuvre.getImage()).toURI().toString()));

                imageView.setFitWidth(100); // Ajustez la largeur de l'image selon vos besoins
                imageView.setFitHeight(100); // Ajustez la hauteur de l'image selon vos besoins
                oeuvreContainer.getChildren().add(imageView);

                // Ajoutez le conteneur d'œuvre à la VBox principale
                classementVBox.getChildren().add(oeuvreContainer);
            }
        }
    }

}

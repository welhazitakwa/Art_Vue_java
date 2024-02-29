package Controles;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class RatingBar extends HBox {

    private List<ImageView> stars = new ArrayList<>();
    private int selectedRating = 0;

    public RatingBar() {
        // Initialisation des étoiles
        for (int i = 1; i <= 5; i++) {
            ImageView star = createStar(i <= selectedRating);
            stars.add(star);

            // Gestionnaire d'événements pour le survol
            star.setOnMouseEntered(createHoverHandler(stars.indexOf(star) + 1));

            // Gestionnaire d'événements pour le clic
            star.setOnMouseClicked(createClickHandler(stars.indexOf(star) + 1));

            this.getChildren().add(star);
        }
    }

    private ImageView createStar(boolean filled) {
        String starPath = filled ? "/imageConcours/pleine.png" : "/imageConcours/vide.png";
        ImageView star = new ImageView(new Image(starPath));
        star.setFitWidth(20);
        star.setFitHeight(20);
        return star;
    }

    private EventHandler<MouseEvent> createHoverHandler(int starIndex) {
        return event -> handleStarHover(starIndex);
    }

    private EventHandler<MouseEvent> createClickHandler(int starIndex) {
        return event -> handleStarClick(starIndex);
    }

    private void handleStarHover(int starIndex) {
        // Mettez à jour l'apparence des étoiles en fonction du survol
        for (int i = 0; i < stars.size(); i++) {
            stars.get(i).setImage(new Image(i < starIndex ? "/imageConcours/pleine.png" : "/imageConcours/vide.png"));
        }
    }

    private void handleStarClick(int starIndex) {
        // Mettez à jour l'apparence des étoiles en fonction du clic
        selectedRating = starIndex;
        for (int i = 0; i < stars.size(); i++) {
            stars.get(i).setImage(new Image(i < starIndex ? "/imageConcours/pleine.png" : "/imageConcours/vide.png"));
        }

        // Ajoutez ici la logique pour traiter le clic, si nécessaire
        System.out.println("Rating sélectionné : " + selectedRating);
    }

    public int getRating() {
        return selectedRating;
    }
}

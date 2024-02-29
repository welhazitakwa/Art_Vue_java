package services.vote;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class StarRating extends HBox {

    private int maxRating;
    private int rating;
    private ImageView[] starIcons;

    public StarRating(int maxRating) {
        this.maxRating = maxRating;
        this.rating = 0;
        this.starIcons = new ImageView[maxRating];
        initializeStars();
    }

    private void initializeStars() {
        for (int i = 0; i < maxRating; i++) {
            ImageView star = createStarIcon();
            final int starValue = i + 1;

            star.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setRating(starValue));

            starIcons[i] = star;
            getChildren().add(star);
        }
    }

    private ImageView createStarIcon() {
        Image starImage = new Image(getClass().getResourceAsStream("C:\\Users\\marie\\OneDrive\\Desktop\\Art_Vue\\src\\main\\resources\\imageConcours\\etoilee.png"));
        ImageView star = new ImageView(starImage);
        star.setFitHeight(20);
        star.setFitWidth(20);
        return star;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 0 && rating <= maxRating) {
            this.rating = rating;
            updateStars();
        }
    }

    private void updateStars() {
        for (int i = 0; i < maxRating; i++) {
            if (i < rating) {
                starIcons[i].setOpacity(1.0);
            } else {
                starIcons[i].setOpacity(0.3);
            }
        }
    }
}

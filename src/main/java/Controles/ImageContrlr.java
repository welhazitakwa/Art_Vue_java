package Controles;

import models.Utilisateur;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class ImageContrlr extends TableCell<Utilisateur, String>{

    private final ImageView imageView = new ImageView();

    public ImageContrlr() {
        setGraphic(imageView);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(String imagePath, boolean empty) {
        super.updateItem(imagePath, empty);

        if (empty || imagePath == null) {
            imageView.setImage(null);
        } else {
            Image image = new Image(imagePath);
            imageView.setImage(image);
            imageView.setFitWidth(93);
            imageView.setFitHeight(100);
        }
    }
}

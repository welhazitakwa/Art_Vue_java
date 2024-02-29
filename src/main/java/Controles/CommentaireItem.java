package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Commentaire;

public class CommentaireItem {

    @FXML
    private Label dateComment;

    @FXML
    private Label nomPrenom;

    @FXML
    private TextField textFieldComment;

    @FXML
    private ImageView userImg;

    @FXML
    void deleteComment(ActionEvent event) {

    }

    @FXML
    void editComment(ActionEvent event) {

    }

    public void setData (Commentaire comment) {

//        System.out.println("Nom: " + user.getNom());
//        System.out.println("Prenom: " + user.getPrenom());
//        System.out.println("Adresse: " + user.getAdresse());
//        System.out.println("Email: " + user.getEmail());
//        System.out.println("NumTel: " + user.getNumTel());
        textFieldComment.setText("qd");
        nomPrenom.setText("qzd");
        dateComment.setText("qzd");

        if (comment != null) {
            textFieldComment.setText(comment.getCommentaire());
            //nomPrenom.setText(comment.get);
            dateComment.setText(String.valueOf(comment.getDate_commentaire()));

        }









    }


}

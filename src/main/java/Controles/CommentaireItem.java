package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    public Button idDelete;

    @FXML
    public Button idedit;
    private int idClient ;

    public void setParametre(int param) {
        idClient = param;
        System.out.println("ahawa l'client connecté : " + param);
    }

    @FXML
    void deleteComment(ActionEvent event) {
    }

    @FXML
    void editComment(ActionEvent event) {

    }

    public void setData (Commentaire comment) {


        ImageView imgProfile = new ImageView(new Image(comment.getImage()));
        userImg.setImage(imgProfile.getImage());
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
            nomPrenom.setText(comment.getNom()+" "+comment.getPrenom());
            dateComment.setText(String.valueOf(comment.getDate_commentaire()));

            if (comment.getClient_id() == idClient) {
                System.out.println("kifkif rww");
                idedit.setVisible(true);
                idDelete.setVisible(true);
            } else {
                idedit.setVisible(false);
                idDelete.setVisible(false);
                System.out.println("mch kifkif");
            }

        }

    }


}

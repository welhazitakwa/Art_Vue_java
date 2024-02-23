package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Utilisateur;
import services.utilisateur.UtilisateurService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserItem {

    @FXML
    private Label UIAdresse;

    @FXML
    private Label UIEmail;

    @FXML
    private Label UINom;

    @FXML
    private Label UIPrenom;

    @FXML
    private Label UITel;

    @FXML
    private ImageView userImg;

    @FXML
    void deleteButton(ActionEvent event) {

    }

    @FXML
    void detailsButton(ActionEvent event) {

    }
    public void setData (Utilisateur user) {
//      //  ImageView imgProfile = new ImageView(String.valueOf(getClass().getResourceAsStream(user.getImage())));
//
//       // userImg.setImage (imgProfile.getImage());
//        System.out.println("Nom: " + user.getNom());
//        System.out.println("Prenom: " + user.getPrenom());
//        System.out.println("Adresse: " + user.getAdresse());
//        System.out.println("Email: " + user.getEmail());
//        System.out.println("NumTel: " + user.getNumTel());
        UINom.setText("qd");
        UIPrenom.setText("qzd");
        UIAdresse.setText("qzd");
        UIEmail.setText("qzd");
        UITel.setText("qzd");
        if (user != null) {
            UINom.setText(user.getNom());
            UIPrenom.setText(user.getPrenom());
            UIAdresse.setText(user.getAdresse());
            UIEmail.setText(user.getEmail());
            UITel.setText(String.valueOf(user.getNumTel()));
        }









    }

}

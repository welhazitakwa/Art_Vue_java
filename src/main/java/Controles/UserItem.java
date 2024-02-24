package Controles;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Utilisateur;
import services.utilisateur.UtilisateurService;

import java.awt.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private Label parametreField;
    @FXML
    private Label parametreField2;
    @FXML
    private HBox card;

    @FXML
    private ImageView userImg;
    public void setParametre(String parametre) {
        parametreField.setText(parametre);
    }
    public void setParametre2(String parametre) {
        parametreField2.setText(parametre);
    }
    @FXML
    void deleteButton(ActionEvent event) {
        Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle(" Confirmation de suppression ");
        dialogC.setHeaderText("Voulez vous vraiment supprimer "+ parametreField2.getText() + " !");
       // dialogC.setContentText(parametreField.getText());
        dialogC.setContentText("Le compte de "+ parametreField2.getText()+" va être supprimé définitivement");

        Optional<ButtonType> answer = dialogC.showAndWait();
        if (answer.get() == ButtonType.OK) {
            UtilisateurService user1 = new UtilisateurService();
            try {
                user1.supprimer(Integer.parseInt(parametreField.getText()));
                } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("User chose Cancel or closed the dialog-box");
        }
    }
    @FXML
    void detailsButton(ActionEvent event) {
        System.out.println(parametreField.getText());
        Stage detailsSatge = (Stage)((Node) event.getSource()).getScene().getWindow();
        double X = detailsSatge.getX();
        double Y = detailsSatge.getY();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userDetails.fxml"));
            Parent root = loader.load() ;
            ScaleTransition st = new ScaleTransition(Duration.millis(50),root);
            st.setInterpolator(Interpolator.EASE_BOTH);
            st.setFromX(0);
            st.setFromY(0);
            st.setToX(1);
            st.setToY(1);

            Stage stage = new Stage() ;
            stage.setTitle("Détails de "+parametreField2.getText());
            Scene scene= new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            stage.setX(X + 200);
            stage.setY(Y + 200);
        } catch (Exception e) {
            e.printStackTrace();

        }

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

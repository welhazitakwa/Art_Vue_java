package Controles;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import models.Utilisateur;

public class RecupMdp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField confirmerMDP;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField hide;

    @FXML
    private PasswordField nvMDP;

    @FXML
    void Modifier(ActionEvent event) {
   //     if (nvMDP.getText().equals(confirmerMDP.getText())) {
//            try {
//               // user1.register(new Utilisateur(nom.getText(),prenom.getText(),email.getText(),login.getText(),pwdd.getText()),selectedValue);
//            } catch (SQLException e) {
//                Alert alert2 = new Alert(Alert.AlertType.ERROR);
//                alert2.setContentText(e.getMessage());
//                alert2.show();
//            }
//            errorLabel.setTextFill(Color.GREEN);
//            errorLabel.setText(" Votre mot de passe est réinitialisé \n Vous pouvez connecter ! ");
//        }
//
//
//        else {
//            errorLabel.setTextFill(Color.RED);
//            errorLabel.setText(" Essayez de reconfirmer votre mot de passe !");
//
//        }
    }

    @FXML
    void initialize() {

    }

}

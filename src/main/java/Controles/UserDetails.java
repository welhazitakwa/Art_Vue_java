package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class UserDetails {

    double x,y;

    @FXML
    private Label adresse;

    @FXML
    private Label cin;

    @FXML
    private Label dateInscri;

    @FXML
    private Label dateNaissance;

    @FXML
    private Label genre;

    @FXML
    private Label login;

    @FXML
    private Label mail;

    @FXML
    private Label nomPrenom;

    @FXML
    private Label numTel;

    @FXML
    private Label profil;

    @FXML
    void closeBtn(ActionEvent event) {
        Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}





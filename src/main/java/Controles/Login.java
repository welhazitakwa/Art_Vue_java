package Controles;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.utilisateur.UtilisateurService;

public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField mdpTextField;

    @FXML
    void seConnecter(ActionEvent event) {
        UtilisateurService user1 = new UtilisateurService();
        int validLogin = 5;
        try {
            validLogin = user1.validateLogin(loginTextField.getText(), mdpTextField.getText());
          //  System.out.println(loginTextField.getText());
          //  System.out.println(mdpTextField.getText());
        } catch (SQLException e) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText(e.getMessage());
            alert2.show();
        }
        if (validLogin == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("c'est un admin");
            alert.show();
        } else if (validLogin == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("c'est un artiste");
            alert.show();
        }else if (validLogin == 2) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("c'est un client");
            alert.show();

        }
        else if (validLogin == 5) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(" mahiich initialisé variable ta3 test");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("barra a3ml compte wella jiibli données s7a7 y3aychk");
            alert.show();
        }
    }

    @FXML
    void initialize() {

    }

}

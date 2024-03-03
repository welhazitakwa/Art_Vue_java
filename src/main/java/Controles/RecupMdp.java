package Controles;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Utilisateur;
import services.utilisateur.UtilisateurService;

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
    private String mail;

    @FXML
    void Modifier(ActionEvent event) {
        if (nvMDP.getText().equals(confirmerMDP.getText())) {
            try {
                UtilisateurService user1 = new UtilisateurService();
                user1.nouveauMDP(nvMDP.getText(),mail);

            } catch (SQLException e) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setContentText(e.getMessage());
                alert2.show();
            }
            errorLabel.setTextFill(Color.GREEN);
            errorLabel.setText(" Votre mot de passe est réinitialisé \n Vous pouvez connecter ! ");
        }


        else {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText(" Essayez de reconfirmer votre mot de passe !");

        }
    }

    @FXML
    void initialize() {


    }

    public void setParametre(String mail) {
      this.mail = mail;
      System.out.println("mail fel recup mdp : " + mail);

    }

    public void connecter(ActionEvent actionEvent) {
        try {   FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent registerParent = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

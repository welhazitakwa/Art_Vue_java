package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import models.Utilisateur;
import services.utilisateur.UtilisateurService;

import java.io.IOException;
import java.sql.SQLException;

import static java.awt.Color.green;

public class Register {
    @FXML
    private AnchorPane contentRegister;

    @FXML
    private RadioButton artisteRadio;

    @FXML
    private RadioButton clientRadio;

    @FXML
    private PasswordField confirmpwd;

    @FXML
    private TextField email;

    @FXML
    private TextField login;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private PasswordField pwdd;

    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private Label regerror;

    @FXML
    void connecterButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent registerParent = loader.load();
            contentRegister.getChildren().clear();  // Use clear() instead of removeAll()
            contentRegister.getChildren().add(registerParent);
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception appropriately (log or show an error message)
        }
    }

    @FXML
    void sinscrireButton(ActionEvent event) {
        UtilisateurService user1 = new UtilisateurService();
        Toggle selectedToggle = toggleGroup.getSelectedToggle();
        if (selectedToggle != null) {
            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String selectedValue = selectedRadioButton.getText();

        if ( nom.getText().equals("") || prenom.getText().equals("") || pwdd.equals("")||login.getText().equals("") ||
                email.getText().equals("")|| confirmpwd.getText().equals("") ) {
            regerror.setText("Assurez-vous de remplir toutes les données !");

        } else {
            regerror.setText(" ");
            if (pwdd.getText().equals(confirmpwd.getText())) {
                try {
                    user1.register(new Utilisateur(nom.getText(),prenom.getText(),email.getText(),login.getText(),pwdd.getText()),selectedValue);
                } catch (SQLException e) {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setContentText(e.getMessage());
                    alert2.show();
                }

                regerror.setTextFill(Color.GREEN);
                regerror.setText(" Vous pouvez connecter maintenant ! ");
            } else {
                regerror.setText(" Essayez de reconfirmer votre mot de passe !");

            }

        }
        } else {
            System.out.println("Aucun RadioButton sélectionné.");
            regerror.setText("Vous êtes un Artiste ou un Client ?");

            //label ta3 erreuuur
        }
        int registeruser = 5;
//        try {
//            user1.register(new Utilisateur(nom.getText(),prenom.getText(),email.getText(),login.getText(),pwdd.getText()),"artiste");
//            //  System.out.println(loginTextField.getText());
//            //  System.out.println(mdpTextField.getText());
//        } catch (SQLException e) {
//            Alert alert2 = new Alert(Alert.AlertType.ERROR);
//            alert2.setContentText(e.getMessage());
//            alert2.show();
//        }
//        if (validLogin == 0) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("c'est un admin");
//            alert.show();
//        } else if (validLogin == 1) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("c'est un artiste");
//            alert.show();
//        }else if (validLogin == 2) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("c'est un client");
//            alert.show();
//
//        } else {
//            labelError.setText("Merci de vérifier vos données !");
//        }
    }

}

package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import services.utilisateur.UtilisateurService;

import java.io.IOException;
import java.sql.SQLException;

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
//        UtilisateurService user1 = new UtilisateurService();
//        //int validLogin = 5;
//        try {
//            validLogin = user1.validateLogin(loginTextField.getText(), mdpTextField.getText());
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

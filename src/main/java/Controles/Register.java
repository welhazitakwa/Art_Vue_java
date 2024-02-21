package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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

    }

}

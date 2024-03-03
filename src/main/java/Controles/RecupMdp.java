package Controles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    }

    @FXML
    void initialize() {
        assert confirmerMDP != null : "fx:id=\"confirmerMDP\" was not injected: check your FXML file 'RecupMdp.fxml'.";
        assert errorLabel != null : "fx:id=\"errorLabel\" was not injected: check your FXML file 'RecupMdp.fxml'.";
        assert hide != null : "fx:id=\"hide\" was not injected: check your FXML file 'RecupMdp.fxml'.";
        assert nvMDP != null : "fx:id=\"nvMDP\" was not injected: check your FXML file 'RecupMdp.fxml'.";

    }

}

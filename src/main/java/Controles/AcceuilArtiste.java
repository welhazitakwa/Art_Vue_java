package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcceuilArtiste {

    @FXML
    private Label parametreField;
    public void setParametre(String parametre) {
        parametreField.setText(parametre);
    }
    private void loadNewPage(String fxmlFilePath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent newPage = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        loadNewPage("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml", event);


    }
    public void To_Apropos(ActionEvent event) {
        loadNewPage("/fxml/fxmlArtiste/AproposArtiste.fxml", event);

    }
}

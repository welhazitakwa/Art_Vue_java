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
    private BorderPane contentArea;
    @FXML
    private Label parametreField;
    public void setParametre(String parametre) {
        parametreField.setText(parametre);
    }
    private void loadNewPage(String fxmlFilePath, ActionEvent event) {
        try {
            // Charger la nouvelle page depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent newPage = loader.load();
            // Accéder au stage actuel à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Remplacer la scène actuelle par la nouvelle scène
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml"));
            Parent registerParent = loader.load();
            OeuvresPageArtiste oeuvresPageArtiste = loader.getController();
            oeuvresPageArtiste.setParametre( parametreField.getText());
            contentArea.getChildren().clear();  // Use clear() instead of removeAll()
            contentArea.getChildren().add(registerParent);
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception appropriately (log or show an error message)
        }

    }



}

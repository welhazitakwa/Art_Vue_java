package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acceuil {

    @FXML
    private Button BtnToOeuvreArt;
    private int idClient ;

    public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de l'client connecté : " + idClient);
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
           // FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOeuvre.fxml"));
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOeuvre.fxml"));
            Parent registerParent = loader.load();
            PageOeuvre pageOeuvre = loader.getController();
            pageOeuvre.setParametre(idClient); // Passage de l'ID au contrôleur AcceuilArtiste
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

    public void To_Apropos(ActionEvent event) {
        loadNewPage("/fxml/fxmlClient/Apropos.fxml", event);

    }

    public void topropos(ActionEvent event) {
        loadNewPage("/fxml/fxmlClient/Apropos.fxml", event);
    }
}

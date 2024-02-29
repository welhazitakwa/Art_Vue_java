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
    private int idArtiste; // Changer le type de idArtiste en int
    public void setParametre(int idArtiste) { // Modifier le type du paramètre
        this.idArtiste = idArtiste;
        System.out.println("ID de l'artiste dans page Acceuil : " + idArtiste);

    }

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml"));
            Parent newPage = loader.load();
            OeuvresPageArtiste oeuvrePageArtiste = loader.getController();
            oeuvrePageArtiste.setParametre(idArtiste); // Passage de l'ID de l'artiste à la page OeuvrePageArtiste
          
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void To_Apropos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/AproposArtiste.fxml"));
            Parent newPage = loader.load();
            AproposArtiste aproposArtiste = loader.getController();
            aproposArtiste.setParametre(idArtiste);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

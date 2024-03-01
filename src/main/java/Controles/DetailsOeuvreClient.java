package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.OeuvreArt;
import services.Panier.PanierService;
import services.panieroeuvre.PanieroeuvreService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetailsOeuvreClient implements Initializable {

    @FXML
    private Button BtnToAccueil;

    @FXML
    private Button BtnToOeuvreArt;

    @FXML
    private Label idArtiste;

    @FXML
    private Button idBTNCategorie;

    @FXML
    private Button idBTNCategorie1;

    @FXML
    private Button idBtnAjoutPanier;

    @FXML
    private Label idCategorie;

    @FXML
    private Label idDescription;

    @FXML
    private Label idEmailArtiste;

    @FXML
    private ImageView idImage;

    @FXML
    private Label idPrix;

    @FXML
    private Label IdTitre;

    @FXML
    private Label idStatus;

    @FXML
    private Label idTelArtiste;

    @FXML
    private Label idTitrePageLabel;

    private OeuvreArt oeuvreArt;
    private int idClient ;
     private PanieroeuvreService panieroeuvreService;
    private PanierService panierService;

    public void initData(OeuvreArt oeuvreArt) {
        this.oeuvreArt = oeuvreArt;
        afficherDetailsOeuvre();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panieroeuvreService = new PanieroeuvreService();
        panierService = new PanierService();
    }
    private void afficherDetailsOeuvre() {
        idTitrePageLabel.setText("Détails de l'oeuvre d'art " + oeuvreArt.getTitre());
        IdTitre.setText(oeuvreArt.getTitre());
        idDescription.setText(oeuvreArt.getDescription());
        idPrix.setText(oeuvreArt.getPrixVente() + " DT");
        idArtiste.setText(oeuvreArt.getArtiste().getNom() + " " + oeuvreArt.getArtiste().getPrenom());
        idCategorie.setText(oeuvreArt.getCategorie().getNomCategorie());
        idStatus.setText(oeuvreArt.getStatus());
        idEmailArtiste.setText(oeuvreArt.getArtiste().getEmail());
        idTelArtiste.setText(String.valueOf(oeuvreArt.getArtiste().getNumTel()));
        String imageUrl = oeuvreArt.getImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Image image = new Image(imageUrl);
            idImage.setImage(image);
        }
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
    public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de l'client connecté page accueil : " + idClient);
    }
    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOeuvre.fxml"));
            Parent registerParent = loader.load();
            PageOeuvre pageOeuvre = loader.getController();
            pageOeuvre.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void To_Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/Acceuil.fxml"));
            Parent registerParent = loader.load();
            Acceuil acceuil = loader.getController();
            acceuil.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void To_Apropos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/Apropos.fxml"));
            Parent registerParent = loader.load();
            Apropos apropos = loader.getController();
            apropos.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void to_Page_Panier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PagePanier.fxml"));
            Parent registerParent = loader.load();
            PagePanier pagePanier = loader.getController();
            pagePanier.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Ajouter_ToPanier(ActionEvent event) throws SQLException {
        if (oeuvreArt != null) { // Vérifier que oeuvreArt n'est pas null
            try {
                int quantite = 1;
                int idOeuvre = oeuvreArt.getId(); // Obtenez l'identifiant de l'oeuvre
                int idPanier = 0;
                idPanier = panierService.getPanierIdByClientId(idClient);
                ; // Remplacez 1 par l'identifiant du panier que vous souhaitez utiliser pour le test
                boolean ajoutReussi = false;
                try {
                    ajoutReussi = panieroeuvreService.ajouterOeuvreAuPanier(idPanier, idOeuvre, quantite);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (ajoutReussi) {
                    // Afficher une alerte indiquant que l'oeuvre a été ajoutée avec succès
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("L'oeuvre " + oeuvreArt.getTitre() + " a été ajoutée avec succès");
                    alert.show();
                } else {
                    // Afficher une alerte indiquant que l'oeuvre existe déjà dans le panier
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("L'oeuvre " + oeuvreArt.getTitre() + " existe déjà dans le panier");
                    alert.show();
                }
            } catch (NumberFormatException e) {
                // Gérer l'exception si la quantité saisie n'est pas un nombre valide
                e.printStackTrace();
            }
        } else {
            // Gérer le cas où oeuvreArt est null
            System.err.println("Erreur : oeuvreArt est null.");
        }
    }

}

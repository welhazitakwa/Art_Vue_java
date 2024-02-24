package Controles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Categorie;
import models.OeuvreArt;
import services.categorie.CategorieService;
import services.oeuvreArt.OeuvreArtService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PageOeuvre implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private ComboBox<String> categorieComboBox;

    private OeuvreArtService oeuvreArtService;
    private CategorieService categorieService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oeuvreArtService = new OeuvreArtService();
        categorieService = new CategorieService();
        // Remplir la liste déroulante des catégories
        try {
            List<Categorie> categories = categorieService.AfficherCategorie();
            ObservableList<String> categoryNames = FXCollections.observableArrayList();
            for (Categorie categorie : categories) {
                categoryNames.add(categorie.getNomCategorie());
            }
            categorieComboBox.setItems(categoryNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Récupérer et afficher les œuvres d'art
        afficherToutesOeuvres();
    }
    private void afficherToutesOeuvres() {
        try {
            List<OeuvreArt> oeuvres = oeuvreArtService.AfficherOeuvreArt();
            afficherOeuvres(oeuvres);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void afficherOeuvres(List<OeuvreArt> oeuvres) {
        gridPane.getChildren().clear();

        int column = 0;
        int row = 0;

        for (OeuvreArt oeuvre : oeuvres) {
            VBox card = createArtworkCard(oeuvre);

            Button discoverButton = new Button("Découvrir");
            discoverButton.setStyle("-fx-background-color:transparent; -fx-background-radius: 5; -fx-border-color: transparent transparent #bc5f6a transparent;");
            discoverButton.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/DetailsOeuvreClient.fxml"));
                    Parent detailsOeuvre = loader.load();
                    DetailsOeuvreClient controller = loader.getController();
                    controller.initData(oeuvre); // Passer l'œuvre sélectionnée au contrôleur
                    Stage stage = new Stage();
                    stage.setScene(new javafx.scene.Scene(detailsOeuvre));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(PageOeuvre.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            HBox buttonBox = new HBox(discoverButton); // Créer une HBox pour centrer le bouton
            buttonBox.setAlignment(javafx.geometry.Pos.CENTER);

            card.getChildren().add(buttonBox); // Ajouter la HBox contenant le bouton

            // Ajout de la marge entre les cartes
            GridPane.setMargin(card, new Insets(20, 20, 20, 20));

            gridPane.add(card, column, row);

            column++;
            if (column == 5) {
                column = 0;
                row++;
            }
        }
    }
    @FXML
    void handleCategorySelection(ActionEvent event) {
        try {
            String selectedCategory = categorieComboBox.getValue(); // Récupérer la catégorie sélectionnée
            List<OeuvreArt> oeuvres;
            if (selectedCategory != null) {
                // Si une catégorie est sélectionnée, récupérer les œuvres d'art correspondantes à cette catégorie
                oeuvres = oeuvreArtService.getAllOeuvreArtByCategorie(selectedCategory);
            } else {
                // Sinon, récupérer toutes les œuvres d'art
                afficherToutesOeuvres();
                return;
            }
            afficherOeuvres(oeuvres);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour créer une carte représentant une œuvre d'art
    private VBox createArtworkCard(OeuvreArt oeuvreArt) {
        VBox card = new VBox();
        card.getStyleClass().add("hbox"); // Ajouter la classe de style pour la carte

        // Image de l'œuvre d'art
        ImageView imageView = new ImageView("image/art.jpg");
        imageView.setFitWidth(136);
        imageView.setFitHeight(174);

        // Titre de l'œuvre d'art
        Label titleLabel = new Label(oeuvreArt.getTitre());
        titleLabel.getStyleClass().add("title-label"); // Ajouter la classe de style pour le titre

        // Artiste
        Label artistLabel = new Label("Artiste: " + oeuvreArt.getArtiste().getNom() + " " + oeuvreArt.getArtiste().getPrenom());

        // Prix
        Label priceLabel = new Label("Prix: " + oeuvreArt.getPrixVente() + " €");

        // Ajout des éléments à la carte
        card.getChildren().addAll(imageView, titleLabel, artistLabel, priceLabel);
        return card;
    }

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOeuvre.fxml"));
            Parent pageOeuvre = loader.load();
            Stage stage = new Stage();
            stage.setScene(new javafx.scene.Scene(pageOeuvre));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void To_Accueil(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/Acceuil.fxml"));
            Parent pageAccueil = loader.load();
            Stage stage = new Stage();
            stage.setScene(new javafx.scene.Scene(pageAccueil));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package Controles;

import Controles.DetailsOeuvreClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    private int idClient ;

    public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de l'client connecté : " + idClient);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oeuvreArtService = new OeuvreArtService();
        categorieService = new CategorieService();
        // Remplir la liste déroulante des catégories
        try {
            List<Categorie> categories = categorieService.AfficherCategorie();
            ObservableList<String> categoryNames = FXCollections.observableArrayList();
            categoryNames.add("Tous"); // Ajouter l'option "Tous"
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
                    // Charger la vue des détails de l'œuvre
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/DetailsOeuvreClient.fxml"));
                    Parent detailsOeuvre = loader.load();

                    // Obtenir une référence au contrôleur
                    DetailsOeuvreClient controller = loader.getController();
                    controller.initData(oeuvre); // Passer l'œuvre sélectionnée au contrôleur

                    // Obtenir la scène actuelle à partir du bouton découvrez
                    Scene currentScene = ((Node) e.getSource()).getScene();

                    // Modifier la racine de la scène pour afficher les détails de l'œuvre
                    currentScene.setRoot(detailsOeuvre);
                } catch (IOException ex) {
                    Logger.getLogger(PageOeuvre.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            //----------commen----------------------------------------

            Button discoverButton2 = new Button("Commenter");
            discoverButton2.setStyle("-fx-background-color:transparent; -fx-background-radius: 5; -fx-border-color: transparent transparent #bc5f6a transparent;");
            discoverButton2.setOnAction(e -> {
                try {
                    // Charger la vue des détails de l'œuvre
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/CommentairesOeuvre.fxml"));
                    Parent detailsOeuvre = loader.load();
                    CommentairesOeuvre commentairesOeuvre = loader.getController();
                    commentairesOeuvre.initData(oeuvre);
                    commentairesOeuvre.setParametre(idClient);

                    Scene currentScene = ((Node) e.getSource()).getScene();

                    // Modifier la racine de la scène pour afficher les détails de l'œuvre
                    currentScene.setRoot(detailsOeuvre);
                } catch (IOException ex) {
                    Logger.getLogger(PageOeuvre.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            // Créer une HBox pour centrer le bouton
            HBox buttonBox = new HBox(discoverButton,discoverButton2);
            buttonBox.setSpacing(10);

            // Créer une HBox pour mettre les boutons côte à côte
//            HBox buttonBox = new HBox(discoverButton);
//            buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
//            buttonBox.setSpacing(5); // Espacement entre les boutons

            card.getChildren().add(buttonBox); // Ajouter la HBox contenant les boutons à la carte

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
            if (selectedCategory != null && !selectedCategory.equals("Tous")) {
                // Si une catégorie est sélectionnée (autre que "Tous"), récupérer les œuvres d'art correspondantes à cette catégorie
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
        ImageView imageView = new ImageView(new Image(oeuvreArt.getImage()));
        imageView.setFitWidth(136);
        imageView.setFitHeight(174);

        // Titre de l'œuvre d'art
        Label titleLabel = new Label(oeuvreArt.getTitre());
        titleLabel.getStyleClass().add("title-label"); // Ajouter la classe de style pour le titre

        // Artiste
        Label artistLabel = new Label("Artiste: " + oeuvreArt.getArtiste().getNom() + " " + oeuvreArt.getArtiste().getPrenom());
        artistLabel.setPadding(new Insets(0, 0, 10, 0));
        // Prix
        Label priceLabel = new Label("Prix: " + oeuvreArt.getPrixVente() + " DT ");
        priceLabel.setStyle("-fx-font-weight: bold;"); // Mettre le prix en gras

        // Icône panier
        ImageView panierIcon = new ImageView(new Image("/image/panier.png"));
        panierIcon.setFitWidth(17);
        panierIcon.setFitHeight(17);

// Bouton d'image panier
        Button panierButton = new Button("", panierIcon);
        panierButton.setStyle("-fx-background-color: transparent; -fx-background-size: 25px 25px; -fx-background-repeat: no-repeat; -fx-background-position: center;");
        panierButton.setPadding(new Insets(0, 0, 0, 6)); // Ajouter un padding à droite de l'icône
        panierButton.setOnAction(event -> {
            // Ajouter la logique pour ajouter l'œuvre au panier
            // Par exemple :
            // addToCart(oeuvreArt);
        });

        // Regrouper le prix et l'icône du panier dans une HBox
        HBox pricePanierBox = new HBox(priceLabel, panierButton);
        pricePanierBox.setSpacing(10); // Espacement entre le prix et l'icône du panier

        // Ajout des éléments à la carte
        card.getChildren().addAll(imageView, titleLabel, artistLabel, pricePanierBox);
        return card;
    }

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        loadNewPage("/fxml/fxmlClient/PageOeuvre.fxml", event);
    }

    @FXML
    public void To_Accueil(ActionEvent event) {
        loadNewPage("/fxml/fxmlClient/Acceuil.fxml", event);
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

    public void To_Apropos(ActionEvent event) {
        loadNewPage("/fxml/fxmlClient/Apropos.fxml", event);
    }
}

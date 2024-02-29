package Controles;

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
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OeuvresPageArtiste implements Initializable {
    @FXML
    private Label labelTest;
    @FXML
    private GridPane gridPane;
    @FXML
    private ComboBox<String> categorieComboBox;

    private OeuvreArtService oeuvreArtService;
    private CategorieService categorieService;
    private int artisteConnecte = 0; // Le nom de l'artiste connecté
    public void setParametre(String parametre) {
        artisteConnecte = Integer.parseInt(parametre) ;
        labelTest.setText(String.valueOf(artisteConnecte));
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
            List<OeuvreArt> oeuvres = oeuvreArtService.getAllOeuvreArtByArtistes(artisteConnecte);
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

            // Bouton Modifier
            Button modifierButton = new Button();
            ImageView editIcon = new ImageView(new Image("/image/editer.png")); // Remplacez "chemin/vers/votre/edit_icon.png" par le chemin de votre icône de modification
            editIcon.setFitWidth(20); // Largeur personnalisée de l'icône
            editIcon.setFitHeight(20); // Hauteur personnalisée de l'icône
            modifierButton.setGraphic(editIcon);

            modifierButton.setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-font-size: 10px; -fx-background-radius: 5;");
            modifierButton.setPrefWidth(20); // Largeur personnalisée du bouton
            modifierButton.setPrefHeight(20); // Hauteur personnalisée du bouton

            modifierButton.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/ModifierOeuvre.fxml"));
                    Parent modifierOeuvreParent = loader.load();
                    ModifierOeuvre controller = loader.getController();
                    controller.EnvoyerDataOeuvre(oeuvre); // Passer l'œuvre sélectionnée au contrôleur

                    // Récupérer le Stage actuel
                    Stage stage = (Stage) gridPane.getScene().getWindow();

                    // Remplacer la scène actuelle par la scène de modification
                    stage.getScene().setRoot(modifierOeuvreParent);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            // Bouton Supprimer
            Button deleteButton = new Button();
            ImageView deleteIcon = new ImageView(new Image("/image/supprimer.png")); // Remplacez "chemin/vers/votre/delete_icon.png" par le chemin de votre icône de suppression
            deleteIcon.setFitWidth(20); // Largeur personnalisée de l'icône
            deleteIcon.setFitHeight(20); // Hauteur personnalisée de l'icône
            deleteButton.setGraphic(deleteIcon);

            deleteButton.setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-font-size: 10px; -fx-background-radius: 5;");
            deleteButton.setPrefWidth(20); // Largeur personnalisée du bouton
            deleteButton.setPrefHeight(20); // Hauteur personnalisée du bouton

            deleteButton.setOnAction(e -> {
                // Créer une boîte de dialogue d'alerte
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression" );
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir supprimer cette œuvre d'art ?");

                // Personnaliser les boutons de l'alerte
                ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                // Afficher l'alerte et attendre la réponse de l'utilisateur
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeYes) {
                    try {
                        int idOeuvreArt = oeuvre.getId(); // Récupérer l'ID de l'œuvre d'art à supprimer
                        OeuvreArtService oeuvreArtService = new OeuvreArtService(); // Créer une instance du service OeuvreArtService
                        oeuvreArtService.SupprimerOeuvreArt(idOeuvreArt); // Appeler la méthode SupprimerOeuvreArt pour supprimer l'œuvre d'art
                        afficherToutesOeuvres(); // Rafraîchir l'affichage des œuvres après la suppression
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });


            // Ajouter les boutons à une boîte HBox
            HBox buttonBox = new HBox(modifierButton, deleteButton);
            buttonBox.setSpacing(10); // Espacement entre les boutons
            buttonBox.setAlignment(javafx.geometry.Pos.CENTER);

            card.getChildren().add(buttonBox); // Ajouter la boîte HBox contenant les boutons à la carte

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
                oeuvres = oeuvreArtService.getAllOeuvreArtByArtisteAndCategory(artisteConnecte, selectedCategory);
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

        // Prix
        Label priceLabel = new Label("Prix: " + oeuvreArt.getPrixVente() + " DT ");

        // Ajout des éléments à la carte
        card.getChildren().addAll(imageView, titleLabel, artistLabel, priceLabel);
        return card;
    }

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        loadNewPage("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml", event);
    }

    @FXML
    public void To_Accueil(ActionEvent event) {
        loadNewPage("/fxml/fxmlArtiste/AcceuilArtiste.fxml", event);
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

    public void to_Ajout(ActionEvent event) {
        loadNewPage("/fxml/fxmlArtiste/AjouterOeuvre.fxml", event);
    }

    public void toApropos(ActionEvent event) {
        loadNewPage("/fxml/fxmlArtiste/AproposArtiste.fxml", event);
    }
}

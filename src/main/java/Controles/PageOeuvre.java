package Controles;

import Controles.DetailsOeuvreClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
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
import services.likes.LikesService;
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
    private int idClient;

    public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de l'client connecté page Oeuvre : " + idClient);
    }

    // Autres variables et méthodes

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
        // Appeler la méthode pour mettre à jour l'état des likes après un court délai
        Platform.runLater(this::updateLikeStateForAllArtworks);
    }


    // Méthode pour mettre à jour l'état des likes pour chaque œuvre
    private void updateLikeStateForAllArtworks() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof VBox) {
                VBox card = (VBox) node;
                // Récupérer l'œuvre associée à cette carte
                OeuvreArt oeuvre = (OeuvreArt) card.getUserData();
                // Récupérer le bouton de like dans cette carte
                ToggleButton likeButton = (ToggleButton) card.lookup(".like-button");
                if (likeButton != null) {
                    try {
                        LikesService likesService = new LikesService();
                        boolean isLiked = likesService.isLikedByUser(idClient, oeuvre.getId());
                        likeButton.setSelected(isLiked); // Définir l'état initial du bouton en fonction de l'état de like dans la base de données
                        ImageView likeIcon = (ImageView) likeButton.getGraphic();
                        if (isLiked) {
                            likeIcon.setImage(new Image("/image/coeurlike.png")); // Afficher l'icône de like
                        } else {
                            likeIcon.setImage(new Image("/image/coeurDislike.png")); // Afficher l'icône de dislike
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Gérer les erreurs
                    }
                }
            }
        }
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
                    controller.setParametre(idClient);
                    controller.initData(oeuvre);
                    Scene currentScene = ((Node) e.getSource()).getScene();
                    currentScene.setRoot(detailsOeuvre);
                } catch (IOException ex) {
                    Logger.getLogger(PageOeuvre.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            Button discoverButton2 = new Button("Commenter");
            discoverButton2.setStyle("-fx-background-color:transparent; -fx-background-radius: 5; -fx-border-color: transparent transparent #bc5f6a transparent;");
            discoverButton2.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/CommentairesOeuvre.fxml"));
                    Parent commeOeuvre = loader.load();
                    CommentairesOeuvre commentairesOeuvre = loader.getController();
                    commentairesOeuvre.setParametre(idClient);

                    commentairesOeuvre.initData(oeuvre);



                    Scene currentScene = ((Node) e.getSource()).getScene();
                    currentScene.setRoot(commeOeuvre);
                } catch (IOException ex) {
                    Logger.getLogger(PageOeuvre.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            // Créer une HBox pour centrer le bouton
            HBox buttonBox = new HBox(discoverButton, discoverButton2);
            buttonBox.setSpacing(10);
            card.getChildren().add(buttonBox);
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

    private VBox createArtworkCard(OeuvreArt oeuvreArt) {
        VBox card = new VBox();
        card.getStyleClass().add("hbox");

        // Créer une StackPane pour superposer l'image et l'icône de like
        StackPane imageContainer = new StackPane();
        ImageView imageView = new ImageView(new Image(oeuvreArt.getImage()));
        imageView.setFitWidth(136);
        imageView.setFitHeight(174);

        // Bouton pour liker ou disliker l'œuvre
        ToggleButton likeButton = new ToggleButton();
        likeButton.setStyle("-fx-background-color: transparent ; -fx-padding: 5 15 0 0 ; ");
        ImageView likeIcon = new ImageView();
        likeIcon.setFitWidth(24);
        likeIcon.setFitHeight(24);

        // Vérifier si l'utilisateur a aimé cette œuvre
        try {
            LikesService likesService = new LikesService();
            boolean isLiked = likesService.isLikedByUser(idClient, oeuvreArt.getId());
            likeButton.setSelected(isLiked); // Définir l'état initial du bouton en fonction de l'état de like dans la base de données
            if (isLiked) {
                likeIcon.setImage(new Image("/image/coeurlike.png")); // Afficher l'icône de like
            } else {
                likeIcon.setImage(new Image("/image/coeurDislike.png")); // Afficher l'icône de dislike
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs
        }

        likeButton.setGraphic(likeIcon);

        // Action à effectuer lors du clic sur le bouton
        likeButton.setOnAction(event -> {
            try {
                LikesService likesService = new LikesService();
                boolean isLiked = likesService.isLikedByUser(idClient, oeuvreArt.getId());
                if (isLiked) {
                    // L'utilisateur a déjà liké cette œuvre, effectuer un dislike
                    likesService.removeLike(idClient, oeuvreArt.getId());
                    likeIcon.setImage(new Image("/image/coeurDislike.png")); // Changer l'icône en dislike
                } else {
                    // L'utilisateur n'a pas encore liké cette œuvre, effectuer un like
                    likesService.addLike(idClient, oeuvreArt.getId());
                    likeIcon.setImage(new Image("/image/coeurlike.png")); // Changer l'icône en like
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer les erreurs
            }
        });

        // Ajouter l'image et l'icône de like à la StackPane
        imageContainer.getChildren().addAll(imageView, likeButton);
        StackPane.setAlignment(likeButton, Pos.TOP_RIGHT); // Positionner le bouton en haut à droite de l'image

        // Ajouter les autres détails de l'œuvre à la VBox
        Label titleLabel = new Label(oeuvreArt.getTitre());
        titleLabel.getStyleClass().add("title-label");
        Label artistLabel = new Label("Artiste: " + oeuvreArt.getArtiste().getNom() + " " + oeuvreArt.getArtiste().getPrenom());
        artistLabel.setPadding(new Insets(0, 0, 10, 0));
        Label priceLabel = new Label("Prix: " + oeuvreArt.getPrixVente() + " DT ");
        priceLabel.setStyle("-fx-font-weight: bold;"); // Mettre le prix en gras

        // Regrouper le prix et l'icône du panier dans une HBox
        HBox pricePanierBox = new HBox(priceLabel);
        pricePanierBox.setSpacing(10); // Espacement entre le prix et l'icône du panier

        // Ajouter les éléments à la carte
        card.getChildren().addAll(imageContainer, titleLabel, artistLabel, pricePanierBox);
        return card;
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

    public void to_Page_Panier(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PagePanier.fxml"));
            Parent registerParent = loader.load();
            Acceuil acceuil = loader.getController();
            acceuil.setParametre(idClient);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

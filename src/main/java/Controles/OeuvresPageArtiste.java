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
import services.likes.LikesService;
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
    @FXML
    private TextField idRecherche;

    private OeuvreArtService oeuvreArtService;
    private CategorieService categorieService;


    private int idArtiste;

    public void setParametre(int idArtiste) {
        this.idArtiste = idArtiste;
        System.out.println("ID de l'artiste dans page Oeuvre : " + idArtiste);

        afficherToutesOeuvres();
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
    }

    private void afficherToutesOeuvres() {
        try {
            List<OeuvreArt> oeuvres = oeuvreArtService.getAllOeuvreArtByArtistes(idArtiste);
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
            ImageView editIcon = new ImageView(new Image("/image/editer.png"));
            editIcon.setFitWidth(20);
            editIcon.setFitHeight(20);
            modifierButton.setGraphic(editIcon);

            modifierButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-font-size: 10px; -fx-background-radius: 5;");
            modifierButton.setPrefWidth(20);
            modifierButton.setPrefHeight(20);

            modifierButton.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/ModifierOeuvre.fxml"));
                    Parent modifierOeuvreParent = loader.load();
                    ModifierOeuvre controller = loader.getController();
                    controller.EnvoyerDataOeuvre(oeuvre);
                    controller.setParametre(idArtiste);
                    Stage stage = (Stage) gridPane.getScene().getWindow();
                    Scene scene = new Scene(modifierOeuvreParent);
                    stage.setScene(scene);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            // Bouton Supprimer
            Button deleteButton = new Button();
            ImageView deleteIcon = new ImageView(new Image("/image/supprimer.png"));
            deleteIcon.setFitWidth(20);
            deleteIcon.setFitHeight(20);
            deleteButton.setGraphic(deleteIcon);

            deleteButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-padding: 2px 5px; -fx-font-size: 10px; -fx-background-radius: 5;");
            deleteButton.setPrefWidth(20);
            deleteButton.setPrefHeight(20);

            deleteButton.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir supprimer cette œuvre d'art ?");

                ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeYes) {
                    try {
                        int idOeuvreArt = oeuvre.getId();
                        OeuvreArtService oeuvreArtService = new OeuvreArtService();
                        oeuvreArtService.SupprimerOeuvreArt(idOeuvreArt);
                        afficherToutesOeuvres();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            HBox buttonBox = new HBox(modifierButton, deleteButton);
            buttonBox.setSpacing(10);
            buttonBox.setAlignment(javafx.geometry.Pos.CENTER);

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
            String recherche = idRecherche.getText(); // Récupérer le texte saisi dans le champ de recherche
            if (!recherche.isEmpty()) { // Vérifier si le champ de recherche n'est pas vide
                List<OeuvreArt> oeuvres;
                if (selectedCategory != null && !selectedCategory.equals("Tous")) {
                    // Si une catégorie est sélectionnée (autre que "Tous"), récupérer les œuvres d'art correspondantes à cette catégorie et à cet artiste
                    oeuvres = oeuvreArtService.rechercherParTitreEtCategorie(recherche, selectedCategory);
                } else {
                    // Sinon, récupérer les œuvres d'art correspondant uniquement à cet artiste
                    oeuvres = oeuvreArtService.rechercherParTitre(recherche);
                }
                afficherOeuvres(oeuvres); // Afficher les œuvres correspondantes
            } else {
                // Si le champ de recherche est vide, afficher toutes les œuvres de la catégorie sélectionnée
                if (selectedCategory != null && !selectedCategory.equals("Tous")) {
                    List<OeuvreArt> oeuvres = oeuvreArtService.getAllOeuvreArtByCategorie(selectedCategory);
                    afficherOeuvres(oeuvres);
                } else {
                    afficherToutesOeuvres(); // Afficher toutes les œuvres
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private VBox createArtworkCard(OeuvreArt oeuvreArt) {
        VBox card = new VBox();
        card.getStyleClass().add("hbox");

        ImageView imageView = new ImageView(new Image(oeuvreArt.getImage()));
        imageView.setFitWidth(136);
        imageView.setFitHeight(174);

        Label titleLabel = new Label(oeuvreArt.getTitre());
        titleLabel.getStyleClass().add("title-label");

        Label artistLabel = new Label("Artiste: " + oeuvreArt.getArtiste().getNom() + " " + oeuvreArt.getArtiste().getPrenom());

        Label priceLabel = new Label("Prix: " + oeuvreArt.getPrixVente() + " DT ");

        // Ajout d'un label pour afficher le nombre total de likes
        Label likesLabel = new Label("Likes:   " + countTotalLikes(oeuvreArt.getId()) );
        likesLabel.setStyle("-fx-font-weight: bold;"); // Appliquer le style CSS pour le gras

        card.getChildren().addAll(imageView, titleLabel, artistLabel, priceLabel, likesLabel);
        return card;
    }
    private int countTotalLikes(int idOeuvre) {
        int totalLikes = 0;
        try {
            LikesService likesService = new LikesService();
            totalLikes = likesService.countLikesForArtwork(idOeuvre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalLikes;
    }

    public void Rechercher(ActionEvent event) {
        String recherche = idRecherche.getText();
        if (!recherche.isEmpty()) { // Vérifier si le champ de recherche n'est pas vide
            try {
                List<OeuvreArt> oeuvres = oeuvreArtService.rechercherParTitre(recherche); // Appeler le service pour rechercher les œuvres par artiste
                afficherOeuvres(oeuvres); // Afficher les œuvres correspondantes
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Si le champ de recherche est vide, afficher toutes les œuvres
            afficherToutesOeuvres();
        }
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

    @FXML
    public void To_Accueil(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/AcceuilArtiste.fxml"));
            Parent newPage = loader.load();
            AcceuilArtiste acceuilArtiste = loader.getController();
            acceuilArtiste.setParametre(idArtiste);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void to_Ajout(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/AjouterOeuvre.fxml"));
            Parent AjouterOeuvreParent = loader.load();
            AjouterOeuvre controller = loader.getController();
            controller.setParametre(idArtiste);
            Stage stage = (Stage) gridPane.getScene().getWindow();
            Scene scene = new Scene(AjouterOeuvreParent);
            stage.setScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void toApropos(ActionEvent event) {
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

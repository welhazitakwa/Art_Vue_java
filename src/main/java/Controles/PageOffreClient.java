package Controles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import models.Exposition;
import models.OffreEnchere;
import models.VenteEncheres;
import services.Exposition.ExpositionService;
import services.offreenchere.OffreEnchereService;
import services.venteencheres.VenteEncheresService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PageOffreClient implements Initializable {
    public ComboBox comboBoxOptions;
    public ComboBox expositionComboBox;
    public ListView venteEncheresListView;
    private ExpositionService expositionService;
    private VenteEncheresService venteEncheresService;
    private OffreEnchereService offreEnchereService;
    private int idClient; // Changer le type de idArtiste en int
    public void setParametre(int idClient) { // Modifier le type du paramètre
        this.idClient = idClient;

    }
    public void To_Accueil(ActionEvent actionEvent) {
    }

    public void To_Apropos(ActionEvent actionEvent) {
    }

    public void To_Oeuvre_Art(ActionEvent actionEvent) {
    }

    public void traiterSelectionComboBox(ActionEvent actionEvent) {
    }

    public void handleExpositionSelection(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        expositionService = new ExpositionService();
        venteEncheresService = new VenteEncheresService();
        offreEnchereService = new OffreEnchereService();
        comboboxexpo();
        afficherVenteEncheres();
    }



    public void comboboxexpo(){
        List<Exposition> expositions = null;
        try {
            expositions = expositionService.AfficherExposition();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Exposition tousExposition = new Exposition(-1, "Tous", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 0);
        expositions.add(0, tousExposition);
        expositionComboBox.setItems(FXCollections.observableArrayList(expositions));
        // Définir un convertisseur pour le ComboBox
        expositionComboBox.setConverter(new StringConverter<Exposition>() {
            @Override
            public String toString(Exposition exposition) {
                if (exposition == null) {
                    return "";
                }
                return exposition.getId() + " - " + exposition.getNom();
            }

            @Override
            public Exposition fromString(String string) {
                return null; // Vous n'avez pas besoin de cela pour un ComboBox non éditable
            }
        });

        // Définir la cellule de rendu personnalisée
        expositionComboBox.setCellFactory(param -> new ListCell<Exposition>() {
            @Override
            protected void updateItem(Exposition exposition, boolean empty) {
                super.updateItem(exposition, empty);
                if (empty || exposition == null) {
                    setText(null);
                } else {
                    setText(exposition.getId() + " - " + exposition.getNom());
                }
            }
        });

        expositionComboBox.setItems(FXCollections.observableArrayList(expositions));
    }




    public void afficherVenteEncheres() {

        List<VenteEncheres> venteEncheres = null;
        try {
            venteEncheres = venteEncheresService.AfficherVenteEncheres();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<VenteEncheres> observableList = FXCollections.observableArrayList(venteEncheres);

        // Définir la cellule de rendu personnalisée pour la ListView
        venteEncheresListView.setCellFactory(param -> new ListCell<VenteEncheres>() {
            @Override
            protected void updateItem(VenteEncheres venteEncheres, boolean empty) {
                super.updateItem(venteEncheres, empty);

                if (empty || venteEncheres == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Créer le bouton pour faire une offre
                    Button faireOffreButton = new Button("Faire une offre");
                    faireOffreButton.setOnAction(event -> {
                        // Ouvrir la fenêtre modale pour faire une offre
                        openFenetreOffre(venteEncheres);

                        // Ajouter ici le code pour ouvrir une fenêtre modale et gérer l'offre
                        // Vous pouvez accéder à l'objet VenteEncheres via la variable venteEncheres
                    });

                    // Afficher les offres enchères liées à cette vente enchère
                    ListView<OffreEnchere> offreEnchereListView = new ListView<>();
                    List<OffreEnchere> offresEncheres = null;
                    try {
                        // Récupérer les offres enchères pour cette vente enchère
                        offresEncheres = offreEnchereService.getOffresEncheresForVenteEncheres(venteEncheres.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    offreEnchereListView.setItems(FXCollections.observableArrayList(offresEncheres));

                    // Créer un conteneur VBox pour placer les informations de la vente enchère, le bouton "Faire une offre" et les offres enchères
                    VBox vbox = new VBox();
                    vbox.getChildren().addAll(
                            new Label("ID: " + venteEncheres.getId() + "\n" +
                                    "Date de début: " + venteEncheres.getDateDebut() + "\n" +
                                    "Date de fin: " + venteEncheres.getDateFin() + "\n" +
                                    "Prix de départ: " + venteEncheres.getPrixDepart() + "\n" +
                                    "Statut: " + venteEncheres.getStatue()),
                            offreEnchereListView,
                            faireOffreButton
                    );
                    vbox.setSpacing(10); // Espacement entre les éléments
                    vbox.setAlignment(Pos.CENTER_LEFT); // Aligner les éléments à gauche

                    // Définir le contenu de la cellule
                    setGraphic(vbox);
                }
            }
        });

        venteEncheresListView.setItems(observableList);
    }




    private void openFenetreOffre(VenteEncheres venteEncheres) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOffreEnchere.fxml"));
            Parent root = loader.load();

            // Passer les informations nécessaires au contrôleur de la fenêtre modale
            PageOffreEnchere controller = loader.getController();
            controller.initData(venteEncheres.getId());

            // Créer une nouvelle instance de Stage pour afficher la fenêtre modale
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Faire une offre");
            stage.setScene(new Scene(root));

            // Afficher la fenêtre modale et attendre jusqu'à sa fermeture
            stage.showAndWait();

            // Actualiser l'affichage ou effectuer d'autres actions après la fermeture de la fenêtre modale si nécessaire
            afficherVenteEncheres();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

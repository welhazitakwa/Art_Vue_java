// OeuvresArtController.java

package Controles;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Categorie;
import models.OeuvreArt;
import services.oeuvreArt.OeuvreArtService;

import java.sql.SQLException;
import java.util.List;

public class OeuvresArtController {


    @FXML
    private TableColumn<OeuvreArt, Void> actionColumn;
    @FXML
    private TableView<OeuvreArt> oeuvresTableView;
    @FXML
    private TableColumn<OeuvreArt, String> categorieColumn;
    @FXML
    TableColumn<OeuvreArt, String> artisteColumn;
    @FXML
    private TableColumn<OeuvreArt, Integer> idColumn;
    @FXML
    private TableColumn<OeuvreArt, String> titreColumn;
    @FXML
    private TableColumn<OeuvreArt, String> descriptionColumn;
    @FXML
    private TableColumn<OeuvreArt, String> dateAjoutColumn;
    @FXML
    private TableColumn<OeuvreArt, Float> prixVenteColumn;
    @FXML
    private TableColumn<OeuvreArt, String> statusColumn;
    @FXML
    private TableColumn<OeuvreArt, String> imageColumn;

    private OeuvreArtService oeuvreArtService = new OeuvreArtService();

    private void initializeCategorieColumn() {
        categorieColumn.setCellValueFactory(cellData -> {
            // Accéder à l'objet OeuvreArt correspondant à la ligne
            OeuvreArt oeuvre = cellData.getValue();
            // Récupérer le nom de la catégorie
            String nomCategorie = oeuvre.getCategorie().getNomCategorie();
            // Retourner le nom de la catégorie
            return new SimpleStringProperty(nomCategorie);
        });
    }
    private void initializeArtisteColumn() {

        artisteColumn.setCellValueFactory(cellData -> {
            // Accéder à l'objet OeuvreArt correspondant à la ligne
            OeuvreArt oeuvre = cellData.getValue();
            // Récupérer le nom et le prénom de l'artiste
            String nomArtiste = oeuvre.getArtiste().getNom();
            String prenomArtiste = oeuvre.getArtiste().getPrenom();
            // Concaténer le nom et le prénom de l'artiste
            String nomCompletArtiste = nomArtiste + " " + prenomArtiste;
            // Retourner le nom complet de l'artiste
            return new SimpleStringProperty(nomCompletArtiste);
        });
    }

    @FXML
    public void initialize() {
        // Récupérer les œuvres d'art depuis la base de données
        ObservableList<OeuvreArt> oeuvresList = null;
        try {
            oeuvresList = FXCollections.observableArrayList(oeuvreArtService.AfficherOeuvreArt());
            System.out.println("Œuvres d'art récupérées avec succès !");
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la récupération des œuvres d'art : " + e.getMessage());
            throw new RuntimeException(e);
        }

        // Ajouter les données dans le TableView
        oeuvresTableView.setItems(oeuvresList);

        // Configurer les colonnes du TableView
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie.nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateAjoutColumn.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));
        prixVenteColumn.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        initializeCategorieColumn();
        initializeArtisteColumn();
        // Configurer la colonne "Action"
        actionColumn.setCellFactory(param -> new ButtonCellOeuvre(this));


    }
    public void afficherOeuvreArt() {
        try {
            if (oeuvreArtService != null) {
                List<OeuvreArt> oeuvreArts = oeuvreArtService.AfficherOeuvreArt();
                ObservableList<OeuvreArt> oeuvreArtObservableList = FXCollections.observableArrayList(oeuvreArts);
                oeuvresTableView.setItems(oeuvreArtObservableList);
                System.out.println("Oeuvres arts affichées avec succès : " + oeuvreArts.size());
            } else {
                System.err.println("oeuvreArtService n'est pas initialisé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

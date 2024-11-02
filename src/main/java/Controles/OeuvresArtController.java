package Controles;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import models.Categorie;
import models.OeuvreArt;
import org.controlsfx.control.Notifications;
import services.categorie.CategorieService;
import services.oeuvreArt.OeuvreArtService;

import java.sql.SQLException;
import java.util.List;

public class OeuvresArtController {

    @FXML
    private TableColumn<OeuvreArt, Void> actionColumn;
    @FXML
    private ComboBox<String> categorieComboBox;
    @FXML
    private TableView<OeuvreArt> oeuvresTableView;
    @FXML
    private TableColumn<OeuvreArt, String> categorieColumn;
    private CategorieService categorieService;
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
            OeuvreArt oeuvre = cellData.getValue();
            String nomCategorie = oeuvre.getCategorie().getNomCategorie();
            return new SimpleStringProperty(nomCategorie);
        });
    }

    private void initializeArtisteColumn() {
        artisteColumn.setCellValueFactory(cellData -> {
            OeuvreArt oeuvre = cellData.getValue();
            String nomArtiste = oeuvre.getArtiste().getNom();
            String prenomArtiste = oeuvre.getArtiste().getPrenom();
            String nomCompletArtiste = nomArtiste + " " + prenomArtiste;
            return new SimpleStringProperty(nomCompletArtiste);
        });
    }

    @FXML
    public void initialize() {

        categorieService = new CategorieService();
        try {
            List<Categorie> categories = categorieService.AfficherCategorie();
            ObservableList<String> categoryNames = FXCollections.observableArrayList();
            categoryNames.add("Tous"); // Ajout de l'option "Tous"
            for (Categorie categorie : categories) {
                categoryNames.add(categorie.getNomCategorie());
            }
            categorieComboBox.setItems(categoryNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            List<OeuvreArt> oeuvresList = oeuvreArtService.AfficherOeuvreArt();
            ObservableList<OeuvreArt> oeuvreArtObservableList = FXCollections.observableArrayList(oeuvresList);
            oeuvresTableView.setItems(oeuvreArtObservableList);
            System.out.println("Oeuvres d'art récupérées avec succès !");
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de la récupération des œuvres d'art : " + e.getMessage());
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateAjoutColumn.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));
        prixVenteColumn.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        imageColumn.setCellValueFactory(cellData -> {
            String imageName = cellData.getValue().getImage();
            String imageUrl = "file:///C:/Users/LENOVO/Desktop/Esprit-2024/PIDEV/Partie_Symfony/Art_Vue_Symfony/public/oeuvre/" + imageName;
            return new SimpleStringProperty(imageUrl);
        });

        imageColumn.setCellFactory(param -> new ImageCell());
        initializeCategorieColumn();
        initializeArtisteColumn();
        actionColumn.setCellFactory(param -> new ButtonCellOeuvre(this));
    }

    public void afficherOeuvreArt() {
        try {
            List<OeuvreArt> oeuvreArts = oeuvreArtService.AfficherOeuvreArt();
            ObservableList<OeuvreArt> oeuvreArtObservableList = FXCollections.observableArrayList(oeuvreArts);
            oeuvresTableView.setItems(oeuvreArtObservableList);
            System.out.println("Oeuvres arts affichées avec succès : " + oeuvreArts.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void afficher (){
        String selectedCategory = categorieComboBox.getValue();
    }

    public void handleCategorySelection(ActionEvent actionEvent) {
        String selectedCategory = categorieComboBox.getValue();
        if ("Tous".equals(selectedCategory)) {
            afficherOeuvreArt(); // Afficher toutes les œuvres d'art
        } else if (selectedCategory != null) {
            try {
                List<OeuvreArt> oeuvresByCategory = oeuvreArtService.getAllOeuvreArtByCategorie(selectedCategory);
                ObservableList<OeuvreArt> oeuvreArtObservableList = FXCollections.observableArrayList(oeuvresByCategory);
                oeuvresTableView.setItems(oeuvreArtObservableList);
                System.out.println("Oeuvres arts filtrées par catégorie affichées avec succès : " + oeuvresByCategory.size());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void printPDF(ActionEvent event) {
        System.out.println("Print PDF button clicked");
        ObservableList<OeuvreArt> oeuvres = oeuvresTableView.getItems();
        System.out.println("Nombre d'oeuvres : " + oeuvres.size());
        PDFGeneratorNour.generatePDF(oeuvres);
        Image image1 = new Image("/image/succes.png");
        ImageView imageView = new ImageView(image1);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        Notifications notifications = Notifications.create();
        notifications.graphic(imageView);
        notifications.text("Votre tableau des oeuvres d'art a été imprimé avec succès.");
        notifications.title("Success Message");
        notifications.hideAfter(Duration.seconds(4.0));
        notifications.show();
    }
}

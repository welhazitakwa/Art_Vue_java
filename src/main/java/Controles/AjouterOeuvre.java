package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Categorie;
import models.OeuvreArt;
import models.Utilisateur;
import services.categorie.CategorieService;
import services.oeuvreArt.OeuvreArtService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AjouterOeuvre {

    @FXML
    private TextField titreField;

    @FXML
    private TextField descriptionField;

    @FXML
    private ComboBox<String> categorieComboBox; // Modification du type de ComboBox

    @FXML
    private TextField prixField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField imageField;

    @FXML
    private Button Ajouter;

    private CategorieService categorieService;

    @FXML
    void initialize() {
        // Initialiser la date par défaut à aujourd'hui
        datePicker.setValue(LocalDate.now());

        categorieService = new CategorieService();

        try {
            // Récupérer les catégories depuis la base de données
            List<Categorie> categories = categorieService.AfficherCategorie();

            // Définir les noms de catégorie dans la ComboBox
            for (Categorie categorie : categories) {
                categorieComboBox.getItems().add(categorie.getNomCategorie());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception en conséquence
        }

        
    }

    public void AjouterOeuvre(ActionEvent actionEvent) {
        // Récupérer les valeurs des champs de texte et de la combobox
        String titre = titreField.getText();
        String description = descriptionField.getText();
        String categorieNom = categorieComboBox.getValue(); // Récupérer le nom de la catégorie
        float prixVente = Float.parseFloat(prixField.getText());
        String image = imageField.getText();
        LocalDate localDate = datePicker.getValue();
        Date dateAjout = java.sql.Date.valueOf(localDate);

        try {
            // Récupérer l'objet Catégorie
            CategorieService categorieService = new CategorieService();
            Categorie categorieObj = categorieService.getCategorieByNom(categorieNom);

            // Récupérer l'artiste
            Utilisateur artiste = new Utilisateur();
            artiste.setId(1);

            // Créer une nouvelle instance de OeuvreArt
            OeuvreArt oeuvreArt = new OeuvreArt(image, titre, description, dateAjout, prixVente, categorieObj, "disponible", artiste);

            // Ajouter l'oeuvre d'art à la base de données
            OeuvreArtService oeuvreArtService = new OeuvreArtService();
            oeuvreArtService.AjouterOeuvreArt(oeuvreArt);

            // Afficher un message de succès ou effectuer d'autres actions nécessaires
            System.out.println("L'oeuvre d'art a ete ajoutee avec succes.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception en conséquence
        }

    }}

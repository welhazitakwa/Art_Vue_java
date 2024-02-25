package Controles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;


public class AdminDashboard implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox contentArea;

    @FXML
    private Button idBTNCategorie;
    @FXML
    private Label parametreField;
    @FXML
    private Button btnExit;
    @FXML
    private ComboBox<?> comboBoxOptions;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*btnExit.setOnMouseClicked(e->{
            System.exit(0);
        });
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll();
        }
        catch (IOException ex){
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE , null , ex);
        }*/


    }








    public void setParametre(String parametre) {
        parametreField.setText(parametre);
    }
    @FXML
    void pageCategorie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CategoriePage.fxml"));
            Parent categoriePage = loader.load();
            // Si nécessaire, vous pouvez également obtenir le contrôleur de la page de catégorie
            CategoriePage categorieController = loader.getController();

            // Effacez le contenu existant et affichez la page de catégorie
            contentArea.getChildren().clear();
            contentArea.getChildren().add(categoriePage);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void pageDashboard(ActionEvent actionEvent) {
        // Charger le contenu de la page 2
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("Contenu de la page 2"+ "   l'id de l'utilisateur connecté "+parametreField.getText()));
    }


    @FXML
    void pageUtilisateurs(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UtilisateurDashboard.fxml"));
            Parent UtilisateurPage = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(UtilisateurPage);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void comboBox(ActionEvent event) {
        ObservableList<String> items = FXCollections.observableArrayList("Modifier Profil", "Déconnexion");
        ChoiceBox<String> choiceBox = new ChoiceBox<>(items);

        // Gérer la sélection d'un élément dans la ChoiceBox
        choiceBox.setOnAction(e -> {
            String selectedOption = choiceBox.getValue();
            System.out.println("Option sélectionnée : " + selectedOption);

            // Ajoutez ici la logique pour gérer chaque option (Modifier Profil, Déconnexion)
            if ("Modifier Profil".equals(selectedOption)) {
                // Logique pour "Modifier Profil"
                System.out.println("Action : Modifier Profil");
            } else if ("Déconnexion".equals(selectedOption)) {
                // Logique pour "Déconnexion"
                System.out.println("Action : Déconnexion");
            }

            // Fermer la fenêtre contextuelle après la sélection
            ((Popup) choiceBox.getScene().getWindow()).hide();
        });

        // Créer une VBox pour contenir la ChoiceBox
        VBox container = new VBox();
        container.getChildren().add(choiceBox);

        // Créer une fenêtre contextuelle (Popup) et y ajouter la VBox
        Popup popup = new Popup();
        popup.getContent().add(container);

        // Obtenez la scène à partir de l'événement
        if (event.getSource() instanceof javafx.scene.Node) {
            javafx.scene.Node sourceNode = (javafx.scene.Node) event.getSource();
            popup.show(sourceNode.getScene().getWindow());
        } else {
            System.err.println("L'événement ne provient pas d'un élément de l'interface utilisateur.");
        }
    }


    public void traiterSelectionComboBox(ActionEvent actionEvent) {

        String optionSelectionnee = (String) comboBoxOptions.getValue();
        if (optionSelectionnee.equals("Déconnexion")) {
            System.out.println(" deconnecté ");
        } else if (optionSelectionnee.equals("Modifier le profil")) {
            System.out.println("profill ");
        }
    }
}


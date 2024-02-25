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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;


public class AdminDashboard implements Initializable {
    @FXML
    private BorderPane BoderPaneContainer;
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
    private ComboBox<String> comboBoxOptions;


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


    public void traiterSelectionComboBox(ActionEvent actionEvent) {


        if (comboBoxOptions != null && comboBoxOptions.getValue() != null) {
            String optionSelectionnee = (String) comboBoxOptions.getValue();
            if ("Déconnexion".equals(optionSelectionnee)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
                    Parent registerParent = loader.load();
                    BoderPaneContainer.getChildren().clear();  // Use clear() instead of removeAll()
                    BoderPaneContainer.getChildren().add(registerParent);
                } catch (IOException e) {
                    e.printStackTrace();  // Handle the exception appropriately (log or show an error message)
                }
            } else if ("Modifier le profil".equals(optionSelectionnee)) {
                System.out.println("Profil");
            }
        } else {
            System.out.println("Le ComboBox n'est pas correctement initialisé ou aucune valeur n'est sélectionnée.");
        }
    }
}


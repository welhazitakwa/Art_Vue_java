package Controles;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


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
    private Button id_pageconcoursmenu;

    @FXML
    private Label parametreField;
    @FXML
    private Button btnExit;
    @FXML
    private ComboBox<String> comboBoxOptions;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            pageDashboard(null);


    }




    public void setParametre(String parametre) {
        parametreField.setText(parametre);
    }
    @FXML
    void pageCategorie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/CategoriePage.fxml"));
            Parent categoriePage = loader.load();
            CategoriePage categorieController = loader.getController();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(categoriePage);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML

    public void pageDashboard(ActionEvent actionEvent) {

        // Charger le contenu de la page 2
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("Contenu de la page 2"+ "   l'id de l'utilisateur connecté "+parametreField.getText()));
    }




    public void to_ouevre_art(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/OeuvreArtPage.fxml"));
            Parent OeuvreArtPage = loader.load();
            OeuvresArtController oeuvresArtController = loader.getController();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(OeuvreArtPage);

        } catch (IOException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void pageconcoursmenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Concours.fxml"));
            Parent Concours1 = loader.load();

           Concours1 ConcoursController = loader.getController();

            // Effacez le contenu existant et affichez la page de catégorie
            contentArea.getChildren().clear();
            contentArea.getChildren().add(Concours1);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);

        }
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
            }
            else if ("Modifier le profil".equals(optionSelectionnee)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/modifierProfil.fxml"));
                    Parent UtilisateurPage = loader.load();
                    ModifierProfil modifierProfil = loader.getController();
                    modifierProfil.setParametre(parametreField.getText());
                    modifierProfil.initialize(null,null);
                    contentArea.getChildren().clear();

                    contentArea.getChildren().add(UtilisateurPage);
                } catch (IOException ex) {
                    Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }



            else if ("Consulter mon compte".equals(optionSelectionnee)) {
                /***********************************************************************/
                Stage detailsSatge = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                double X = detailsSatge.getX();
                double Y = detailsSatge.getY();
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userDetails.fxml"));
                    Parent root = loader.load() ;

                    ScaleTransition st = new ScaleTransition(Duration.millis(50),root);
                    st.setInterpolator(Interpolator.EASE_BOTH);
                    st.setFromX(0);
                    st.setFromY(0);
                    st.setToX(1);
                    st.setToY(1);

                    Stage stage = new Stage() ;
                    Scene scene= new Scene(root);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    stage.setX(X + 350);
                    stage.setY(Y + 150);
                    UserDetails udcontroller = loader.getController();
                    udcontroller.setParametre(parametreField.getText());
                    udcontroller.initialize(null,null);
                } catch (Exception e) {
                    e.printStackTrace();

                }
                /***********************************************************************/
            }
        } else {
            System.out.println("Le ComboBox n'est pas correctement initialisé ou aucune valeur n'est sélectionnée.");
        }
    }
}


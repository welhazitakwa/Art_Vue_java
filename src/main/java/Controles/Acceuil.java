package Controles;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acceuil {

    @FXML
    private Button BtnToOeuvreArt;
    private int idClient ;
    @FXML
    private ComboBox<String> comboBoxOptions;

    public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de l'client connecté : " + idClient);
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

    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
           // FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOeuvre.fxml"));
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOeuvre.fxml"));
            Parent registerParent = loader.load();
            PageOeuvre pageOeuvre = loader.getController();
            pageOeuvre.setParametre(idClient); // Passage de l'ID au contrôleur AcceuilArtiste
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

    public void To_Apropos(ActionEvent event) {
        loadNewPage("/fxml/fxmlClient/Apropos.fxml", event);

    }

    public void topropos(ActionEvent event) {
        loadNewPage("/fxml/fxmlClient/Apropos.fxml", event);
    }

    public void traiterSelectionComboBox(ActionEvent actionEvent) {

        if (comboBoxOptions != null && comboBoxOptions.getValue() != null) {
            String optionSelectionnee = (String) comboBoxOptions.getValue();
            if ("Déconnexion".equals(optionSelectionnee)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
                    Parent registerParent = loader.load();
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(registerParent));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if ("Modifier le profil".equals(optionSelectionnee)) {
//
                Stage detailsSatge = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                double X = detailsSatge.getX();
                double Y = detailsSatge.getY();
                try{
                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/modifierCompteClient.fxml"));
                    Parent root2 = loader2.load() ;
                    ScaleTransition st = new ScaleTransition(Duration.millis(50),root2);
                    st.setInterpolator(Interpolator.EASE_BOTH);
                    st.setFromX(0);
                    st.setFromY(0);
                    st.setToX(1);
                    st.setToY(1);
                    Stage stage = new Stage() ;
                    Scene scene= new Scene(root2);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    stage.setX(X + 200);
                    stage.setY(Y + 50);

                    ModifierCompteClient modifierProfil = loader2.getController();
                    modifierProfil.setParametre(String.valueOf(idClient));
                    modifierProfil.initialize(null,null);
                } catch (Exception e) {
                    e.printStackTrace();

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
                    udcontroller.setParametre(String.valueOf(idClient));
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

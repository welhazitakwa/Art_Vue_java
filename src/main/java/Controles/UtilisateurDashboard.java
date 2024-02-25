package Controles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Utilisateur;
import services.utilisateur.UtilisateurService;
import javafx.scene.control.Label;
//import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurDashboard implements Initializable {
    @FXML
    private ResourceBundle resources;
    @FXML
    private ComboBox<String> comboBoxOptions;

    @FXML
    private URL location;
    @FXML
    private Label txt1;

    @FXML
    private Label txt2;

    @FXML
    private Label txt3;

    @FXML
    private Label txt4;
    @FXML
    private VBox usersLayout;
    @FXML
    private ImageView userImg;
    @FXML
    private HBox hbox2;

    @FXML
    private HBox hbox3;
    @FXML
    private HBox navbar;

    @FXML
    void AffichageClients(ActionEvent event) {
        txt1.setText(" ");
        txt2.setText(" ");
        txt3.setText(" ");
        txt4.setText(" ");

        UtilisateurService user1 = new UtilisateurService();
        try {
            List <Utilisateur> users = user1.listClients() ;
            System.out.println(users);
            usersLayout.getChildren().clear();

            for (int i=0; i<users.size();i++) {
                System.out.println(users.size());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/userItem.fxml"));
                HBox hbox = fxmlLoader.load();
                UserItem uicontroller = fxmlLoader.getController();
                uicontroller.setParametre(String.valueOf(users.get(i).getId()));
                uicontroller.setParametre2(users.get(i).getNom()+" "+users.get(i).getPrenom());

                uicontroller.setData(users.get(i));
                usersLayout.getChildren().add(hbox);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void affichageArtistes(ActionEvent event) {
        txt1.setText(" ");
        txt2.setText(" ");
        txt3.setText(" ");
        txt4.setText(" ");
        UtilisateurService user1 = new UtilisateurService();
        try {
            List <Utilisateur> users = user1.listAllArtistes() ;
            System.out.println(users);
            usersLayout.getChildren().clear();

            for (int i=0; i<users.size();i++) {
                System.out.println(users.size());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/userItem.fxml"));
                HBox hbox = fxmlLoader.load();
                UserItem uicontroller = fxmlLoader.getController();
                uicontroller.setParametre(String.valueOf(users.get(i).getId()));
                uicontroller.setParametre2(users.get(i).getNom()+" "+users.get(i).getPrenom());
                uicontroller.setData(users.get(i));
                usersLayout.getChildren().add(hbox);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        UtilisateurService user1 = new UtilisateurService();
//        try {
//            List <Utilisateur> users = user1.listAll() ;
//            System.out.println(users);
//            for (int i=0; i<users.size();i++) {
//                System.out.println(users.size());
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/fxml/userItem.fxml"));
//                HBox hbox = fxmlLoader.load();
//                UserItem uicontroller = fxmlLoader.getController();
//                uicontroller.setData(users.get(i));
//                usersLayout.getChildren().add(hbox);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//

        String optionSelectionnee = comboBoxOptions.getValue();
        System.out.println("Option sélectionnée : " + optionSelectionnee);

        // Ajouter ici la logique pour gérer chaque option (Modifier, Déconnecter)
        if ("Modifier".equals(optionSelectionnee)) {
            // Logique pour "Modifier"
            System.out.println("Action : Modifier");
        } else if ("Déconnecter".equals(optionSelectionnee)) {
            // Logique pour "Déconnecter"
            System.out.println("Action : Déconnecter");
        }
  }
}

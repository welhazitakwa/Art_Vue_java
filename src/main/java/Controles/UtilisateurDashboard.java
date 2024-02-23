package Controles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Utilisateur;
import services.utilisateur.UtilisateurService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UtilisateurDashboard implements Initializable {

    @FXML
    private ImageView userImg;

    @FXML
    private VBox usersLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UtilisateurService user1 = new UtilisateurService();
        try {
            List <Utilisateur> users = user1.listAll() ;
            System.out.println(users);
            for (int i=0; i<users.size();i++) {
                System.out.println(users.size());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/userItem.fxml"));
                HBox hbox = fxmlLoader.load();
                UserItem uicontroller = fxmlLoader.getController();
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
}

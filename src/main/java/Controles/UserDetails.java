package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class UserDetails  implements Initializable {

    double x,y;

    @FXML
    private Label adresse;

    @FXML
    private Label cin;

    @FXML
    private Label dateInscri;

    @FXML
    private Label dateNaissance;

    @FXML
    private Label genre;

    @FXML
    private Label login;

    @FXML
    private Label mail;

    @FXML
    private Label nomPrenom;

    @FXML
    private Label numTel;

    @FXML
    private Label profil;

    @FXML
    void closeBtn(ActionEvent event) {
        Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
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

    }
}





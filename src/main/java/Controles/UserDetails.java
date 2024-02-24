package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.utilisateur.UtilisateurService;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
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
    public Label label;
    private int id ;





    @FXML
    void closeBtn(ActionEvent event) {
        Stage stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       // System.out.println(passeId.getText());
//        nomPrenom.setText(label.getText());




    }
    public void setParametre(String parametre) {
        String vv = parametre ;
        label.setText(vv);
        id = Integer.parseInt(vv);
        System.out.println("laaaaaabbbbbbbeeeeellll"+label.getText());
        System.out.println("iiiiiidddddddddd"+id);
        try {
            UtilisateurService user1 = new UtilisateurService();

            nomPrenom.setText(user1.getUtilisateurById(id).getPrenom());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}





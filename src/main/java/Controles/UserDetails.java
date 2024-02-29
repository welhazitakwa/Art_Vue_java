package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.utilisateur.UtilisateurService;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
public class UserDetails  implements Initializable {

    double x,y;

    @FXML
    private ImageView imageRecup;

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



//regex
    }
    public void setParametre(String parametre) {
        String vv = parametre ;
        label.setText(vv);
        id = Integer.parseInt(vv);
        System.out.println("laaaaaabbbbbbbeeeeellll"+label.getText());
        System.out.println("iiiiiidddddddddd"+id);
        try {
            UtilisateurService user1 = new UtilisateurService();
            ImageView imgProfile = new ImageView(new Image(user1.getUtilisateurById(id).getImage()));

            imageRecup.setImage(imgProfile.getImage());
            nomPrenom.setText(user1.getUtilisateurById(id).getNom() + " "+ user1.getUtilisateurById(id).getPrenom() );
            genre.setText(user1.getUtilisateurById(id).getGenre());
            dateNaissance.setText(String.valueOf(user1.getUtilisateurById(id).getDateNaissance()));
            if (user1.getUtilisateurById(id).getProfil()==1) {
                profil.setText("Artiste");
            }
            else {
                profil.setText("Client");
            }
            dateInscri.setText(String.valueOf(user1.getUtilisateurById(id).getDate_inscription()));
            numTel.setText(String.valueOf(user1.getUtilisateurById(id).getNumTel()));
            mail.setText(user1.getUtilisateurById(id).getEmail());
            adresse.setText(user1.getUtilisateurById(id).getAdresse());
            login.setText(user1.getUtilisateurById(id).getLogin());
            cin.setText(String.valueOf(user1.getUtilisateurById(id).getCin()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}





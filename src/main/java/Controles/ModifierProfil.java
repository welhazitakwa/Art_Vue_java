package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.utilisateur.UtilisateurService;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModifierProfil implements Initializable {

    @FXML
    private TextField adresseEdit;
    private int id ;
    @FXML
    private TextField cinEdit;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField loginEdit;

    @FXML
    private TextField mailEdit;

    @FXML
    private TextField nomEdit;

    @FXML
    private TextField numTelEdit;

    @FXML
    private TextField prenomEdit;

    @FXML
    private Label recuperationId;

    @FXML
    void sauvegarderInfo(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setParametre(String parametre) {
        String vv = parametre ;
        //recuperationId.setText(vv);
        id = Integer.parseInt(vv);
       // System.out.println("laaaaaabbbbbbbeeeeellll"+recuperationId.getText());
        System.out.println("iiiiiidddddddddd"+id);
        System.out.println();
        try {
            UtilisateurService user1 = new UtilisateurService();

            nomEdit.setText(user1.getUtilisateurById(id).getNom());
            prenomEdit.setText( user1.getUtilisateurById(id).getPrenom() );
            adresseEdit.setText( user1.getUtilisateurById(id).getEmail());
            //            genre.setText(user1.getUtilisateurById(id).getGenre());
            cinEdit.setText(String.valueOf(user1.getUtilisateurById(id).getCin()));
            // Supposons que vous ayez un DatePicker appelé datePicker et un Label appelé dateLabel
            datePicker.setValue(user1.getUtilisateurById(id).getDateNaissance().toLocalDate());
            numTelEdit.setText(String.valueOf(user1.getUtilisateurById(id).getNumTel()));
            mailEdit.setText(user1.getUtilisateurById(id).getEmail());
            loginEdit.setText(user1.getUtilisateurById(id).getLogin());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Utilisateur;
import services.utilisateur.UtilisateurService;

import java.net.URL;
import java.sql.Date;
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
    private Label regerror;
    @FXML
    private ComboBox<String> comboGenre;

    @FXML
    void sauvegarderInfo(ActionEvent event) {
        UtilisateurService user1 = new UtilisateurService();
        try {
            user1.modifier(new Utilisateur(
                    id, nomEdit.getText(), prenomEdit.getText(), mailEdit.getText(),
                    Integer.parseInt(numTelEdit.getText()), loginEdit.getText(), Integer.parseInt(cinEdit.getText()), "cccccc ", comboGenre.getValue(),
                    Date.valueOf(datePicker.getValue()), adresseEdit.getText()));

                    Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                    dialog.setTitle("Alerte de Confirmation");
                    dialog.setHeaderText(" La modification est effectué avec succès ");
                    dialog.setContentText("Votre Compte est à jour");
                    dialog.showAndWait();



            } catch (SQLException s) {
                System.out.println(s.getMessage());
            }

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
            adresseEdit.setText( user1.getUtilisateurById(id).getAdresse());
            comboGenre.setValue(user1.getUtilisateurById(id).getGenre());
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

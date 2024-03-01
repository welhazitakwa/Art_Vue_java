package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import models.Utilisateur;
import services.utilisateur.UtilisateurService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.awt.Color.green;

public class Register {
    @FXML
    private AnchorPane contentRegister;

    @FXML
    private RadioButton artisteRadio;

    @FXML
    private RadioButton clientRadio;

    @FXML
    private PasswordField confirmpwd;

    @FXML
    private TextField email;

    @FXML
    private TextField login;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private PasswordField pwdd;

    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private Label regerror;

    @FXML
    void connecterButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent registerParent = loader.load();
            contentRegister.getChildren().clear();  // Use clear() instead of removeAll()
            contentRegister.getChildren().add(registerParent);
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception appropriately (log or show an error message)
        }
    }


    public static String validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "Adresse mail invalide !";
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            return "valide";
        } else {
            return "Adresse mail invalide !";
        }
    }


    @FXML
    void sinscrireButton(ActionEvent event) {
        UtilisateurService user1 = new UtilisateurService();
        Toggle selectedToggle = toggleGroup.getSelectedToggle();
        if (selectedToggle != null) {
            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String selectedValue = selectedRadioButton.getText();

        if ( nom.getText().equals("") || prenom.getText().equals("") || pwdd.equals("")||login.getText().equals("") ||
                email.getText().equals("")|| confirmpwd.getText().equals("") ) {
            regerror.setTextFill(Color.RED);
            regerror.setText("Assurez-vous de remplir toutes les données !");

        } else {
            regerror.setText(" ");
            int validLogin = 0;
            try {
                validLogin = user1.validateRegisterLoginMail(login.getText(),email.getText());
                            if (validLogin == 1) {
                                regerror.setTextFill(Color.RED);
                                regerror.setText("Ce login est déjà utilisé !");
                }
                else if (validLogin == 2) {
                                regerror.setTextFill(Color.RED);
                                regerror.setText("Cette adresse mail est déjà utilisé !");
                }
                else {
                    if (validateEmail(email.getText()).equals("Adresse mail invalide !")){
                        regerror.setTextFill(Color.RED);
                        regerror.setText("Merci d'utiliser un mail valide !");
                    } else {
                        if (pwdd.getText().equals(confirmpwd.getText())) {
                        try {
                            user1.register(new Utilisateur(nom.getText(),prenom.getText(),email.getText(),login.getText(),pwdd.getText()),selectedValue);
                        } catch (SQLException e) {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR);
                            alert2.setContentText(e.getMessage());
                            alert2.show();
                        }
                        regerror.setTextFill(Color.GREEN);
                        regerror.setText(" Vous pouvez connecter maintenant ! ");
                    }


                    else {
                            regerror.setTextFill(Color.RED);
                            regerror.setText(" Essayez de reconfirmer votre mot de passe !");

                    }}









                }

                } catch (SQLException e) {
                throw new RuntimeException(e);}


        }
        } else {
            System.out.println("Aucun RadioButton sélectionné.");
            regerror.setText("Vous êtes un Artiste ou un Client ?");

        }

    }

}

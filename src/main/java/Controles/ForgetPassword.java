package Controles;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import services.utilisateur.UtilisateurService;

public class ForgetPassword {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EmailField;

    @FXML
    private Label ErrorLabel;

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
    void Valider(ActionEvent event) {
        UtilisateurService user1 = new UtilisateurService();
        try {


                if (user1.checkEmail(EmailField.getText())==0) {


                    if (validateEmail(EmailField.getText()).equals("Adresse mail invalide !")){
                        ErrorLabel.setText("Merci d'utiliser un mail valide !");} else {
                        ErrorLabel.setText("Aucun compte trouvé avec l'email fourni !");
                    }



                } else {
                    ErrorLabel.setText(" ");

                    ErrorLabel.setText("jawek behi t3ada");
                }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {

    }

}

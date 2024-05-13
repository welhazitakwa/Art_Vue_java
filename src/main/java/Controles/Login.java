package Controles;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.utilisateur.UtilisateurService;

public class Login {
    @FXML
    private AnchorPane contentArea;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField mdpTextField;
    @FXML
    private Label labelError;
    @FXML
    private TextField validateCaptcha;
    @FXML
    private TextField captchaCodeValue;
    private String a = "";
    public static String createCaptchaValue(){
        Random random = new Random();
        int length=7+(Math.abs(random.nextInt()) % 3);
        StringBuffer captchaStrBuffer = new StringBuffer() ;
        for (int i=0; i<length;i++) {
            int baseCharacterNumber = Math.abs(random.nextInt()%62);
            int characterNumber = 0 ;
            if(baseCharacterNumber < 26) {
                characterNumber= 65 + baseCharacterNumber ;
            } else if (baseCharacterNumber < 52) {
                characterNumber = 97 + (baseCharacterNumber - 26);
            }else {
                characterNumber = 48 + (baseCharacterNumber-52);
            }
            captchaStrBuffer.append((char)characterNumber);
        }
        return captchaStrBuffer.toString();
    }
    @FXML
    void seConnecter(ActionEvent event) throws SQLException {
        UtilisateurService user1 = new UtilisateurService();
        if (validateCaptcha.getText().equals(a)) {
            System.out.println("haw el a :" + a);
            System.out.println(" s7iiii7");


        int validLogin = 5;
        try {
            validLogin = user1.validateLogin(loginTextField.getText(), mdpTextField.getText());
          //  System.out.println(loginTextField.getText());
          //  System.out.println(mdpTextField.getText());

        } catch (SQLException e) {
            if (validateCaptcha.getText().equals(a)) {
                System.out.println("haw el a :" + a);
                System.out.println(" s7iiii7");

            }else {
                labelError.setText("CAPTCHA code invalide !");
                System.out.println("mouuuchhhh s7iiii7");
            }
        }
        if (validLogin == 0) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("c'est un admin"+" l'id te3ou : "+ user1.getIdUserConnected(loginTextField.getText(), mdpTextField.getText()));
//            alert.show();
//            labelError.setText(" ");


            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/AdminDashboard.fxml"));
                Parent registerParent = loader.load();
                AdminDashboard adminDashController = loader.getController();
                adminDashController.setParametre( String.valueOf(user1.getIdUserConnected(loginTextField.getText(), mdpTextField.getText())));
                contentArea.getChildren().clear();  // Use clear() instead of removeAll()
                contentArea.getChildren().add(registerParent);
            } catch (IOException e) {
                e.printStackTrace();  // Handle the exception appropriately (log or show an error message)
            }

        }
        else if (validLogin == 1) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/AcceuilArtiste.fxml"));
                Parent registerParent = loader.load();
                AcceuilArtiste acceuilArtiste = loader.getController();
                int idArtiste = user1.getIdUserConnected(loginTextField.getText(), mdpTextField.getText()); // Récupération de l'ID
                acceuilArtiste.setParametre(idArtiste); // Passage de l'ID au contrôleur AcceuilArtiste
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(registerParent));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (validLogin == 2) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/Acceuil.fxml"));
                Parent registerParent = loader.load();
                Acceuil acceuilClient = loader.getController();
                int idClient = user1.getIdUserConnected(loginTextField.getText(), mdpTextField.getText()); // Récupération de l'ID
                acceuilClient.setParametre(idClient); // Passage de l'ID au contrôleur AcceuilArtiste
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(registerParent));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            labelError.setText("Merci de vérifier vos données !");
        }
        }else {
            labelError.setText("CAPTCHA code invalide !");
            System.out.println("mouuuchhhh s7iiii7");
        }
    }
    @FXML
    void forgetBtn(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/forgetPassword.fxml"));
            Parent registerParent = loader.load();
            contentArea.getChildren().clear();  // Use clear() instead of removeAll()
            contentArea.getChildren().add(registerParent);
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception appropriately (log or show an error message)
        }
    }

    @FXML
    void registerBtn(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
            Parent registerParent = loader.load();
            contentArea.getChildren().clear();  // Use clear() instead of removeAll()
            contentArea.getChildren().add(registerParent);
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception appropriately (log or show an error message)
        }

    }

    @FXML
    void initialize() {
        a = createCaptchaValue();
        captchaCodeValue.setText(a);

    }

}

package Controles;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

public class OTP {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    private String mail;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField hide;

    @FXML
    private TextField verifOTP;
    public void Random (){
        Random rd = new Random();
        hide.setText(""+rd.nextInt(10000+1));
    }
    @FXML
    void valider(ActionEvent event) {
            if(hide.getText().equals(verifOTP.getText())){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RecupMdp.fxml"));
                    Parent registerParent = loader.load();
                    RecupMdp recupMdp = loader.getController();
                    recupMdp.setParametre(mail); // Passage de l'ID au contrôleur AcceuilArtiste
                    recupMdp.initialize();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(registerParent));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                errorLabel.setText("OTP incorrecte ! ");
            }
    }
    public void setParametre(String text) {
        mail = text ;
        System.out.println("mail fel OTP mdp : " + mail);

    }

    public void sendOTP() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.user", "artvuecontact@gmail.com");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.debug", true);
        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", false);

        try {
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setText("Votre OTP est : " + hide.getText());
            message.setSubject("OTP pour récupérer votre mot de passe");
            message.setFrom(new InternetAddress("artvuecontact@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.mail));
            message.saveChanges();

            try {
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", "artvuecontact@gmail.com", "nbqr tqqk tdbw wkxs");
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();

                Alert dialogC = new Alert(Alert.AlertType.INFORMATION);
                dialogC.setTitle(" Confirmation d'envoi OTP ");
                dialogC.setHeaderText("Votre OTP était envoyé");
                dialogC.setContentText("Vous pouvez Consulter votre boite mail");
                dialogC.show();

                //JOptionPane.showMessageDialog(null,"OTP has send to your Email id");
            } catch (Exception e) {
                Alert dialogC = new Alert(Alert.AlertType.ERROR);
                dialogC.setTitle(" Echec d'envoi ");
                dialogC.setHeaderText("Votre OTP n'était pas envoyé");
                dialogC.setContentText("Vérifiez votre connexion internet");
                dialogC.show();
            }

        } catch (Exception e) {
            System.out.println("echec");
        }
        }

        @FXML
        void initialize () {
            Random();
            System.out.println(hide.getText());
            System.out.println("mail fel otp 2 initialise hihi : " + this.mail);
            sendOTP();
        }
    }




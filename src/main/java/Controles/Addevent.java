package Controles;

import models.evenement;
import services.EventService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class  Addevent {
    @FXML
    private Label ccnom;
    @FXML
    private Label cclieu;
    @FXML
    private Label ccdate;
    @FXML
    private Label ccprix;
    @FXML
    private Label cccap;
    @FXML
    private TextField nom;
    @FXML
    private DatePicker date;
    @FXML
    private TextField lieu;
    @FXML
    private TextField prix;
    @FXML
    private TextField cap;
    int id;
    public void setId(int id){
        this.id=id;
    }
    public void retour(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/AfficheEventAdmin.fxml"));
        Parent root=loader.load();
        date.getScene().setRoot(root);
    }

    public void ajout(ActionEvent actionEvent) throws SQLException {
        int t=0;
        EventService e=new EventService();
        if (nom.getText().isEmpty() || !nom.getText().matches("[a-zA-Z]+")) {
            t = 1;
            this.ccnom.setText("Nom invalide");
        } else {
            this.ccnom.setText("");
        }
        if (lieu.getText().isEmpty() || !lieu.getText().matches("[a-zA-Z]+")) {
            t = 1;
            this.cclieu.setText("lieu invalide");
        } else {
            this.cclieu.setText("");
        }
        if (prix.getText().isEmpty() || !prix.getText().matches("^\\d+$")) {
            t = 1;
            this.ccprix.setText("Prix invalide");
        } else if (Integer.parseInt(prix.getText()) == 0) {
            t = 1;
            this.ccprix.setText("Le prix ne peut pas être 0");
        } else {
            this.ccprix.setText("");
        }
        if (cap.getText().isEmpty() || !cap.getText().matches("^\\d+$")) {
            t = 1;
            this.cccap.setText("Capacité invalide");
        } else if (Integer.parseInt(cap.getText()) == 0) {
            t = 1;
            this.cccap.setText("La Capacité ne peut pas être 0");
        } else {
            this.cccap.setText("");
        }
        if (date.getValue() == null) {
            t = 1;
            this.ccdate.setText("Date  manquante");
        } else {
            this.ccdate.setText("");
        }
        if(t==0){
            Date ddate = java.sql.Date.valueOf(date.getValue());
                evenement ee = new evenement(nom.getText(),lieu.getText(),ddate,id,Float.parseFloat(prix.getText()),Integer.parseInt(cap.getText()));
               int p=e.ajouterEvent(ee,id);
               if(p==1){
                   Notifications n = Notifications.create()
                           .title("Félicitations !")
                           .text("Ajouté avec succès")
                           .hideAfter(Duration.seconds(5))
                           .position(Pos.CENTER_RIGHT)
                           .darkStyle();
                   n.showInformation();

               }else {
                   Notifications n2 = Notifications.create()
                           .title("Erreur")
                           .text("Désolé, l'ajout a échoué")
                           .hideAfter(Duration.seconds(5))
                           .position(Pos.CENTER_RIGHT)
                           .darkStyle();
                   n2.showError();

               }
            }

            }

    }


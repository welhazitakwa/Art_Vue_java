package Controles;

import models.Calendar;
import services.calenderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class Addcalender {
    @FXML
    private Button okbtn;
    @FXML
    private Button retourbtn;
    @FXML
    private Label ccnom;
    @FXML
    private Label ccdated;
    @FXML
    private Label ccdatef;
    @FXML
    private TextField nom;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;

    public void ajoutercalender(ActionEvent actionEvent) throws SQLException {
        int t=0;
        calenderService cs =new calenderService();
        if (nom.getText().isEmpty() || !nom.getText().matches("[a-zA-Z]+")) {
            t = 1;
            this.ccnom.setText("Nom invalide");
        } else {
            this.ccnom.setText("");
        }
        if (datedebut.getValue() == null) {
            t = 1;
            this.ccdated.setText("Date de début manquante");
        } else {
            this.ccdated.setText("");
        }
        if (datefin.getValue() == null) {
            t = 1;
            this.ccdatef.setText("Date de fin manquante");
        } else {
            this.ccdatef.setText("");
        }
        if(t==0){
            Date debut = java.sql.Date.valueOf(datedebut.getValue());
            Date fin = java.sql.Date.valueOf(datefin.getValue());
            if (datedebut.getValue().isAfter(datefin.getValue())) {
                Notifications n2 = Notifications.create()
                        .title("Erreur")
                        .text("La date de début doit être antérieure à la date de fin")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.CENTER_RIGHT)
                        .darkStyle();
                n2.showInformation();

            }else {
                Calendar c = new Calendar(nom.getText(), debut, fin);
                cs.ajoutercalender(c);
                Notifications n = Notifications.create()
                        .title("Félicitations !")
                        .text("Ajouté avec succès")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.CENTER_RIGHT)
                        .darkStyle();

                n.showConfirm();
            }

        }
    }

    public void retour(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/AfficheCalender.fxml"));
        Parent root=loader.load();
        datefin.getScene().setRoot(root);
    }
}
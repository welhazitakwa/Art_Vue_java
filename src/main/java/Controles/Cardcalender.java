package Controles;

import models.Calendar;
import services.calenderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;

public class  Cardcalender {
    @FXML
    private Label nom;
    @FXML
    private Label datedebut;
    @FXML
    private Label datefin;
    private  int id;

    public void setId(int id) {
        this.id = id;
    }
    public void setdata(Calendar c){
        nom.setText(c.getName());
        datedebut.setText(c.getStartdate().toString());
        datefin.setText(c.getEnddate().toString());
    }
    public void edit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Modifiercalender.fxml"));
        Parent root=loader.load();
        Modifiercalender mc=loader.getController();
        mc.setdata(id);
        mc.setId(id);
        datefin.getScene().setRoot(root);
    }

    public void supprimer(ActionEvent actionEvent) throws SQLException, IOException {
        int t=0;
        calenderService cs =new calenderService();
        t=cs.supprimercalender(id);
        if (t==1){
            Notifications n = Notifications.create()
                    .title("Félicitations !")
                    .text("Suppression avec succès")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER_RIGHT)
                    .darkStyle();
            n.showConfirm();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/AfficheCalender.fxml"));
            Parent root=loader.load();
            datefin.getScene().setRoot(root);
        }else{
            Notifications n2 = Notifications.create()
                    .title("Erreur")
                    .text("Désolé, la suppression a échoué")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER_RIGHT)
                    .darkStyle();
            n2.showError();

        }
    }

    public void add(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Addevent.fxml"));
        Parent root=loader.load();
        Addevent a=loader.getController();
        a.setId(id);
        datefin.getScene().setRoot(root);
    }
}

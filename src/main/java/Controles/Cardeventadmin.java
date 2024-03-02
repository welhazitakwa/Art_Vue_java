package Controles;

import models.evenement;
import services.EventService;
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

public class  Cardeventadmin {
    @FXML
    private Label nomevent;
    @FXML
    private Label nomcalender;
    @FXML
    private Label lieu;
    @FXML
    private Label date;
    @FXML
    private Label prix;
    @FXML
    private Label capa;
    private  int id;

    public void setId(int id) {
        this.id = id;
    }
    public void setdata(evenement e){
        nomevent.setText(e.getNom());
        nomcalender.setText(e.getCalendername());
        lieu.setText(e.getLieu());
        date.setText(e.getDate().toString());
        prix.setText(String.valueOf(e.getPrice()));
        capa.setText(String.valueOf(e.getCapacite()));
    }
    public void edit(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Modifierevent.fxml"));
        Parent root=loader.load();
        Modifierevent mc=loader.getController();
        mc.setdata(id);
        mc.setId(id);
        capa.getScene().setRoot(root);
    }

    public void delete(ActionEvent actionEvent) throws SQLException, IOException {
        EventService es=new EventService();
        int t=es.supprimerEvent(id);
        if (t==1){
            Notifications n = Notifications.create()
                    .title("Félicitations !")
                    .text("Suppression avec succès")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER_RIGHT)
                    .darkStyle();
            n.showConfirm();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/AfficheEventAdmin.fxml"));
            Parent root=loader.load();
            capa.getScene().setRoot(root);
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

    public void voir(ActionEvent actionEvent) throws SQLException, IOException {
        EventService es=new EventService();
        int t=es.nombredeutulisateurparevent(id);
        if(t==0){
            Notifications n2 = Notifications.create()
                    .title("Info")
                    .text("Désolé, Cette evenement n'a pas de utulisateur inscrit")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER_RIGHT)
                    .darkStyle();
            n2.showConfirm();
        }else{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Eventuser.fxml"));
            Parent root=loader.load();
            Eventuser mc=loader.getController();
            mc.setdata(id);
            mc.setId(id);
            capa.getScene().setRoot(root);
        }
    }
}

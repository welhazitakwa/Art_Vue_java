package Controles;

import models.evenement;
import services.EventService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficheEventAdmin implements Initializable {
    @FXML
    private VBox vbox;


    public void  gotocalendrier(ActionEvent actionEvent) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/AfficheCalender.fxml"));
        Parent root=loader.load();
        vbox.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<evenement> list;
        EventService es=new EventService();
        try {
            list=es.listAllEventcalender();
            for(int i=0;i<list.size();i++){
                FXMLLoader fxl=new FXMLLoader();
                fxl.setLocation(getClass().getResource("/fxml/Cardeventadmin.fxml"));
                Parent root=fxl.load();
                Cardeventadmin c=fxl.getController();
                c.setdata(list.get(i));
                c.setId(list.get(i).getId());
                vbox.getChildren().add(root);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }


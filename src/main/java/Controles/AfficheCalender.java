package Controles;

import models.Calendar;
import services.calenderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficheCalender implements Initializable {
    @FXML
    private Button ajtbtn;
    @FXML
    private VBox vbox;
    public void ajouter(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Addcalender.fxml"));
        Parent root=loader.load();
        ajtbtn.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Calendar> list;
        calenderService cs =new calenderService();
        try {
            list=cs.listAllcalenders();
            for(int i=0;i<list.size();i++){
                FXMLLoader fxl=new FXMLLoader();
                fxl.setLocation(getClass().getResource("/fxml/cardcalender.fxml"));
                Parent root=fxl.load();
                Cardcalender c=fxl.getController();
                c.setdata(list.get(i));
                c.setId(list.get(i).getId());
                vbox.getChildren().add(root);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void retour(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/AfficheEventAdmin.fxml"));
        Parent root=loader.load();
        vbox.getScene().setRoot(root);
    }
}

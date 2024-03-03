package Controles;

import models.evenement;
import services.EventService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Eventclient implements Initializable {
    @FXML
    private VBox vbox;
    @FXML
    private Button btnok;
    @FXML
    private TextField rechercher;
    private int idu=13;
    public void setParametre(int idu) {
        this.idu = idu;
        System.out.println("ID de l'client connecté page evenement : " + idu);
    }
    public void ok(ActionEvent actionEvent) throws SQLException, IOException {

        String text =rechercher.getText();
        if(!text.isEmpty()){
            EventService es=new EventService();
            evenement e=es.getEventBynom(text);
           if(e!=null){
               //efface tous les enfants du conteneur vbox. Un vbox est probablement un conteneur graphique
               vbox.getChildren().clear();
               //Crée un chargeur FXML, qui permet de charger une hiérarchie de nœuds à partir d'un fichier FXML.
               FXMLLoader fxl=new FXMLLoader();
               //Spécifie l'emplacement du fichier FXML à charger
               fxl.setLocation(getClass().getResource("/fxml/CardEventClient.fxml"));
               //Charge la hiérarchie de nœuds depuis le fichier FXML spécifié et retourne le nœud racine.
               Parent root=fxl.load();
               // Récupère le contrôleur associé à la vue FXML
               CardEventClient c=fxl.getController();
               //récupéré et le nombre d'utilisateurs associés à cet événement.
               c.setdata(e,es.nombredeutulisateurparevent(e.getId()));
               c.setId(e.getId());
               c.SetIdu(idu);
               // Ajoute le nœud racine de la vue FXML  au conteneur vbox.
               vbox.getChildren().add(root);
            }else{
               Notifications n2 = Notifications.create()
                       .title("Info")
                       .text("Désolé,Il n'y a pas un evenment avec cette nom")
                       .hideAfter(Duration.seconds(5))
                       .position(Pos.CENTER_RIGHT)
                       .darkStyle();
               n2.showError();
           }
        }else{
            Notifications n2 = Notifications.create()
                    .title("Info")
                    .text("Il faut entrer le nom d'evenment")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER_RIGHT)
                    .darkStyle();
            n2.showError();
        }

    }

    @Override
    public void  initialize(URL url, ResourceBundle resourceBundle) {
        List<evenement> list;
        EventService es=new EventService();
        try {
            list=es.listAllEvent();
            for(int i=0;i<list.size();i++){
                FXMLLoader fxl=new FXMLLoader();
                fxl.setLocation(getClass().getResource("/fxml/CardEventClient.fxml"));
                Parent root=fxl.load();
                CardEventClient c=fxl.getController();
                c.setdata(list.get(i),es.nombredeutulisateurparevent(list.get(i).getId()));
                c.setId(list.get(i).getId());
                c.SetIdu(idu);
                vbox.getChildren().add(root);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

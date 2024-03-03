package Controles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


     //  FXMLLoader loader=new FXMLLoader(getClass().getResource("/AjouterCategorie.fxml"));
    //FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/AdminDashboard.fxml"));
   // FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
    // FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ClassementConcours.fxml"));
       // FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        //FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlClient/Acceuil.fxml"));
        //FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/AdminDashboard.fxml"));
        //FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/AcceuilArtiste.fxml"));
        //FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml"));

         // FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/CategoriePage.fxml"));
        //FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/AdminDashboard.fxml"));
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
       // FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/PageExposition.fxml"));


         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AfficheEventAdmin.fxml"));
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/AdminDashboard.fxml"));
         //  FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
       // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Eventclient.fxml"));


        try {
            Parent root = loader.load();
            primaryStage.setTitle("Ligin");
            //Scene scene = new Scene(root, 930,700);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}

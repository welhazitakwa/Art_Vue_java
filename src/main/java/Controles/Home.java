package Controles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    double x,y =0;

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Parent root= FXMLLoader.load(getClass().getResource("/fxml/ajouterCategorie.fxml"));
        //Parent root= FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/AdminDashboard.fxml"));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        //move window
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getSceneX() - x);
            primaryStage.setY(event.getSceneY() - y);
        });
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}

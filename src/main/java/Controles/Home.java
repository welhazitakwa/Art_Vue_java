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
double x,y = 0;
    @Override
    public void start(Stage primaryStage) {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("/AjouterCategorie.fxml"));
       // FXMLLoader loader=new FXMLLoader(getClass().getResource("/Login.fxml"));

        try {
            Parent root = loader.load();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragOver(event ->{
                primaryStage.setX(event.getSceneX() - x);
                primaryStage.setY(event.getSceneY() - y);
            });
            Scene scene = new Scene(root, 800, 500);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

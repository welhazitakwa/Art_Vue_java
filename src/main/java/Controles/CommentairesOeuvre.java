package Controles;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Commentaire;
import models.OeuvreArt;
import services.commentaire.CommentaireService;

public class CommentairesOeuvre {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BtnToAccueil;

    @FXML
    private Button BtnToOeuvreArt;

    @FXML
    private VBox commentLayout;

    @FXML
    private Button idBTNCategorie;

    @FXML
    private Button idBTNCategorie1;
    private OeuvreArt oeuvreArt;
    @FXML
    private TextField idTextField;
    private int idClient ;

    public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de l'client connecté : " + idClient);
    }
    @FXML
    private ImageView imageOuevre;
    public void initData(OeuvreArt oeuvreArt) {
        this.oeuvreArt = oeuvreArt;
        System.out.println("l'oeuuvredfdfdfdf : "+oeuvreArt);
        CommentaireService comment1 = new CommentaireService();
        ImageView imgProfile = new ImageView(new Image(oeuvreArt.getImage()));

         imageOuevre.setImage(imgProfile.getImage());
        try {
            List<Commentaire> comments = comment1.getCommentsByOeuvre(this.oeuvreArt.getId()) ;
            System.out.println(comments);
            for (int i=0; i<comments.size();i++) {
                System.out.println(comments.size());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/fxmlClient/CommentaireItem.fxml"));
                HBox hbox = fxmlLoader.load();
                CommentaireItem commentaireItem = fxmlLoader.getController();
                commentaireItem.setData(comments.get(i));
                commentLayout.getChildren().add(hbox);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addComment(ActionEvent event) {
        CommentaireService comment1 = new CommentaireService() ;
        if (idTextField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setContentText("Vous devez saisir un commentaire non vide ");
            alert.show();
        } else {
        try {
         comment1.ajouter(new Commentaire( idTextField.getText(),idClient,this.oeuvreArt.getId()));
         commentLayout.getChildren().clear();
         initData(this.oeuvreArt);
        } catch (SQLException s){
            System.out.println(s.getMessage());
        } }
    }
    @FXML
    void initialize() {


    }


    public void toOuevre(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOeuvre.fxml"));
            Parent registerParent = loader.load();
            PageOeuvre pageOeuvre = loader.getController();
            pageOeuvre.setParametre(idClient); // Passage de l'ID au contrôleur AcceuilArtiste
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

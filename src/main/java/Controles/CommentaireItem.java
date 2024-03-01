package Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Commentaire;
import models.OeuvreArt;
import services.commentaire.CommentaireService;
import services.utilisateur.UtilisateurService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentaireItem {

    @FXML
    private Label dateComment;

    @FXML
    private Label nomPrenom;

    @FXML
    private TextField textFieldComment;

    @FXML
    private ImageView userImg;
    @FXML
    public Button idDelete;

    @FXML
    public Button idedit;
    private int idClient ;
    private OeuvreArt idsupp ;
    public void setParametre(int param, OeuvreArt o) {
        idClient = param;
        idsupp = o ;
        System.out.println("ahawa l'client connecté : " + param);
    }

    @FXML
    void deleteComment(ActionEvent event) {
        Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle(" Confirmation de suppression ");
        dialogC.setHeaderText("Voulez vous vraiment supprimer ce commentaire ! ");
        // dialogC.setContentText(parametreField.getText());
        dialogC.setContentText("Le commentaire va être supprimé définitivement");

        Optional<ButtonType> answer = dialogC.showAndWait();
        if (answer.get() == ButtonType.OK) {

            try {
                CommentaireService comment1 = new CommentaireService() ;
                comment1.supprimer(idOeuvre);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/CommentairesOeuvre.fxml"));
                    Parent detailsOeuvre = loader.load();
                    CommentairesOeuvre commentairesOeuvre = loader.getController();
                    commentairesOeuvre.setParametre(idClient);
                    commentairesOeuvre.initData(idsupp);
                    Scene currentScene = ((Node) event.getSource()).getScene();

                    // Modifier la racine de la scène pour afficher les détails de l'œuvre
                    currentScene.setRoot(detailsOeuvre);
                } catch (IOException ex) {
                    Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException e) {
            System.out.println(e.getMessage());
}
        }
        else {
            System.out.println("User chose Cancel or closed the dialog-box");
        }
    }

    @FXML
    void editComment(ActionEvent event) {

    }
    private int idOeuvre = 0 ;
    private int id ;

    public void setData (Commentaire comment) {

        ImageView imgProfile = new ImageView(new Image(comment.getImage()));
        userImg.setImage(imgProfile.getImage());
//        System.out.println("Nom: " + user.getNom());
//        System.out.println("Prenom: " + user.getPrenom());
//        System.out.println("Adresse: " + user.getAdresse());
//        System.out.println("Email: " + user.getEmail());
//        System.out.println("NumTel: " + user.getNumTel());
        textFieldComment.setText("qd");
        nomPrenom.setText("qzd");
        dateComment.setText("qzd");

        if (comment != null) {

            textFieldComment.setText(comment.getCommentaire());
            nomPrenom.setText(comment.getNom()+" "+comment.getPrenom());
            dateComment.setText(String.valueOf(comment.getDate_commentaire()));
            idOeuvre = comment.getId() ;
            id = comment.getOeuvre_id();
            if (comment.getClient_id() == idClient) {
                System.out.println("kifkif rww");
                idedit.setVisible(true);
                idDelete.setVisible(true);
            } else {
                idedit.setVisible(false);
                idDelete.setVisible(false);
                System.out.println("mch kifkif");
            }

        }

    }


}

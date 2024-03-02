package Controles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Utilisateur;
import services.oeuvreArt.OeuvreArtService;
import services.utilisateur.UtilisateurService;
import javafx.scene.control.Label;
//import java.awt.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static Controles.PDFGenerator.generatePDF;

public class UtilisateurDashboard implements Initializable {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Label txt1;
    @FXML
    private Label txt2;
    @FXML
    private Label txt3;
    @FXML
    private Label txt4;
    @FXML
    private VBox usersLayout;
    @FXML
    private ImageView userImg;
    @FXML
    private HBox hbox2;

    @FXML
    private HBox hbox3;
    @FXML
    private HBox navbar;
    @FXML
    private Label label1Dash;

    @FXML
    private Label label2Dash;

    @FXML
    private Label label3Dash;

    @FXML
    private Label label4Dash;

    @FXML
    void AffichageClients(ActionEvent event) {
        txt1.setText(" ");
        txt2.setText(" ");
        txt3.setText(" ");
        txt4.setText(" ");

        UtilisateurService user1 = new UtilisateurService();
        try {
            List <Utilisateur> users = user1.listClients() ;
            System.out.println(users);
            usersLayout.getChildren().clear();

            for (int i=0; i<users.size();i++) {
                System.out.println(users.size());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/userItem.fxml"));
                HBox hbox = fxmlLoader.load();
                UserItem uicontroller = fxmlLoader.getController();
                uicontroller.setParametre(String.valueOf(users.get(i).getId()));
                uicontroller.setParametre2(users.get(i).getNom()+" "+users.get(i).getPrenom());

                uicontroller.setData(users.get(i));
                usersLayout.getChildren().add(hbox);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void affichageArtistes(ActionEvent event) {
        txt1.setText(" ");
        txt2.setText(" ");
        txt3.setText(" ");
        txt4.setText(" ");
        UtilisateurService user1 = new UtilisateurService();
        try {
            List <Utilisateur> users = user1.listAllArtistes() ;
            System.out.println(users);
            usersLayout.getChildren().clear();

            for (int i=0; i<users.size();i++) {
                System.out.println(users.size());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/userItem.fxml"));
                HBox hbox = fxmlLoader.load();
                UserItem uicontroller = fxmlLoader.getController();
                uicontroller.setParametre(String.valueOf(users.get(i).getId()));
                uicontroller.setParametre2(users.get(i).getNom()+" "+users.get(i).getPrenom());
                uicontroller.setData(users.get(i));
                usersLayout.getChildren().add(hbox);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        UtilisateurService user1 = new UtilisateurService();
//        try {
//            List <Utilisateur> users = user1.listAll() ;
//            System.out.println(users);
//            for (int i=0; i<users.size();i++) {
//                System.out.println(users.size());
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/fxml/userItem.fxml"));
//                HBox hbox = fxmlLoader.load();
//                UserItem uicontroller = fxmlLoader.getController();
//                uicontroller.setData(users.get(i));
//                usersLayout.getChildren().add(hbox);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        UtilisateurService user1 = new UtilisateurService() ;
        OeuvreArtService o1= new OeuvreArtService() ;
        try {
            int total = user1.nbtotal()-1 ;
            label1Dash.setText("Total des utilisateurs: \n"+ "                "+total);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            label2Dash.setText("Total des artistes : \n"+ "                "+user1.nbArtistes());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            label3Dash.setText("Total des clients : \n"+ "                " +user1.nbClients());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            label4Dash.setText("Total des œuvres : \n"+ "                " +o1.nombreOeuvresArt());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void telecharger(ActionEvent actionEvent) {
        //List<Utilisateur> userList = null;// = List.of("Utilisateur 1", "Utilisateur 2", "Utilisateur 3");
        //generatePDF(userList, "C:\\Users\\Fatma Ouelhazi\\Downloads\\output.pdf");


        List<String> artistList = new ArrayList<>();
        List<String> clientList = new ArrayList<>();
        UtilisateurService user1 = new UtilisateurService();
        try {
            List <Utilisateur> users = user1.listAll() ;

            System.out.println(users);
            for (int i=0; i<users.size();i++) {
                System.out.println(users.size());
                if (users.get(i).getProfil() == 1) {
                    artistList.add(users.get(i).getNom()+" "+users.get(i).getPrenom());
                } else if (users.get(i).getProfil()==2) {
                    clientList.add(users.get(i).getNom()+" "+users.get(i).getPrenom());
                }
            }
            generatePDF(artistList, clientList,"C:\\Users\\Fatma Ouelhazi\\Downloads\\output.pdf");

            System.out.println("aww fel tryy");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }








    }
}


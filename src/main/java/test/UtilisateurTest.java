package test;

import com.mysql.cj.util.TimeUtil;
import models.Utilisateur;
import services.utilisateur.UtilisateurService;
import utils.MyDataBase;

import java.sql.SQLException;
import java.util.Date;


public class UtilisateurTest {
    public static void main(String[] args) {
        // MyDataBase d =  MyDataBase.getInstance();
        UtilisateurService user1 = new UtilisateurService();
    /*   try {
            user1.ajouter(new Utilisateur(
                    "crypted", "pwd","check@gmail.com",
                    12365479, "233AFT", "123.","imaage ", "genre",
                    new java.sql.Date(28022005), "addddd"));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }*/


        try {
            user1.modifier(new Utilisateur(1, "edit", "test","cryptage@gmail.com",
                    12367009, "2cvcv3AFT", "88123.","imcaage ", "gencvre",
                    new java.sql.Date(280922005), "acvcvddd"));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }

       /* try {
            user1.supprimer(4);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } */

       /* try {
            System.out.println(user1.listAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
    }
    String userProvidedPassword = "user123";

    // Vérifier le mot de passe lors de la connexion
    String enteredPassword = "user123";
        }
